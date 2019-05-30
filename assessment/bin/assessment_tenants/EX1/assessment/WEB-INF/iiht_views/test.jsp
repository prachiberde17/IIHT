<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>IIHT</title>
        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="css/font-awesome.css" rel="stylesheet" type="text/css">
        <link href="css/style.css" rel="stylesheet" type="text/css">
	<link href="css/style_testjourney.css" rel="stylesheet" type="text/css">
        <link href="css/responsive.css" rel="stylesheet" type="text/css">
	<link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/html2canvas.js"></script>
	<script>
	var active = 'true';
	var studentNameTaken = localStorage.getItem('${studentTestForm.firstName}${studentTestForm.lastName}');
	var testNameTaken = localStorage.getItem('testName-${studentTestForm.firstName}${studentTestForm.lastName}');
	var tc= localStorage.getItem('timeCounter-${studentTestForm.firstName}${studentTestForm.lastName}');
		
		
		if(studentNameTaken == 'yes' && testNameTaken == '${studentTestForm.firstName}${studentTestForm.lastName}-${studentTestForm.testName}'  && tc != null){
		timeCounter=tc;
		}
		else{
			timeCounter = 0;
		}
		
		if(tc == null){
			timeCounter= 0;
		}
		
	function setTimeOnLoad(){
	timeProcess();
	}	
	
	function timeProcess(){
	timeCounter = parseInt(timeCounter) + 1;
	var end = new Date();
	var hours =  (${studentTestForm.duration}/60) % 60;
	var minutes = (${studentTestForm.duration}) % 60;
	var seconds = (${studentTestForm.duration} * 60) % 60;
	
	end.setMinutes(minutes);
	end.setHours(hours);
	end.setSeconds(seconds);
	
	var start = new Date();
	start.setMinutes((timeCounter/60) % 60);
	start.setHours((timeCounter/(60*60)) % 60);
	start.setSeconds(timeCounter % 60);
	
	var t = Date.parse(end) - Date.parse(start);
	seconds = Math.floor( (t/1000) % 60 );
	minutes = Math.floor( (t/1000/60) % 60 );
	hours = Math.floor( (t/(1000*60*60)) % 24 );
	
	 if (hours   < 10) {hours   = "0"+hours;}
	 
	  if (minutes < 10) {minutes = "0"+minutes;}
	  
	   if (seconds < 10) {seconds = "0"+seconds;}
	
	document.getElementById("examTimer").innerHTML = hours+":"+minutes+":"+seconds;
		
	}
	 	
	var submitTest = 'false';
	
	function examTimer(){
		if(submitTest == 'true'){
			return;
		}
		timeProcess();
	
		if((${studentTestForm.duration} * 60) - timeCounter <= 3 ){
			notify('Info', 'Test Time exceeding shortly! Your test will be auto submitted');
		}		
			
		if( timeCounter >= (${studentTestForm.duration} * 60)  ){
		submitTest();
		}
	}
	
	function takeScreenShot(){
		if(active == 'false'){
			notify('Info', 'The exam window looks to be in the background. This is a non-compliance. We are recording it in our system. If number of non-

compliances exceed a threshold, the Test Admin may mark this attempt as void. Please beware! ');
			var datasend="user=${studentTestForm.emailId}\ntestName=${studentTestForm.testName}\ncompanyId=${studentTestForm.companyId}";
			$.ajax({
			    type: "POST",
			    url: "registerNonCompliance",
			    data: { 
				data:datasend
			    }
			}).done(function(fileName) {
				alert("done");

			}); 
		}
		else if(active == 'true'){
			this.window.focus();
			 html2canvas(document.querySelector("#screenShotId"), {
			logging: true,
			allowTaint: true
		    }).then(function(canvas) {
			var dataImage = canvas.toDataURL("image/png");
			$.ajax({
			    type: "POST",
			    url: "uploadScreenSnapShot?testName=${studentTestForm.testName}",
			    data: { 
				data:dataImage
			    }
			}).done(function(fileName) {
				alert("done");

			}); 
		    });
		}
	
	
	}
	
	function activeScreen(){
	active = 'true';
	//alert(' active ' +active);
	}
	
	function passiveScreen(){
	active = 'false';
	//alert(' passicve ' +active);
	}
	window.addEventListener('focus', activeScreen);
	window.addEventListener('blur', passiveScreen);

	
	var myVar = setInterval(examTimer, 1000);
	var myVar2 = setInterval(takeScreenShot, 45000);
	</script>
    </head>
    <body onload="setTimeOnLoad()">


	 <form:form id="testForm" name="testForm" method="POST"   modelAttribute="currentQuestion">


        <div class="startexams"  id="screenShotId">
            <div class="col-md-12">
                <div class="topbar">
                    <div class="col-md-3 col-sm-12">
                        <h3>${studentTestForm.testName}</h3>
                    </div>
                    <div class="col-md-9 col-sm-12">
                        <h5>Total Questions - ${studentTestForm.totalQuestions}    &nbsp&nbsp&nbsp </h5>
                        <h5>Total Attempts Yet - ${studentTestForm.noOfAttempts}  &nbsp&nbsp&nbsp   </h5>
                        <h5 style="margin-right: 10px;">Welcome ${studentTestForm.firstName} ${studentTestForm.lastName}  |</h5>
                        <h5 ><i class="fa fa-clock-o" aria-hidden="true"></i> &nbsp <h5 id="examTimer"> </h5> </h5>
                    </div>
                </div>


                <div class="examscontents">
                    <div class="col-md-2 left">
                        <div class="sections">
                            <h3>Sections</h3>
                            <ul>
				<c:forEach var="sectionInstance" varStatus="status" items="${sectionInstanceDtos}" >
                                <li ${sectionInstance.style}  onclick="javascript:changeSection('${sectionInstance.section.sectionName}');">

${sectionInstance.section.sectionName}</li>
                                
				</c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-7 center">
                        <div class="question">
                            <span>${currentQuestion.position}</span>
                            <p>${currentQuestion.questionMapperInstance.questionMapper.question.questionText}</p>
                            <div class="options">
                                <ul>
				
                                    <li style="${currentQuestion.questionMapperInstance.questionMapper.question.choice1 == null || 

currentQuestion.questionMapperInstance.questionMapper.question.choice1.trim().length() == 0? 'display: none;':''}">
				   
				    <form:checkbox path="one" />
				    ${currentQuestion.questionMapperInstance.questionMapper.question.choice1}
				    </li>
				    
                                    <li style="${currentQuestion.questionMapperInstance.questionMapper.question.choice2 == null || 

currentQuestion.questionMapperInstance.questionMapper.question.choice2.trim().length() == 0? 'display: none;':''}">
				    
				   <form:checkbox path="two" />
				   ${currentQuestion.questionMapperInstance.questionMapper.question.choice2}
				    </li>
				    
                                    <li style="${currentQuestion.questionMapperInstance.questionMapper.question.choice3 == null || 

currentQuestion.questionMapperInstance.questionMapper.question.choice3.trim().length() == 0? 'display: none;':''}">
				    <form:checkbox path="three" />
				    ${currentQuestion.questionMapperInstance.questionMapper.question.choice3}
				    </li>
				    
                                    <li style="${currentQuestion.questionMapperInstance.questionMapper.question.choice4 == null || 

currentQuestion.questionMapperInstance.questionMapper.question.choice4.trim().length() == 0? 'display: none;':''}">
				    <form:checkbox path="four" />
				    ${currentQuestion.questionMapperInstance.questionMapper.question.choice4}
				    </li>
				    
                                    <li style="${currentQuestion.questionMapperInstance.questionMapper.question.choice5 == null || 

currentQuestion.questionMapperInstance.questionMapper.question.choice5.trim().length() == 0? 'display: none;':''}">
				    <form:checkbox path="five" />
				    ${currentQuestion.questionMapperInstance.questionMapper.question.choice5}
				    </li>
				    
				    <li style="${currentQuestion.questionMapperInstance.questionMapper.question.choice6 == null || 

currentQuestion.questionMapperInstance.questionMapper.question.choice6.trim().length() == 0? 'display: none;':''}">
				    <form:checkbox path="six" />
				    ${currentQuestion.questionMapperInstance.questionMapper.question.choice6}
				    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 right">
                        <div class="questionpalette">
                            <h4>Question Palette</h4>
                           <!-- <select>
                                <option>section 1</option>
                                <option>section 2</option>
                                <option>section 3</option>
                                <option>section 4</option>
                                <option>section 5</option>
                                <option>section 6</option>
                                <option>section 7</option>
                                <option>section 8</option>
                                <option>section 9</option>
                                <option>section 10</option>
                            </select> -->

                            <div class="questioninfo">
                                <ul>
                                    
				    <c:forEach var="questionInstance" varStatus="status" items="${currentSection.questionInstanceDtos}" >
                                <li  class="${questionInstance.style}">${status.index + 1}</li>
                                
				</c:forEach>
                                </ul>
                            </div>

                            <div class="questatus">
                                <ul>
                                    <li class="answered"><span></span>Answered</li>
                                    <li class="notanswered"><span></span>Not Answered</li>
                                   
                                </ul>
                            </div>

                        </div>
                    </div>
                </div>

            </div>
        </div>


        <div class="nextprebbuttons">
            <div class="innerbutton">
                <div class="col-md-12">
                    <div class="col-md-2">
                        &nbsp;
                    </div>
                    <div class="col-md-7">
                        <div class="flagbtn">
                            <a href="#">flag</a>
                        </div>
                        <div class="nextprebtn">
                           
                            
			    <c:choose>
				    <c:when test="${currentSection.first==true}">
					
				    </c:when>    
				    <c:otherwise>
					<a href="javascript:prev();">Previous</a>
				    </c:otherwise>
			</c:choose>
			
			<c:choose>
				    <c:when test="${currentSection.last==true}">
					<a class="next" href="javascript:next();" id="next">Finish Test</a>
				    </c:when>    
				    <c:otherwise>
					<a class="next" href="javascript:next();" id="next">Next</a>
				    </c:otherwise>
			</c:choose>
			    
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="endtestbtn">
                            <a href="javascript:submitTest();">End Test</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
	</form:form>
	
	<script>
	function changeSection(sectionName){
		window.location = 'changeSection?sectionName='+sectionName+"&timeCounter="+timeCounter;
		localStorage.setItem('timeCounter', timeCounter);
	}
	
	function next(){
	var linktext=document.getElementById('next').text;
		if(linktext == 'Finish Test'){
		submitTest();
		}
		else{
		document.testForm.action = "nextQuestion?questionId=${currentQuestion.questionMapperInstance.questionMapper.id}&timeCounter="+timeCounter;
		storeTimeLocal();
	 document.testForm.submit();
		}
	
	}
	
	function prev(){
	document.testForm.action = "prevQuestion?questionId=${currentQuestion.questionMapperInstance.questionMapper.id}&timeCounter="+timeCounter;
	storeTimeLocal();
	document.testForm.submit();
	}
	
	function submitTest(){
	document.testForm.action = "submitTest?questionId=${currentQuestion.questionMapperInstance.questionMapper.id}&timeCounter="+timeCounter;
	resetTimeLocal();
	//modal.style.display = "block";
	//document.getElementById("showAlert").innerHTML = 'You have exceeded your time limit to complete the test. The test will auto submit in a moment...';
	document.testForm.submit();
	submitTest = 'true';
	
	}
	
	function storeTimeLocal(){
	localStorage.setItem('${studentTestForm.firstName}${studentTestForm.lastName}', 'yes');
	localStorage.setItem('testName-${studentTestForm.firstName}${studentTestForm.lastName}', '${studentTestForm.firstName}${studentTestForm.lastName}-

${studentTestForm.testName}');
	localStorage.setItem('timeCounter-${studentTestForm.firstName}${studentTestForm.lastName}', timeCounter);
	}
	
	function resetTimeLocal(){
	localStorage.setItem('${studentTestForm.firstName}${studentTestForm.lastName}', 'no');
	localStorage.setItem('testName-${studentTestForm.firstName}${studentTestForm.lastName}', null);
	localStorage.setItem('timeCounter-${studentTestForm.firstName}${studentTestForm.lastName}', 0);
	}
	

	</script>
	
	<!-- The Modal -->
	<div id="myModal" class="modal">

	  <!-- Modal content -->
	  <div class="modal-content">
	    <span class="close">&times;</span>
	    <p id="showAlert">Some text in the Modal..</p>
	  </div>

	</div>
	
	<script>
		// Get the modal
		var modal = document.getElementById('myModal');

		// Get the button that opens the modal
		var btn = document.getElementById("myBtn");

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];
			
		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
		    modal.style.display = "none";
		}	
		
		function notify(messageType, message){
		 var notification = 'Information';
			 $(function(){
				 new PNotify({
				 title: notification,
				 text: message,
				 type: messageType,
				 styling: 'bootstrap3',
				 hide: false
			     });
			 }); 	
		}


		$(document).ready(function () {
    			//Disable cut copy paste
    			$('body').bind('cut copy paste', function (e) {
        			e.preventDefault();
    			});
   
    			//Disable mouse right click
    			$("body").on("contextmenu",function(e){
       				return false;
    			});
		});
	</script>
       
    </body>
</html>