<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*, com.assessment.web.dto.*" %>
<html>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>IIHT</title>
        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="css/font-awesome.css" rel="stylesheet" type="text/css">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link href="css/responsive.css" rel="stylesheet" type="text/css">
	<link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
	
	<style>
	h4{
	    display: inline;
	    
	}
	</style>
	<script>
	// console.log(' tttt '+${msgtype});
	</script>
	
	<c:if test="${msgtype != null}">
		 <script>
		 var notification = 'Information';
		 var mtp = "${msgtype}";
		 var msg = "${message}";
		 console.log(mtp+' '+msg);
		 $(function(){
			 new PNotify({
			 title: notification,
			 text: msg,
			 type: mtp,
			 styling: 'bootstrap3',
			 hide: true
		     });
		 }); 	 
	      </script>
	</c:if>
	
	<script>
		 var point=false;
		 var count=0;
		    function check(e,value){
		   
		    //Check Charater
		    debugger;
		    if(count==3)return false;
			var unicode=e.charCode? e.charCode : e.keyCode;
			
			if( unicode == 46 && point==true)
			       return false;
			if( unicode == 46 && point==false)
			{
					point=true;
			}
			if (unicode!=8)if((unicode<48||unicode>57)&&unicode!=46)return false;
			if(point==true)count++;
		    }
		    function checkLength(){
		    var fieldVal = document.getElementById('txtF').value;
		    //Suppose u want 3 number of character
		    if(fieldVal <= 100){
			return true;
		    }
		    else
		    {
			var str = document.getElementById('txtF').value;
			str = str.substring(0, str.length - 1);
		    document.getElementById('txtF').value = str;
		    }
		    }
</script>
	
    </head>
    <body>
    
    	
    
        <div class="maincontainer">            
            <div class="wrapper">
                <div class="row row-offcanvas row-offcanvas-left">
                    <!-- sidebar -->
                    <jsp:include page="side.jsp" /> 
                    <!-- /sidebar -->
                    <!-- main right col -->
                    <div class="column col-sm-10 col-xs-11" id="main">
<!-- main contain -->
<div class="rightside">
    <div class="rightdiv settest" style="width: 100%;padding-right: 15px;height: 550px;">


        <div class="teststeps">
            <div class="steps">
                <span>1</span>
                <label><img src="images/u1107.png">Set your test</label>
            </div>
            <div class="steps line">
                <img src="images/u1102.png">
            </div>
            <div class="steps active">
                <span>2</span>
                <label><img src="images/u1105.png">Add Questions</label>
            </div>
            <div class="steps line">
                <img src="images/u1102.png">
            </div>
            <div class="steps">
                <span>3</span>
                <label><img src="images/u1114.png">Add Candidates</label>
            </div>
            <div class="steps line">
                <img src="images/u1102.png">
            </div>
            <div class="steps">
                <span>4</span>
                <label><img src="images/u1106.png">Send Invitation</label>
            </div>
        </div>

        
        <div class="addqueform">

            <div class="step2sections">
                <label class="addquestion">Sections <span>+</span>  </label>

                <div class="quesectiondiv">
			<c:forEach items="${test.sectionDtos}" var="section" >
			    <div style="${section.style}" >
				<h4 style="font-size: 14;font-weight: bold;" onclick="highlight('${section.sectionName}');return false">${section.sectionName}  - </h4> 
				<h4 id="no-${section.sectionName}" style="font-size: 14;font-weight: bold;" >${section.noOfQuestions} </h4> 
				<a href="javascript:removeSection('${section.sectionName}');" > x </a>   
			    </div>
			   
			</c:forEach>
		
                    
                </div>

            </div>
            <div class="step2content">
                <b><h4 class="sectionlabel">Import Questions into Section</h4></b>

                <!-- <div class="questiontable" style="display: none;"> -->
		<div class="questiontable">
                    <div class="questionheading">
                        <div class="right" style="float: left;width: 30%;">
			 
                            
			    <div class="searchdata">
                               
				
				<input type="text" placeholder="Search a Q" name="searchText" id="searchText" style="width : 100%">
                                 <i class="fa fa-search" id="search"></i>
				
                            </div>
                             
			    
                        </div>
			<br/>
                        <div class="queselected">
			
			<input type="text"  id="sectionTopic" placeholder="Enter Section Name" style="width: 13%;"   value="${sectionDto.sectionName}"/>
			<a href="addteststep2" id="showAllQs" style="color:#492a26"> Show All Parent Categories |</a>
			<a href="javascript:showSelected();" id="showSelected" style="color:#228B22"> Show Selected |</a>
			
			<a href="removeAllQuestions" style="color:red">Clear All |</a>
			<a style="color:#020014;" href="javascript:saveSection();">Save Section</a>
			<input type="label" value="Percent of Qs to be asked -"  style="border:none;font-size:"8px" />
			<input type="number"  onKeyPress="return check(event,value)" onInput="checkLength()"  id="txtF" placeholder="Enter percentage" style="width: 7%;" value="${sectionDto.percentQuestionsAsked}"/>
                        </div>
			<br>
			
			
			
                    </div>
                    <div class="questiontablelist" style="height: 500px;overflow-x:auto;padding-top: 0;">
                         <table class="table">
                            <thead>
                                <tr>
                                   
                                    <th>Question</th>
                                    <th>Category</th>
                                    <th>Level</th>
				    <th>Points</th>
                                     <th>Select</th>
                                </tr>
                            </thead>
                            <tbody>
						       <%
							int count = 0;
							%>						       
						       <c:forEach  items="${qs}" var="ques" >   
							<tr id="${ques.id}"  bgcolor="${ques.selected? '#33FFF9':'transparent'}">
								
										
								<td><c:out value="${ques.questionText}"></c:out>  </td>
								
								<td> ${ques.testCategory}</td>
								<td><c:out value="${ques.difficultyLevel.level}"></c:out>   </td>
								
								<td><input type="text" placeholder="Marks, if Correct" value="1  "  disabled  style="width:100%;"></td>
								
								<td  id="${ques.id}-add" style="${ques.selected? 'display: none;':''}"><a href="javascript:addQ('${ques.id}', 'Core Java');">Click to Add</a> </td>
								<td  id="${ques.id}-remove" style="${ques.selected? '':'display: none;'}"><a href="javascript:removeQ('${ques.id}', 'Core Java');">Click to Remove</a></td>
							</tr>
							<%
								count++;
							%>
							</c:forEach>   
						      </tbody>
			   
			</tbody>
                        </table>
                    </div>

                </div>

            </div>


            <div class="addquestionform" style="display: none;">
                <span class="close_queform">+</span>
                <form>
                    <div class="formfield">
                        <label>Question</label>
                        <textarea></textarea>
                    </div>
                    <div class="formfield">
                        <select class="quequery">
                            <option>Select Question Type</option>
                        </select>
                    </div>
                    <div class="formfield">
                        <select>
                            <option>Select Category</option>
                        </select>
                    </div>
                    <div class="formfield">
                        <select>
                            <option>Select Difficulty Level</option>
                        </select>
                    </div>
                    <div class="formfield">
                        <div class="selectoptions">
                            <span>Options</span>
                            <span style="float: right;padding-right: 20px;">Correct Choice</span>
                            <div id="maindivforaddmore">
                                <div class="option">
                                    <span>A</span>
                                    <input type="text">
                                    <div class="choice">
                                        <input name="option" type="radio">
                                    </div>
                                </div>
                                <div class="option">
                                    <span>B</span>
                                    <input type="text">
                                    <div class="choice">
                                        <input name="option" type="radio">
                                    </div>
                                </div>
                                <div class="option">
                                    <span>C</span>
                                    <input type="text">
                                    <div class="choice">
                                        <input name="option" type="radio">
                                    </div>
                                </div>
                                <div class="option">
                                    <span>D</span>
                                    <input type="text">
                                    <div class="choice">
                                        <input name="option" type="radio">
                                    </div>
                                </div>
                                <div class="option">
                                    <span>E</span>
                                    <input type="text">
                                    <div class="choice">
                                        <input name="option" type="radio">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="addanother">
                            <a id="addanother" href="javascript:void(0);"><span>+</span> Add another option</a>
                        </div>
                    </div>
                    <div class="formfield addimagevideo">
                        <a class="addimage" href="#">Add image</a>
                        <a class="addaudio" href="#">Add audio</a>
                        <a class="addvideo" href="#">Add Video</a>
                    </div>
                    <label class="queimage"></label>
                    <label class="queaudio"></label>
                    <label class="quevideo"></label>
                    <input type="file" name="addimage" id="addimage" style="display: none;">
                    <input type="file" name="addaudio" id="addaudio" style="display: none;">
                    <input type="file" name="addvideo" id="addvideo" style="display: none;">
                    <div class="formfield">
                        <label>Question hint</label>
                        <textarea></textarea>
                    </div>
<!--                    <div class="formfield savebtn">
                        <input class="save" type="submit" value="Save">
                        <input type="submit" value="Save and add another">
                        <input type="submit" value="Cancel">
                    </div>-->
                </form>
            </div>
        </div>



    </div>

    <div class="nextbuttons">
        <a class="cancelbtn" href="testlist">Cancel</a>
        <a class="backbtn backstep1" href="gobackStep1Test">Back</a>
        <a class="nextbtn add_test_step3" href="addteststep3">Next</a>
    </div>

</div>
<script>

function saveSection(){
	var name = document.getElementById('sectionTopic').value;
	var txtFValue = document.getElementById('txtF').value;
		if(name.trim().length == 0){
			notify('Information', 'Please enter a meaningful name for your section before saving. ');
			
		}
		else{
			window.location = 'saveSection?sectionTopic='+name+'&percentage='+txtFValue;
			
		}
	
}

function addQ(qid, sectionName){
		
			    
		//window.location = "addQuestionToSection?sectionName="+sectionName+"&questionId="+qid;
		var url = "addQuestionToSectionAjax?sectionName="+sectionName+"&questionId="+qid;
		console.log('here url '+url);
		$.ajax({
				url : url,
				success : function(data) {
					console.log("SUCCESS: ", data);
					var tr = document.getElementById(qid);
					var tds = tr.getElementsByTagName("td");
					var tdadd = document.getElementById(qid+"-add");
					var tdremove = document.getElementById(qid+"-remove");
					console.log(tr);
					console.log(tds);
					console.log(tdadd);
					tr.style.backgroundColor = '#33FFF9';
					tdadd.style.display = "none";
					tdremove.style.display = "";
					
					//document.getElementById("no-"+sectionName).innerHTML = data;
					
				},
				error : function(e) {
					console.log("ERROR: ", e);
					
				}
			});
		    
	}

function removeQ(qid, sectionName){
	//window.location = "removeQuestionToSection?sectionName="+sectionName+"&questionId="+qid;
	    var url = "removeQuestionToSectionAjax?sectionName="+sectionName+"&questionId="+qid;
		console.log('here url '+url);
		$.ajax({
				url : url,
				success : function(data) {
					console.log("SUCCESS: ", data);
					var tr = document.getElementById(qid);
					var tds = tr.getElementsByTagName("td");
					var tdadd = document.getElementById(qid+"-add");
					var tdremove = document.getElementById(qid+"-remove");
					console.log(tr);
					console.log(tds);
					console.log(tdadd);
					tr.style.backgroundColor = 'transparent';
					tdadd.style.display = "";
					tdremove.style.display = "none";
					
					//document.getElementById("no-"+sectionName).innerHTML = data;
					
				},
				error : function(e) {
					console.log("ERROR: ", e);
					
				}
			});
}

function showSelected(){
	//$.ajax({url: "showSectionsQuestions", success: function(result){
	//		notify("Success", "All Questions Selected so far..");
	//	    }});
	window.location = "showSectionsQuestions";
}

function highlight(sectionName){
window.location = "goToSection?sectionName="+sectionName;
}

function removeSection(sectionName) {
           (new PNotify({
		    title: 'Confirmation Needed',
		    text: 'Are you sure you want to delete the section '+sectionName,
		    icon: 'glyphicon glyphicon-question-sign',
		    hide: false,
		    confirm: {
			confirm: true
		    },
		    buttons: {
			closer: false,
			sticker: false
		    },
		    history: {
			history: false
		    }
		})).get().on('pnotify.confirm', function() {
		    window.location = "removeSection?sectionName="+sectionName;
		}).on('pnotify.cancel', function() {
		   
		});
        }


function notify(messageType, message){
		 var notification = 'Information';
			 $(function(){
				 new PNotify({
				 title: notification,
				 text: message,
				 type: messageType,
				 styling: 'bootstrap3',
				 hide: true
			     });
			 }); 	
		}


$('#search').on('click',function(){
	    var text = document.getElementById("searchText").value;
		if(text.length != 0){
		window.location="searchQs?searchText="+text;
		}
	    });


$(function () {
    $(".addimage").on('click', function (e) {
        e.preventDefault();
        $("#addimage").trigger('click');
    });
    $(".addaudio").on('click', function (e) {
        e.preventDefault();
        $("#addaudio").trigger('click');
    });
    $(".addvideo").on('click', function (e) {
        e.preventDefault();
        $("#addvideo").trigger('click');
    });
});

$('#addimage').change(function () {
    var file = $('#addimage')[0].files[0].name;
    $('.queimage').text('Image: ' + file);
});
$('#addaudio').change(function () {
    var file = $('#addaudio')[0].files[0].name;
    $('.queaudio').text('Audio: ' + file);
});
$('#addvideo').change(function () {
    var file = $('#addvideo')[0].files[0].name;
    $('.quevideo').text('Video: ' + file);
});


	function notify(messageType, message){
		 var notification = 'Information';
			 $(function(){
				 new PNotify({
				 title: notification,
				 text: message,
				 type: messageType,
				 styling: 'bootstrap3',
				 hide: true
			     });
			 }); 	
		}

</script>
<!-- /main contain -->


	
  </div>
            </div>
        </div>


	
    </body>
</html>

