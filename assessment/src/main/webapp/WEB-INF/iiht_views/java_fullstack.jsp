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
        <link href="https://fonts.googleapis.com/css?family=Segoe+UI" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="css/font-awesome.css" rel="stylesheet" type="text/css">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link href="css/responsive.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
    </head>

    <body>

        <div class="maincontainer">            

            <div class="wrapper">
                <div class="row row-offcanvas row-offcanvas-left">
                    <!-- sidebar -->
		   <jsp:include page="side_reviewer.jsp" /> 
                 
                    <!-- /sidebar -->

                    <!-- main right col -->
                    <div class="column col-sm-10 col-xs-11" id="main">

                        <div class="rightside">
                            <div class="topmenu text-right">
              
				
				<a href="signoff">Sign Off</a>
			
                            </div>


                            <div class="questiontable">
                                <div class="questionheading">
                                    <div class="left">
                                        <h4><b>Review Java Full Stack Programs by Test Givers</b></h4>
                                    </div>
                                   
                                </div>


                                <div class="questiontablelist" style="overflow-x:scroll;height:500px;">
                                    <table class="table">
                                        <thead>
                                            <tr>
						<th>No</th>
                                                <th>Test Name</th>
                                                <th  style="white-space:nowrap;">Section Name</th>
                                                <th>Test Giver Full Name</th>
												<th>Test Giver Email</th>
                                                <th  style="white-space:nowrap;">Date of Submission</th>
						<th  style="white-space:nowrap;">Review</th>
						
                                            </tr>
                                        </thead>
                                        <tbody>
					<tbody>
						                     
						                       <c:forEach  items="${instances}" var="ins" varStatus="loop">   
						                      	<tr>

										<td>${loop.count}</td>		

												
						                      		<td>${ins.testName}</td>
										
						                      		<td> ${ins.sectionName}</td>
						                      		<td> ${ins.uerFullName}</td>
						                      		<td> ${ins.user}</td>
													
													<td> ${ins.workspaceDateOfSubmission}</td>
						                      		<td><a  href="javascript:void(0);" onClick="javascript:review('${ins.id}', '${ins.questionText}', '${ins.workspaceUrl}', '${ins.usageDocumentUrl}', '${ins.uerFullName}')">Click </a>   </td>
						                      		
						                      	</tr>
						                      	</c:forEach>   
						                      </tbody>
                                           
                                        </tbody>
                                    </table>
                                </div>

                            </div>

                        </div>

                    </div>
                    <!-- /main -->
                </div>
            </div>

        </div>
		
		<!-- Review details Test Popup -->
        <div id="modalshare" class="modal fade modalcopy" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Review Project</h4>
                    </div>
                    <div class="modal-body">
                        <form method="POST" action="sharePublicTest">
						<input id="qmsid" name="qmsid" type="hidden" value="">
						
						<input id="fullname" name="fullname" type="hidden" value="">
                            <label>Problem Statement</label>
                            
							
							<textarea id="problem" rows="3" cols="70" disabled></textarea>
							
                            <label>Workspace Link - <a id="workspace" href="" target="_blank">Click here</a> </label> 
							
                           <!-- <input id="workspace" type="text"/> -->
                            <label>Project Docmentation Uploaded by user - <a id="docurl" href="" target="_blank">Click here</a></label>
                            
                            <label> Functional Compliance - 
								<select id="fun1">
								  <option value="EXCEPTIONAL">EXCEPTIONAL</option>
								  <option value="HIGH">HIGH</option>
								  <option value="AVERAGE">AVERAGE</option>
								  <option value="POOR">POOR</option>
								  <option value="NONE">NONE</option>
								</select>
							</label>
							<textarea id="functional" rows="3" cols="70" placeholder="Enter Functional Compliance Comments"></textarea>
							
							 <label> Design For Performance - 
								<select id="per1">
								  <option value="EXCEPTIONAL">EXCEPTIONAL</option>
								  <option value="HIGH">HIGH</option>
								  <option value="AVERAGE">AVERAGE</option>
								  <option value="POOR">POOR</option>
								  <option value="NONE">NONE</option>
								</select>
							</label>
							<textarea id="performance" rows="3" cols="70" placeholder="How Code was geared for Performance"></textarea>
                            
                           <label> Design For Scalability - 
								<select id="sca1">
								  <option value="EXCEPTIONAL">EXCEPTIONAL</option>
								  <option value="HIGH">HIGH</option>
								  <option value="AVERAGE">AVERAGE</option>
								  <option value="POOR">POOR</option>
								  <option value="NONE">NONE</option>
								</select>
							</label>
							<textarea id="scalability" rows="3" cols="70" placeholder="How Scalable is the Design" ></textarea>
							
							<label> Security - 
								<select id="sec1">
								  <option value="EXCEPTIONAL">EXCEPTIONAL</option>
								  <option value="HIGH">HIGH</option>
								  <option value="AVERAGE">AVERAGE</option>
								  <option value="POOR">POOR</option>
								  <option value="NONE">NONE</option>
								</select>
							</label>
							<textarea id="security" rows="3" cols="70" placeholder="How Secured is the Code"></textarea>
                           
							<label> Code Flexibility - 
								<select id="fle1">
								  <option value="EXCEPTIONAL">EXCEPTIONAL</option>
								  <option value="HIGH">HIGH</option>
								  <option value="AVERAGE">AVERAGE</option>
								  <option value="POOR">POOR</option>
								  <option value="NONE">NONE</option>
								</select>
							</label>
							<textarea id="flexibility" rows="3" cols="70" placeholder="How much Code need to change for changing requirements"></textarea>
							
							<label> Code Adaptability - 
								<select id="ada1">
								  <option value="EXCEPTIONAL">EXCEPTIONAL</option>
								  <option value="HIGH">HIGH</option>
								  <option value="AVERAGE">AVERAGE</option>
								  <option value="POOR">POOR</option>
								  <option value="NONE">NONE</option>
								</select>
							</label>
							<textarea id="adaptability" rows="3" cols="70" placeholder="Can the Design adapt to future requirements" ></textarea>
							
							<label> Test Cases Quality - 
								<select id="tes1">
								  <option value="EXCEPTIONAL">EXCEPTIONAL</option>
								  <option value="HIGH">HIGH</option>
								  <option value="AVERAGE">AVERAGE</option>
								  <option value="POOR">POOR</option>
								  <option value="NONE">NONE</option>
								</select>
							</label>
							<textarea id="testCasesQuality" rows="3" cols="70" placeholder="How good are the test cases"></textarea>
							<label>Overall </label>
							<textarea id="overAll" rows="3" cols="70" placeholder="Any thing else?" ></textarea>
							
							<label> Review Status - 
								<select id="over1">
								  <option value="NOT_STARTED">NOT_STARTED</option>
								  <option value="IN_PROGRESS">IN_PROGRESS</option>
								  <option value="COMPLETE">COMPLETE</option>
								  
								</select>
							</label>
							
							 <input type="button" value="Save Review" onClick="javascript:submitReview()"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
		
     <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>



        <script>

		function review(id, question, workspaceurl, projectdoc, fullName) {
			$('#modalshare').modal('show');
			document.getElementById('qmsid').value = id;
			document.getElementById('fullname').value = fullName;
			document.getElementById('problem').value = question;
			document.getElementById("workspace").href = workspaceurl;
			document.getElementById("docurl").href = projectdoc;
			
			$.ajax({
				url : "getCodeMetrics?qMapperInstanceId="+id,
				type : "GET",
				data : null,
				success : function(res) {
					console.log("SUCCESS: ", res);
					
					if(res != null && res != ''){
						console.log("111 res is "+res);
						document.getElementById("fun1").value = res.fc;
						
						document.getElementById("per1").value = res.per;
						document.getElementById("sca1").value = res.sca;
						document.getElementById("sec1").value = res.sec;
						document.getElementById("fle1").value = res.flex;
						document.getElementById("ada1").value = res.ada;
						document.getElementById("tes1").value = res.tes;
						document.getElementById("over1").value = res.sts;
						
						document.getElementById('functional').value = res.functionalComplianceComments;
						document.getElementById('performance').value = res.performanceComments;
						document.getElementById('scalability').value = res.scalabilityComments;
						document.getElementById('security').value = res.securityComments;
						document.getElementById('flexibility').value = res.flexibilityComments;
						document.getElementById('adaptability').value = res.adaptibilityComments;
						document.getElementById('testCasesQuality').value = res.testCasesComments;
						document.getElementById('overAll').value = res.overAll;
					}
					
					
				},
				error : function(e) {
					console.log("error is: ", e);
				}
			});
		}
		
		
		function submitReview(){
			var url = "saveFullstackReview";
			console.log('here url '+url);
			var fun1 = document.getElementById("fun1");
			var fun1Select = fun1.options[fun1.selectedIndex].value;
			
			var per1 = document.getElementById("per1");
			var per1Select = per1.options[per1.selectedIndex].value;
			
			var sca1 = document.getElementById("sca1");
			var sca1Select = sca1.options[sca1.selectedIndex].value;
			
			var sec1 = document.getElementById("sec1");
			var sec1Select = sec1.options[sec1.selectedIndex].value;
			
			var fle1 = document.getElementById("fle1");
			var fle1Select = fle1.options[fle1.selectedIndex].value;
			
			var ada1 = document.getElementById("ada1");
			var ada1Select = ada1.options[ada1.selectedIndex].value;
			
			var tes1 = document.getElementById("tes1");
			var tes1Select = tes1.options[tes1.selectedIndex].value;
			
			var over1 = document.getElementById("over1");
			var over1Select = over1.options[over1.selectedIndex].value;
			var data = {
			"questionMapperInstanceId":document.getElementById('qmsid').value,
			"fullName":document.getElementById('fullname').value,
			"fc": fun1Select,
			"per": per1Select,
			"sca": sca1Select,
			"sec": sec1Select,
			"flex": fle1Select,
			"ada": ada1Select,
			"tes": tes1Select,
			"sts": over1Select,
			"functionalComplianceComments":document.getElementById('functional').value,
			"performanceComments":document.getElementById('performance').value,
			"scalabilityComments":document.getElementById('scalability').value,
			"securityComments":document.getElementById('security').value,
			"flexibilityComments":document.getElementById('flexibility').value,
			"adaptibilityComments":document.getElementById('adaptability').value,
			"testCasesComments":document.getElementById('testCasesQuality').value,
			"overAll":document.getElementById('overAll').value,
			}
		$.ajax({
				url : url,
				type : "POST",
				data : data,
				success : function(res) {
					console.log("SUCCESS: ", res);
					notify('INFO', 'Your review has been saved');
					$('#modalshare').modal('hide');
					//document.getElementById("no-"+sectionName).innerHTML = data;
					
				},
				error : function(e) {
					console.log("ERROR: ", e);
					notify('INFO', 'There is some problem submiting your review. Please try again');
					$('#modalshare').modal('hide');
				}
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
		
		function confirm(id) {
           (new PNotify({
		    title: 'Confirmation Needed',
		    text: 'Are you sure? Do you really want to delete this Q?',
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
		    window.location = "removeQuestionFromList?qid="+id;
		}).on('pnotify.cancel', function() {
		   
		});
        }
	
     
		    
        </script>

	
	<c:if test="${msgtype != null}">
		 <script>
		 var notification = 'Information';
		 $(function(){
			 new PNotify({
			 title: notification,
			 text: '${message}',
			 type: '${msgtype}',
			 styling: 'bootstrap3',
			 hide: true
		     });
		 }); 	 
	      </script>
	</c:if>
    </body>
</html>
