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
	<script type="text/javascript">
	window.history.forward();
	function noBack() { window.history.forward(); }
</script>
		<script>
window.location.hash="no-back-button";
window.location.hash="Again-No-back-button";//again because google chrome don't insert first hash into history
window.onhashchange=function(){window.location.hash="no-back-button";}
</script>
    </head>
<!------ Include the above in your HEAD tag ---------->
<body onload="noBack();">
<div class="container">
	
	<div class="jumbotron">
	 <h2 style="color:blue">Thanks ${studentTestForm.firstName} ${studentTestForm.lastName} - You have completed the test</h2>
    <h3>Your completed the test in ${studentTestForm.noOfAttempts == null || studentTestForm.noOfAttempts.trim().length() == 0?"1":studentTestForm.noOfAttempts} ${studentTestForm.noOfAttempts == null || studentTestForm.noOfAttempts.trim().length() == 0?"attempt":"attempts"}</h3>
    <h3>Your results have been shared by email to the Test Administrator - ${studentTestForm.testCreatedBy}</h3>
	
	<c:if test="${showResults}">
		<p style="font-size:17px;line-height:24px;margin:0 0 16px">
                     Total Questions - ${TOTAL_QUESTIONS} <br/>
					 Total  Marks - ${TOTAL_MARKS} <br/>
					 Pass Percentage - ${PASS_PERCENTAGE} <br/>
					 Result Percentage - ${RESULT_PERCENTAGE} <br/>
					 Status - ${STATUS} <br/>
					 Topic Wise Performance - See below 
		<table width="100%" style="border-collapse:collapse;border: 1px solid black">
                                          <tbody>
					  <thead>
                                            <tr style="border-collapse:collapse;border: 1px solid black">
						<th style="border: 1px solid black">Section Name</th>
						<th style="border: 1px solid black">Percentage Got </th>
					    </tr>
					    </thead>
			 ${rows}
			 
		  </tbody>
	   </table>
		</p>
	</c:if>
    
    </div>
<div class="container">
  <div class="page-header" style="background-color:#DAA300;color:#fff">
    <h3>If you want to try this test again <a href="mailto:jatin.sutaria@thev2technologies.com">Write to Us</a></h3>      
  </div>
  <p>If you want to provide any feedback on the test  <a href="mailto:feedback@iiht.com">Write to Us</a></p>      
   
</div>

    
</div>

</body>
</html>