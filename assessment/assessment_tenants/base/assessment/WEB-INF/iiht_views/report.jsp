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


        <div class="introsection">

            <div class="importantinstructions reports">
                <div class="container">
                    <div class="instructions">
                        <div class="leftside">
                            <div class="firstdiv">
                                <h5>Candidate Name</h5>
                                <h4>John Doe</h4>
                                
                                <h5>Test Title</h5>
                                <h4>Java SE8 Programmer</h4>
                                
                                <h5>Invited by</h5>
                                <h4>Jane Doe</h4>
                                
                                <h5>Test Date</h5>
                                <h4>July 27, 2018</h4>
                            </div>
                            
                            <div class="seconddiv">
                                <h5>Status</h5>
                                <h4>Completed</h4>
                            </div>
                            
                            <div class="thirdddiv">
                                <img src="images/msg.png">
                                <img src="images/hint.png">
                            </div>
                        </div>
                        <div class="rightside">
                            
                            <div class="scores">
                                <div class="col-md-4">
                                    <h4>Score</h4>
                                    <h3>68 out of 100</h3>
                                </div>
                                <div class="col-md-4">
                                    <h4>Result</h4>
                                    <h3>Pass</h3>
                                </div>
                                <div class="col-md-4">
                                    <h4>Percentage</h4>
                                    <h3>68%</h3>
                                </div>
                            </div>
                            
                            
                            <div class="progressbar">
                                <h3>Section Performance</h3>
                                <img src="images/u148.png">
                                <img src="images/u148.png">
                            </div>
                            
                        </div>
                    </div>
                    
                    <div class="copyright text-center">
                        <h4>1800 - 123 - 321 - 5 support@skillometer.com</h4>
                        <p>Copyrigh Â© 2018 IIHT. All Rights Reserved â Privacy Policy</p>
                    </div>
                    
                </div>
            </div>

        </div>

      
    </body>
</html>