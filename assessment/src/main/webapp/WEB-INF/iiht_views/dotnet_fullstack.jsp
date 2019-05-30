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
        <link href="https://fonts.googleapis.com/css?family=Segoe+UI" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="css/font-awesome.css" rel="stylesheet" type="text/css">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link href="css/responsive.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
    </head>

    <body>

        <div class="maincontainer">            

            <div class="wrapper">
                <div class="row row-offcanvas row-offcanvas-left">
                    <!-- sidebar -->
		   <jsp:include page="side_reviewer.jsp" /> 
                 
                    <!-- /sidebar -->

                    <!-- main right col -->
                    <div class="column col-sm-10 col-xs-11" id="main">

                        <div class="rightside">
                            <div class="topmenu text-right">
              
				
				<a href="signoff">Sign Off</a>
			
                            </div>


                            <div class="questiontable">
                                <div class="questionheading">
                                    <div class="left">
                                        <h3>Question Bank</h3>
                                    </div>
                                    <div class="right">
                                        <div class="searchdata">
                                            <input type="text" placeholder="Search a question" name="searchText" id="searchText">
                                            <i class="fa fa-search" id="search"></i>
                                        </div>

                                        <div class="filter">
                                            <a href="javascript:notify('Information', 'Feature coming soon')"><img src="images/ic_sort.png">Sort</a>
                                            <a href="javascript:notify('Information', 'Feature coming soon')"><img src="images/ic_filter.png">Filter</a>
                                        </div>

                                    </div>
                                </div>


                                <div class="questiontablelist" style="overflow-x:scroll;height:500px;">
                                    <table class="table">
                                        <thead>
                                            <tr>
						<th>No</th>
                                                <th>Test Name</th>
                                                <th  style="white-space:nowrap;">Section Name</th>
                                                <th>Test Giver Full Name</th>
												<th>Test Giver Email</th>
                                                <th  style="white-space:nowrap;">Date of Submission</th>
						<th  style="white-space:nowrap;">Review</th>
						
                                            </tr>
                                        </thead>
                                        <tbody>
					<tbody>
						                     
						                       <c:forEach  items="${instances}" var="ins" varStatus="loop">   
						                      	<tr>

										<td>${loop.count}</td>		

												
						                      		<td>${ins.testName}</td>
										
						                      		<td> ${ins.sectionName}</td>
						                      		<td> ${ins.uerFullName}</td>
						                      		<td> ${ins.user}</td>
													
													<td> ${ins.workspaceDateOfSubmission}</td>
						                      		<td><a  href="review?qid=${ins.id}">Click </a>   </td>
						                      		
						                      	</tr>
						                      	</c:forEach>   
						                      </tbody>
                                           
                                        </tbody>
                                    </table>
                                </div>

                            </div>

                        </div>

                    </div>
                    <!-- /main -->
                </div>
            </div>

        </div>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>



        <script>

		function review(id) {
			window.location = 'review?qmsid='+id;
		}
	
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
		
		function confirm(id) {
           (new PNotify({
		    title: 'Confirmation Needed',
		    text: 'Are you sure? Do you really want to delete this Q?',
		    icon: 'glyphicon glyphicon-question-sign',
		    hide: false,
		    confirm: {
			confirm: true
		    },
		    buttons: {
			closer: false,
			sticker: false
		    },
		    history: {
			history: false
		    }
		})).get().on('pnotify.confirm', function() {
		    window.location = "removeQuestionFromList?qid="+id;
		}).on('pnotify.cancel', function() {
		   
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
