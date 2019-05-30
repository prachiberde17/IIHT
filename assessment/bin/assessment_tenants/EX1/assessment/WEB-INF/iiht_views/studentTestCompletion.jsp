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
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
<!------ Include the above in your HEAD tag ---------->
<body>
<div class="container">
	
	<div class="jumbotron">
	 <h2 style="color:blue">Thanks ${studentTestForm.firstName} ${studentTestForm.lastName} - You have completed the test</h2>
    <h3>Your completed the test in ${studentTestForm.noOfAttempts} attempts</h3>
    <h3>Your results have been shared by email to the Test Administrator - ${studentTestForm.testCreatedBy}</h3>
    
    </div>
<div class="container">
  <div class="page-header" style="background-color:#DAA300;color:#fff">
    <h3>If you want to try this test again Write to <a href="mailto:jatin.sutaria@thev2technologies.com">Click here</a></h3>      
  </div>
  <p>If you want to provide any feedback on the test Write to <a href="mailto:feedback@iiht.com">Click here</a></p>      
   
</div>

    
</div>

</body>
</html>