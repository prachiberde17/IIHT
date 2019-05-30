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
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
	
	 <link href="css/font-awesome.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="maincontainer">            
            <div class="col-md-8 col-sm-12 leftsideimg">
                <img src="images/rsz_new-books.jpg" alt="" class="mainimg">
            </div>
            <div class="col-md-4 col-sm-12">
                <div class="loginform" style="padding-top: 80px;">
                    <div class="logodiv text-center">
                        <img src="images/Logo.png" alt="logo" class="logo">
                    </div>
                    <div class="formdiv">
                        
			 <form name="testloginform" class="userform" method="post" modelAttribute="testUserData" action="publicTestAuthenticate">
                            <div class="form-group">
                                <label>Username</label>
                                
				 <form:input  type="email" path="testUserData.user.email" name="email" id="username" cssClass="form-control" placeholder="Email number" required="true"/>
                            </div>
                            <div class="form-group">
                                <label>First Name</label>
                                <div class="passinputdiv">
                                    
				   <form:input path="testUserData.user.firstName" name="firstName" id="firstName" cssClass="form-control" placeholder="First Name" required="true"/>
                                 
                                </div>
                            </div>
			    <div class="form-group">
                                <label>Last Name</label>
                                <div class="passinputdiv">
                                    
				   <form:input path="testUserData.user.lastName" name="lastName" id="lastName" cssClass="form-control" placeholder="Last Name" required="true"/>
                                 
                                </div>
                            </div>
			    <div class="form-group">
                                <label>Test Name</label>
                                <div class="passinputdiv">
                                    
				   <form:input path="testUserData.testName" name="testName" id="testName" cssClass="form-control"  required="true" disabled="true"/>
                                 <form:hidden path="testUserData.testId" />
				 <form:hidden path="testUserData.user.companyName" />
				 <form:hidden path="testUserData.user.companyId" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Company</label>
                                
				 <form:input path="testUserData.user.companyName" name="companyName" id="companyName" cssClass="form-control" disabled="true"/>
                            </div>
                            <div class="form-group">
                                <button type="submit" name="submit" class="loginbtn">Login</button>
                            </div>
                           
                        </form>
                    </div>
                </div>
            </div>
        </div>


    </body>
</html>