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
	 
	 <style>
	 q {
	  display: inline;
	}
	 
	q:before {
	  content: open-quote;
	}
	 
	q:after {
	  content: close-quote;
	}
	 /* The Modal (background) */
		.modal {
		  display: none; /* Hidden by default */
		  position: fixed; /* Stay in place */
		  z-index: 1; /* Sit on top */
		  left: 0;
		  top: 0;
		  width: 100%; /* Full width */
		  height: 100%; /* Full height */
		  overflow: auto; /* Enable scroll if needed */
		  background-color: rgb(0,0,0); /* Fallback color */
		  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
		}

		/* Modal Content/Box */
		.modal-content {
		  background-color: #fefefe;
		  margin: 15% auto; /* 15% from the top and centered */
		  padding: 20px;
		  border: 1px solid #888;
		  width: 80%; /* Could be more or less, depending on screen size */
		}

		/* The Close Button */
		.close {
		  color: #aaa;
		  float: right;
		  font-size: 28px;
		  font-weight: bold;
		}

		.close:hover,
		.close:focus {
		  color: black;
		  text-decoration: none;
		  cursor: pointer;
	 </style>
    </head>

    <body>

        <div class="maincontainer">            

            <div class="wrapper">
                <div class="row row-offcanvas row-offcanvas-left">
                    <!-- sidebar -->
		   <jsp:include page="side.jsp" /> 
                 
                    <!-- /sidebar -->

                   <div class="column col-sm-10 col-xs-11" id="main">
                        
			    
			   
                            <div class="questiontable">
                                <div class="questionheading">
                                    <div class="left">
                                        <h3>Find Candidates by Skill Tags</h3>
                                    </div>
                                    <div class="right">
                                       
                                        
					<div class="filter">
                                          
					   
                                        </div>
                                    </div>
                                </div>
                                <div class="questiontablelist" style="overflow-x:scroll;overflow-y:auto;">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th><b>No</b></th>
												<th><b>Skill Level 1</b></th>
												<th><b>Skill Level 2</b></th>
												<th><b>Skill Level 3</b></th>
												<th><b>Skill Level 4</b></th>
												<th><b>Skill Level 5</b></th>
												
												
						
						
                                            </tr>
                                        </thead>
                                         <tbody>
					<tbody>
						                     
						                       <c:forEach  items="${skills}" var="skill"  varStatus="loop">   
						                      	<tr>
										<td>${loop.count}</td>
												
						                      		<td><a  href="downloadUserReportsForSkill?skillName=${skill.qualifier1}">${skill.qualifier1} </a> </td>
										
						                      		<td>

														<c:choose>
															<c:when test="${skill.qualifier2.length() > 0}">
																<a  href="downloadUserReportsForSkill?skillName=${skill.qualifier2}">${skill.qualifier2} </a> 
															</c:when>    
															<c:otherwise>
																NA
																
															</c:otherwise>
														</c:choose>
														
														
													</td>
													<td>
														<c:choose>
															<c:when test="${skill.qualifier3.length() > 0}">
																<a  href="downloadUserReportsForSkill?skillName=${skill.qualifier3}">${skill.qualifier3} </a> 
															</c:when>    
															<c:otherwise>
																NA
																
															</c:otherwise>
														</c:choose>
													</td>
													<td><c:choose>
															<c:when test="${skill.qualifier4.length() > 0}">
																<a  href="downloadUserReportsForSkill?skillName=${skill.qualifier4}">${skill.qualifier4} </a> 
															</c:when>    
															<c:otherwise>
																NA
																
															</c:otherwise>
														</c:choose>
													</td>
										            <td>
														<c:choose>
															<c:when test="${skill.qualifier5.length() > 0}">
																<a  href="downloadUserReportsForSkill?skillName=${skill.qualifier2}">${skill.qualifier5} </a> 
															</c:when>    
															<c:otherwise>
																NA
																
															</c:otherwise>
														</c:choose>
													</td>
						                      		
										
								
						                      	</tr>
						                      	</c:forEach>   
						                      </tbody>
                                           
                                        </tbody>
                                    </table>
                                </div>
                                <div>&nbsp;</div>
                                    <div>&nbsp;</div>
                                    <div>&nbsp;</div>
                                    <div>&nbsp;</div>
                                    <div>&nbsp;</div>
                            </div>
                        </div>
                    </div>
                    <!-- /main -->
                </div>
            </div>
        </div>
	
		<dialog id="dial"> 
		<code id="codeWin">#include<iostream.h></code>
		<button id="close">Close Dialog</button> 
		
		</dialog>
		
		<!-- The Modal -->
		<div id="myModal" class="modal">

		  <!-- Modal content -->
		  <div class="modal-content">
			<span class="close">&times;</span>
			<code id="code">Some text in the Modal..</code>
		  </div>

		</div>
	 
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script> 
			// Get the <span> element that closes the modal
			var span = document.getElementsByClassName("close")[0];
			
			// When the user clicks on <span> (x), close the modal
			span.onclick = function() {
			var modal = document.getElementById('myModal');
			  modal.style.display = "none";
			}
		
			function showCode(code){
			var modal = document.getElementById('myModal');
			document.getElementById("code").innerHTML = code;
			 modal.style.display = "block";
			}
		
		</script>


      
	<script>
	
	
	
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
