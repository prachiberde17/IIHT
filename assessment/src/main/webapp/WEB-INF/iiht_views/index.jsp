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

        <div class="maincontainer">            
            <div class="col-md-8 col-sm-12">
                <img src="images/mainimg.png" alt="" class="mainimg">
            </div>
            <div class="col-md-4 col-sm-12">
                <div class="loginform">
                    <div class="logodiv text-center">
                        <img src="images/Logo.png" alt="logo" class="logo">
                    </div>

                    <div class="formdiv">
                        <form name="userloginform" class="userform" method="post" modelAttribute="user" action="authenticate">
                            <div class="form-group">
                                <label>Username</label>
<!--                                 <input type="text" name="username" class="form-control" placeholder="email/mobile number"> -->
                                <form:input type="email" path="user.email" name="email" id="username" cssClass="form-control" required="true"/>
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <div class="passinputdiv">
<!--                                     <input type="password" name="userpassword" id="userpassword" class="form-control" placeholder="password"> -->
                                     <form:password path="user.password" name="password" id="password" cssClass="form-control" required="true"/>
                                    <i class="fa fa-eye" aria-hidden="true" onclick="myFunction()"></i>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Company</label>
<!--                                 <input type="text" name="username" class="form-control" placeholder="Enter your company name"> -->
                                  <form:input  path="user.companyName" name="companyName" id="companyName" cssClass="form-control" required="true"/>
                            </div>
                            <div class="form-group">
                                <button type="submit" name="submit" class="loginbtn">Login</button>
                            </div>
                            <div class="form-group text-center">
                                <a href="#" class="forgotpass">Forgot Password?</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>


      
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript">
            function myFunction() {
                var x = document.getElementById("userpassword");
                if (x.type === "password") {
                    x.type = "text";
                } else {
                    x.type = "password";
                }
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
