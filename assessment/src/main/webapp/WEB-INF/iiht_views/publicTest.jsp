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
                            <li><i class="fa fa-phone"></i>1800-123-321-5</li>
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
                            <h3>Creation timelines for the standard lorem ipsum</h3>
                            <p>McClintock's eye for detail certainly helped narrow the whereabouts of lorem ipsum's origin, 
                                however, the “how and when” still remain something of a mystery, with competing theories and timelines.</p>
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
                            <input type="submit" value="SIGN IN">
                      
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="loginservices">
            <div class="col-md-12">
                <div class="item col-md-3">
                    <img src="images/serviceicon1.png">
                    <h3>Lorem ipsum</h3>
                    <p>McClintock's eye for detail certainly helped narrow the whereabouts of lorem	</p>
                </div>
                <div class="item col-md-3">
                    <img src="images/serviceicon2.png">
                    <h3>Color sit amet</h3>
                    <p>McClintock's eye for detail certainly helped narrow the whereabouts of lorem	</p>
                </div>
                <div class="item col-md-3">
                    <img src="images/serviceicon3.png">
                    <h3>Consectetur</h3>
                    <p>McClintock's eye for detail certainly helped narrow the whereabouts of lorem	</p>
                </div>
                <div class="item col-md-3">
                    <img src="images/serviceicon4.png">
                    <h3>Eiusmod tempor</h3>
                    <p>McClintock's eye for detail certainly helped narrow the whereabouts of lorem	</p>
                </div>
            </div>
        </div>

        <div class="logincopyright">
            <div class="col-md-12">
                <p>Copyrigh © 2018 IIHT. All Rights Reserved – Privacy Policy For enterprise solutions</p>
            </div>
        </div>

        <!-- <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script> -->

    </body>
</html>
