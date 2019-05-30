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
        <link href='http://fonts.googleapis.com/css?family=Roboto:300,400,700' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Muli:300,400,700' rel='stylesheet' type='text/css'>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/font-awesome.css" rel="stylesheet" type="text/css">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/responsive.css" rel="stylesheet" type="text/css">
         <link href="css/font-awesome_new.css" rel="stylesheet" type="text/css">
         <link href="css/style_new.css" rel="stylesheet" type="text/css">
        <link href="css/responsive_new.css" rel="stylesheet" type="text/css">
        <link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
         <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
		<script type="text/javascript" src="scripts/custom.js"></script>
		<script type="text/javascript" src="scripts/jquery.min.js"></script>
		
		<script src="scripts/src-min-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>
    </head>
    <body>
        <form:form id="studentIntro" method="POST" action="studentJourney"  modelAttribute="studentTestForm">
            <div class="header">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="logo">
							<a href="#"><img src="images/logoiiht.png"></a>
						</div>
					</div>
					<div class="col-md-6">
						<div class="userheader">
							<div class="userinfo">
								<h4>Welcome ${studentTestForm.userName}<span>${studentTestForm.emailId}</span></h4>
								<!-- <img src="images/userimg.png"> -->
								<img src="${studentTestForm.techLogo}">
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="creationtimeline">
				<div class="col-md-12">
					<div class="col-md-6 text-center">
						<img src="images/starttestimg.png">
					</div>
					<div class="col-md-6">
						<div class="starttestinfo">
							<div class="testname">
								<img src="images/testimage.png">
								<h3>${studentTestForm.testName}</h3>
							</div>
							<div class="totalque">
								<span>Total Questions &nbsp;&nbsp;  ${studentTestForm.totalQuestions}</span>
								<span>Duration &nbsp;&nbsp; ${studentTestForm.duration} minutes</span>
							</div>
							<div class="publishdate">
								<span>Published on <br> ${studentTestForm.formattedPublishedDate}</span>
								<span>To be completed by <br> Within 1 session</span>
							</div>
							<div class="startbutton">
								<a href="javascript:start()">START TEST</a>
							</div>
							<div class="publishdate">
								<span><i class="fa fa-user"></i>System Administrator</span>
								<span><i class="fa fa-envelope"></i>reachus@iiht.com</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			</form:form>
			
			<div class="importantinstruction">
				<div class="col-md-12">
					<h3>Important Instructions</h3>
					<p>McClintock's eye for detail certainly helped narrow the whereabouts of lorem ipsum's origin, however, 
					the âhow and whenâ still remain something of a mystery, with competing theories and timelines.</p>
				</div>
				<div class="col-md-12">
					<div class="item col-md-3">
						<div class="introicon"><img src="images/introicon1.png"></div>
						<p>In case of power failure,the test will have to be rescheduled</p>
					</div>
					<div class="item col-md-3">
						<div class="introicon"><img src="images/introicon2.png"></div>
						<p>In case of internet failure, submission will not be possible & the test will have to be rescheduled	</p>
					</div>
					<div class="item col-md-3">
						<div class="introicon"><img src="images/introicon3.png"></div>
						<p>There is no negative <br> marking</p>
					</div>
					<div class="item col-md-3">
						<div class="introicon"><img src="images/introicon4.png"></div>
						<p>Please attempt all questions.The test will be submitted only when it is completed in all respect</p>
					</div>
				</div>
			</div>
			
			
				
			
			<div class="logincopyright">
				<div class="col-md-12">
					<p>Copyright 2018 IIHT. All Rights Reserved. Privacy Policy For Enterprise Solutions</p>
				</div>
			</div>

       <!--  <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script> -->
        
         <script>
		     
		   /*   function handleClick(cb) {
				 if(cb.checked){
					document.getElementById("startTest").disabled = false;
				 }
				 else{
				 document.getElementById("startTest").disabled = true;
				 }
			} */
			
			function start(){
			
				
			document.getElementById("studentIntro").submit();
			}
		     </script>

    </body>
</html>
