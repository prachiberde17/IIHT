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
	<script>
	function goback(){
	window.location = "goback";
	}
	</script>
	
	<style>
	.rightside .rightdivTenant{
	    width: 100%;
	    float: left;
	    background-color: #eef5fb;
	    padding-top: 12px;
	    padding-left: 12px;
	    padding-right: 50px;
	    /*padding-bottom: 50px;*/ 
	    padding-bottom: 0px; 
	}
	.rightside .rightdivTenant h3.heading{
	    font-size: 46px;
	    line-height: 22px;
	    color: rgb(51,51,51);
	    font-family: "Segoe UI";
	    font-weight: 400;
	    padding-bottom: 50px;
	}

	.rightside .rightdivTenant .addqueform{
	    float: left;
	    width: 100%;
	}
	.rightside .rightdivTenant .addqueform .formfield{
	    float: left;
	    width: 100%;
	    padding-top: 10px;
	    padding-bottom: 10px;
	}
	.rightside .rightdivTenant .addqueform label{
	    float: left;
	    width: 100%;
	    font-size: 12px;
	    color: rgb(0,0,0);
	    font-family: "Segoe UI";
	    padding-bottom: 9px;
	}
	
	.rightside .rightdivTenant .addqueform select{
	    background-color: #fff;
	    border: 2px solid #8f9396;
	    padding: 5px;
	    width: 45%;
	    font-size: 13px;
	    color: rgb(0,0,0);
	    font-family: "Segoe UI";
	}
	.rightside .rightdivTenant .addqueform select.quequery{
	    background-color: #bfc4ca;
	    border: none;
	}

	.rightside .rightdivTenant .addqueform .selectoptions{
	    width: 75%;
	}
	.rightside .rightdivTenant .addqueform .selectoptions span{
	    font-size: 15px;
	    color: rgb(0,0,0);
	    font-family: "Segoe UI";
	    padding-bottom: 15px;
	}
	.rightside .rightdivTenant .addqueform .selectoptions .option{
	    float: left;
	    width: 100%;
	    padding-bottom: 9px;
	}
	.rightside .rightdivTenant .addqueform .selectoptions .option .choice{
	    float: right;
	}
	.rightside .rightdivTenant .addqueform .selectoptions .option span{
	    width: 10%;
	    float: left;
	}
	.rightside .rightdivTenant .addqueform .selectoptions .option input[type="text"]{
	    border: 2px solid #8f9396;
	    padding: 5px;
	    color: rgb(0,0,0);
	    font-family: "Segoe UI";
	    width: 60%;
	}

	</style>
	
    </head>
    <body>

        <div class="maincontainer">            

            <div class="wrapper">
                <div class="row row-offcanvas row-offcanvas-left">
                    <!-- sidebar -->
                   <jsp:include page="side.jsp" /> 
                    <!-- /sidebar -->

                    <!-- main right col -->
                    <div class="column col-sm-10 col-xs-11" id="main" style="overflow-x:scroll;overflow-y:scroll;">

                        <div class="rightside" >

                            

                            <div class="rightdivTenant" >
                                <h2class="heading">Create\Update Schedule</h2>
                                <div class="addqueform" >
                                     <form name="scheduleForm"  method="post" modelAttribute="schedule" action="saveTestSchedule">
                                       

									<form:hidden path="schedule.scheduleId" />
					
					<div class="formfield">
                                            <div class="selectoptions">
                                             
	
                                                <div id="maindivforaddmore">
                                                    <div class="option">
                                                   <label>Select Test</label>
                                            
													<form:select path="schedule.testId">
														<form:options items="${tests}" itemValue="id" itemLabel="testName"/>
													</form:select>
                                                    </div>
													
													 <div class="option">
                                                   <label>Select Users</label>
													<form:select multiple="true" path="schedule.users">
														 <c:forEach var="user" items="${users}">
													<form:option value="${user.email}"><c:out value="${user.firstName} ${user.lastName}"/></form:option>
														</c:forEach>
													</form:select>
													
                                                    </div>
                                                    <div class="option">
                                                        <label>Select year</label>
                                                       <select>
														  <option value="2019" selected>2019</option>
														
														</select>
                                                    </div>
                                                    <div class="option">
                                                        <label>Select Month</label>
															<form:select path="schedule.month">
															<form:option value="1" label="Jan"/>
															<form:option value="2" label="Feb"/>
															<form:option value="3" label="Mar"/>
															<form:option value="4" label="Aprl"/>
															<form:option value="5" label="May"/>
															
															<form:option value="6" label="Jun"/>
															<form:option value="7" label="Jul"/>
															<form:option value="8" label="Aug"/>
															<form:option value="9" label="Sep"/>
															<form:option value="10" label="Oct"/>
															
															<form:option value="11" label="Nov"/>
															<form:option value="12" label="Dec"/>
														</form:select>
														
                                                    </div>
                                                    <div class="option">
                                                         <label>Seelct Date</label>
														<form:select path="schedule.date">
																<form:option value="1" label="1"/>
																<form:option value="2" label="2"/>
																<form:option value="3" label="3"/>
																<form:option value="4" label="4"/>
																<form:option value="5" label="5"/>
																
																<form:option value="6" label="6"/>
																<form:option value="7" label="7"/>
																<form:option value="8" label="8"/>
																<form:option value="9" label="9"/>
																<form:option value="10" label="10"/>
																
																<form:option value="11" label="11"/>
																<form:option value="12" label="12"/>
																<form:option value="13" label="13"/>
																<form:option value="14" label="14"/>
																<form:option value="15" label="15"/>
																
																<form:option value="16" label="16"/>
																<form:option value="17" label="17"/>
																<form:option value="18" label="18"/>
																<form:option value="19" label="19"/>
																<form:option value="20" label="20"/>
																
																<form:option value="21" label="21"/>
																<form:option value="22" label="22"/>
																<form:option value="23" label="23"/>
																<form:option value="24" label="24"/>
																<form:option value="25" label="25"/>
																
																<form:option value="26" label="26"/>
																<form:option value="27" label="27"/>
																<form:option value="28" label="28"/>
																<form:option value="29" label="29"/>
																<form:option value="30" label="30"/>
																<form:option value="31" label="31"/>
														</form:select>
															
                                                    </div>
													<div class="option">
														<label>Select Hour</label>
															<form:select path="schedule.hours">
																<form:option value="0" label="0"/>
																<form:option value="1" label="1"/>
																<form:option value="2" label="2"/>
																<form:option value="3" label="3"/>
																<form:option value="4" label="4"/>
																<form:option value="5" label="5"/>
																
																<form:option value="6" label="6"/>
																<form:option value="7" label="7"/>
																<form:option value="8" label="8"/>
																<form:option value="9" label="9"/>
																<form:option value="10" label="10"/>
																
																<form:option value="11" label="11"/>
																<form:option value="12" label="12"/>
																
																<form:option value="13" label="13"/>
																<form:option value="14" label="14"/>
																<form:option value="15" label="15"/>
																<form:option value="16" label="16"/>
																<form:option value="17" label="17"/>
																<form:option value="18" label="18"/>
																
																<form:option value="19" label="19"/>
																<form:option value="20" label="20"/>
																<form:option value="21" label="21"/>
																<form:option value="22" label="22"/>
																<form:option value="23" label="23"/>
															
															
														</form:select>   
														 
														</div>
														 <div class="option">
                                                      
                                            
															<label>Select Minutes</label>
															<form:select path="schedule.minutes">
																<form:option value="0" label="0"/>
																<form:option value="2" label="2"/>
																<form:option value="4" label="4"/>
																<form:option value="6" label="6"/>
																<form:option value="8" label="8"/>
																<form:option value="10" label="10"/>
																<form:option value="12" label="12"/>
																
																<form:option value="15" label="15"/>
																
																<form:option value="18" label="18"/>
																<form:option value="20" label="20"/>
																<form:option value="23" label="23"/>
																<form:option value="25" label="25"/>
																<form:option value="28" label="28"/>
																
																<form:option value="30" label="30"/>
																
																<form:option value="33" label="33"/>
																<form:option value="36" label="36"/>
																<form:option value="40" label="40"/>
																<form:option value="42" label="42"/>
																
																
																<form:option value="45" label="45"/>
																<form:option value="48" label="48"/>
																<form:option value="50" label="50"/>
																<form:option value="52" label="52"/>
																<form:option value="54" label="54"/>
																<form:option value="56" label="56"/>
																<form:option value="59" label="59"/>
																
																
																
															</form:select>   
															 
														</div>
                                                    <div class="option">
                                                      
                                            
														<label>Select Seconds</label>
														<form:select path="schedule.seconds">
															<form:option value="0" label="0"/>
															<form:option value="15" label="15"/>
															<form:option value="30" label="30"/>
															<form:option value="45" label="45"/>
															<form:option value="59" label="59"/>
															
															
															
														</form:select>
														  
                                                    </div>
						    
						  
						     <div class="formfield savebtn">
						    <input class="save" type="submit" value="Save Schedule">
						
						    <input type="button" value="Go Back" onClick="location.href='showAllSchedules';">
						</div>
                                                </div>
                                            </div>
				
                                            
                                        </div>

                                       
					
					

                                    </form>
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
