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
	
        
        
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
	<script type="text/javascript" src="scripts/jquery.min.js"></script>
    </head>
    <body>

        <div class="header">
            <div class="col-md-12">
                <div class="col-md-6">
                    <div class="logo">
                        <a href="#"><img src="images/logoiiht.png"></a>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="userheader headerinfos">
                        <ul>
                            <li><i class="fa fa-envelope"></i>reachus@iiht.com</li>
                           <!-- <li><i class="fa fa-phone"></i>1800-123-321-5</li> -->
                        </ul>
                    </div>
                </div>
            </div>
        </div>


        <div class="creationtimeline">
            <div class="col-md-12">
                <div class="col-md-6 text-center">
                    <img src="images/creationtimeline.png">
                    <div class="col-md-12">
                        <div class="creationcontent">
                            <h3>Yaksha is ready to ask what you already know best!</h3>
				<h2>Fillout data in the fields to your right - Lock, Stock, and Crack.
                           	    Employers want Air Worthy Employees. Yaksha is here to engage,
  				    evaluate & enable you for it.</h2>

                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="starttestinfo loginformnew">
                        <h3>Sign In</h3>
                         <form name="testloginform" class="userform" method="post" modelAttribute="testUserData" action="publicTestAuthenticate">
                          <form:hidden path="testUserData.testId" />
				 			<form:hidden path="testUserData.user.companyName" />
				 			<form:hidden path="testUserData.user.companyId" />	
                            <label>User Name</label>
                         <form:input  type="email" path="testUserData.user.email" name="email" id="username"  placeholder="Email" required="true"/>
                            <label>First Name</label>
						 <form:input path="testUserData.user.firstName" name="firstName" id="firstName"  placeholder="First Name" required="true"/>
                            <label>Last Name</label>
                         <form:input path="testUserData.user.lastName" name="lastName" id="lastName"  placeholder="Last Name" required="true"/>
                            <label>Test Name</label>
                       	 <form:input path="testUserData.testName" name="testName" id="testName"   required="true" disabled="true"/>
                          
                            <label>Company</label>
                             <form:input path="testUserData.user.companyName" name="companyName" id="companyName"  disabled="true"/>
							 
							 <label>Mobile</label>
						 <form:input path="testUserData.user.mobileNumber" name="mobileNumber" id="mobileNumber"  placeholder="Contact No" required="true" /> 
							 
							  <label>Aadhar No</label>
                             <form:input path="testUserData.user.aadharNo" name="aadharNo" id="aadharNo" placeholder="Aadhar" required="true"/>
							 
							   <label>Date of Birth</label>
                             <form:input path="testUserData.user.dateOfBirth" name="dateOfBirth" id="dateOfBirth" placeholder="DD/MM/YYYY" required="true" />
							 
							   <label>Gender -    &nbsp &nbsp &nbsp &nbsp   <form:select path="testUserData.user.gender">
							   <form:option value="Male" label="Male" selected="true"/>
							   <form:option value="Female" label="Female"/>
							</form:select>
							   
							   </label>
                             
							
							 <label>Permanent Address</label>
                             <form:input  path="testUserData.user.permanentAddress" name="permanentAddress" id="permanentAddress" placeholder="Enter Address" required="true"/>
							 
							  <label>Address for Communication</label>
                             <form:input  path="testUserData.user.addressCommunication" name="addressCommunication" id="addressCommunication" placeholder="Enter Address" required="true"/>
							 
							  <label>College Name</label>
                             <form:input path="testUserData.user.collegeName" name="collegeName" id="collegeName" placeholder="College Name" required="true" />
							 
							  <label>10th Percentage</label>
                             <form:input path="testUserData.user.tenPercentage" name="tenPercentage" id="tenPercentage" placeholder="Enter Percentage" required="true"/>
							 
							  <label>10th Year of Passing</label>
                             <form:input path="testUserData.user.tenYearOfPassing" name="tenYearOfPassing" id="tenYearOfPassing" placeholder="Enter YYYY" required="true"/>
							 
							  <label>12th/Diploma Percentage</label>
                             <form:input path="testUserData.user.twelveDiplomaPercentage" name="twelveDiplomaPercentage" id="twelveDiplomaPercentage" placeholder="Enter Percentage" required="true"/>
							 
							  <label>12th/Diploma Year of Passing</label>
                             <form:input path="testUserData.user.twelveDiplomaYearOfPassing" name="twelveDiplomaYearOfPassing" id="twelveDiplomaYearOfPassing" placeholder="Enter YYYY" required="true" />
							 
							  <label>Under Grad Degree -    &nbsp &nbsp &nbsp &nbsp   <form:select path="testUserData.user.underGradDegree">
							   <form:option value="BE/B.Tech" label="BE/B.Tech" selected="true"/>
							   <form:option value="BCA" label="BCA"/>
							    <form:option value="Bsc" label="Bsc"/>
							   <form:option value="Others" label="Others"/>
							</form:select></label>
                            
							 
							  <label>Under Grad Specialization -    <form:select  path="testUserData.user.underGradDegreeSpec">
							   <form:option value="CSE" label="CSE" selected="true"/>
							   <form:option value="ISC" label="ISC"/>
							    <form:option value="IT" label="IT"/>
								 <form:option value="ECE" label="ECE"/>
							   <form:option value="TCE" label="TCE"/>
							    <form:option value="ETE" label="ETE"/>
							   <form:option value="Others" label="Others"/></form:select>
							  </label>
                             
							 
							  <label>Under Graduation Year of Passing</label>
                             <form:input path="testUserData.user.underGradYearOfPassing" name="underGradYearOfPassing" id="underGradYearOfPassing" placeholder="Enter YYYY" required="true"/>
							 
							  <label>Under Graduation Percentage</label>
                             <form:input path="testUserData.user.underGradPercentage" name="underGradPercentage" id="underGradPercentage" placeholder="Enter Percentage" required="true"/>
							 
							 <label>Post Grad Degree -    &nbsp &nbsp &nbsp &nbsp   <form:select path="testUserData.user.postGradDegree">
							   <form:option value="ME/M.Tech" label="ME/M.Tech" selected="true"/>
							   <form:option value="MCA" label="MCA"/>
							    <form:option value="M.sc" label="M.sc"/>
								<form:option value="Others" label="Others"/>
								<form:option value="Not Applicable" label="Not Applicable"/></form:select></label>
							 
							   <label>Post Grad Specialization -<form:select path="testUserData.user.postGradDegreeSpec">
							  <form:option value="CSE" label="CSE" selected="true"/>
							   <form:option value="ISC" label="ISC"/>
							    <form:option value="IT" label="IT"/>
								 <form:option value="ECE" label="ECE"/>
							   <form:option value="TCE" label="TCE"/>
							    <form:option value="ETE" label="ETE"/>
							   <form:option value="Others" label="Others"/>
							    <form:option value="Not Applicable" label="Not Applicable"/></form:select></label>
                             
							 
							    <label>Post Graduation Year of Passing</label>
                             <form:input path="testUserData.user.postGradYearOfPassing" name="postGradYearOfPassing" id="postGradYearOfPassing" placeholder="Enter YYYY" required="true" />
							 
							  <label>Post Graduation Percentage</label>
                             <form:input path="testUserData.user.postGradPercentage" name="postGradPercentage" id="postGradPercentage" placeholder="Enter Percentage" required="true"/>
                             
							 
							 <label>Active Backlogs</label>
                             <form:input path="testUserData.user.activeBackLogs" name="activeBackLogs" id="activeBackLogs" placeholder="Backlogs if any" required="true"/>
							 
                            <input type="submit" value="SIGN IN">
                      
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="loginservices">
            <div class="col-md-12">
                <div class="item col-md-3">
                  <h3> <img src="images/serviceicon1.png">
                    Instructions</h3>


       		    <p>&#8226;  Test Results will be sent to you on Completion</p>
		    <p>&#8226; Click Submit for Submission of your Test </p>
		    <p>&#8226; System will auto Submit Test if Timer Expires	 </p>
                </div>
                <div class="item col-md-3">
                    <h3> <img src="images/serviceicon4.png">
                    Web Proctoring</h3>
                    <p><p>&#8226; Do not move mouse pointer to a different tab  </p>
		   <p>&#8226; Use F11 windows for Test if required   </p>
		   <p>&#8226; Non Compliance can result in your Test Declared Invalid   </p>
                </div>
                <div class="item col-md-3">
                   <h3> <img src="images/serviceicon3.png">
                    Tenants</h3>
                    <p>&#8226; Domain specific Users are advise to login using Corporate Credentials  </p>
		   <p>&#8226; Every User is directed to provide Login data for Individual Reporting   </p>
                </div>
                <div class="item col-md-3">
                    <h3> <img src="images/serviceicon2.png">
                    Yaksha</h3>
		          <p>&#8226; Multi Technology Assessments </p>
		          <p>&#8226; Test Cases Based Evaluation </p>
		          <p>&#8226; Weighted Adaptive Assessments	</p>
			
                </div>
            </div>
        </div>

        <div class="logincopyright">
            <div class="col-md-12">
                <p>Copyright 2018 IIHT. All Rights Reserved Privacy Policy For Enterprise Solutions</p>
            </div>
        </div>

        <!-- <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script> -->

    </body>
</html>
