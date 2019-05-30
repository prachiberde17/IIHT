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
        <link href="css/responsive.css" rel="stylesheet" type="text/css">
	<link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
    </head>
    <body>
      	<script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>

              <form:form id="studentIntro" method="POST" action="studentJourney"  modelAttribute="studentTestForm">
        	<div class="introsection">
            <div class="importantinstructions">
                <div class="container">
              
                    <div class="instructions">
                        <div class="leftside">
                            <div class="firstdiv">
                            <h2>
                                Welcome ${studentTestForm.userName}</h2>
                                <form:hidden path="userName"  value="${studentTestForm.userName}"/>
                                <h4>${studentTestForm.emailId}</h4>
                                <form:hidden path="emailId" value="${studentTestForm.emailId}"/>
                            </div>
                            <div class="seconddiv">
                                <h2>${studentTestForm.testName}</h2>
                                <h5>Total Questions: ${studentTestForm.totalQuestions}  &nbsp;&nbsp;&nbsp;&nbsp;   Duration: ${studentTestForm.duration}</h5>
				<h5>Test Administered By: ${studentTestForm.testCreatorName} </h5>
				<h5>Test Administrator Email: ${studentTestForm.testCreatedBy} </h5>
                            </div>
                            <div class="thirdddiv">
                                <h4>Published on</h4>
                                <h5> ${studentTestForm.publishedDate}</h5>
                                <br>
                                <h4>To be completed by</h4>
                                <h5>${studentTestForm.publishedDate}</h5>
                            </div>
                        </div>
                        <div class="rightside">
                            
                           
                            
                            <h3>Important Instructions</h3>
			    <ul style="list-style-type:disc">
                            <li><p> In case of power failure, the test will have to be rescheduled</p></li>
                            <li><p> In case of internet failure, submission will not be possible & the test will have to be rescheduled</p></li>
                            <li><p> There is no negative marking</p></li>
                            <li><p> Please attempt all questions</p></li>
                            <li><p> The test will be submitted only when it is completed in all respect</p></li>
			    </ul>

                            <h4 style="padding-top: 10px;padding-bottom: 10px;">Buttons</h4>

                            <div class="buttoninfo">
                                <p>
                                    <span class="button flag">Flag</span>
                                    <form:label path="">Flag: Inorder to flag a question (May respond later), choose an answer option and click on flag button</form:label>
                                </p>
                                <p>
                                    <span class="button next">Next</span>
                                    <form:label path="">Next: By clicking next button the next question appears</form:label>
                                </p>
                                <p>
                                    <span class="button prev">Previous</span>
                                    <form:label path="">Previous: By clicking previous button the previous question appears</form:label>
                                </p>
                                <p>
                                    <span class="button endtest">End Test</span>
                                    <form:label path="">End Test: By clicking end test button the test gets submitted</form:label>
                                </p>
                            </div>
                            
			    <input type="checkbox" id="agreement" onclick='handleClick(this);'/>
                            <!--<div class="starttestbtn">
                                <form:button type="submit" value="submit">Start Test</form:button>
                            </div>-->
                           <div class="starttestbtn">
                            <button type="button" id="startTest" class="btn btn-success" value="submit" onClick="javascript:start();" disabled >Start Test</button>
                            </div> 
                            
                        </div>
                    </div>
                    </div>
                </div>
                 
            </div>
                     </form:form>
		     
		     <script>
		     
		     function handleClick(cb) {
				 if(cb.checked){
					document.getElementById("startTest").disabled = false;
				 }
				 else{
				 document.getElementById("startTest").disabled = true;
				 }
			}
			
			function start(){
			
				
			document.getElementById("studentIntro").submit();
			}
		     </script>

    </body>
</html>
