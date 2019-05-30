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
                                <h2class="heading">${label} Skill</h2>
                                <div class="addqueform" >
                                     <form name="tenantForm"  method="post" modelAttribute="skill" action="saveSkill">
                                       

                                       
					
					<div class="formfield">
                                            <div class="selectoptions">
                                             

                                                <div id="maindivforaddmore">
                                                    
                                                    <div class="option">
                                                        <label>Skill Name</label>
                                                       <form:input path="skill.skillName" name="skillName" id="skillName" required="true"/>
                                                    </div>
                                                    <div class="option">
                                                        <label>Skill Level</label>
                                            
														<form:select path="skill.level">
							  
														 <form:options items="${levels}" itemValue="level" itemLabel="level" />
													</form:select>
													
													<form:hidden path="skill.id" />
                                                    </div>
                                                   
						    
						     <div class="formfield savebtn">
						    <input class="save" type="submit" value="${label} Skill">
						
						    <input type="button" value="Cancel" onClick="location.href='skills';">
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
