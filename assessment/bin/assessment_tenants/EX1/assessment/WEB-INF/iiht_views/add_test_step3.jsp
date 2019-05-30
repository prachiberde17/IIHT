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
                    <jsp:include page="side.jsp" /> 
                    <!-- /sidebar -->
                    <!-- main right col -->
                    <div class="column col-sm-10 col-xs-11" id="main">
<!-- main contain -->
	<!-- main contain -->
<div class="rightside">
    <div class="rightdiv settest" style="width: 100%;padding-right: 15px;height: 550px;">


        <div class="teststeps">
            <div class="steps">
                <span>1</span>
                <label><img src="images/u1107.png">Set your test</label>
            </div>
            <div class="steps line">
                <img src="images/u1102.png">
            </div>
            <div class="steps">
                <span>2</span>
                <label><img src="images/u1105.png">Add Questions</label>
            </div>
            <div class="steps line">
                <img src="images/u1102.png">
            </div>
            <div class="steps active">
                <span>3</span>
                <label><img src="images/u1114.png">Add Candidates</label>
            </div>
            <div class="steps line">
                <img src="images/u1102.png">
            </div>
            <div class="steps">
                <span>4</span>
                <label><img src="images/u1106.png">Send Invitation</label>
            </div>
        </div>

        <!--<h4 class="heading">Java for Freshers...</h4>-->

        <div class="questiontable step3">
            <div class="questionheading">
                <div class="right" style="float: left;width: 50%;">
                    <div class="searchdata">
                        <input type="text" placeholder="Search a User" name="searchText" id="searchText" style="width : 100%">
                                 <i class="fa fa-search" id="search"></i>
                    </div>
                    
                </div>
               <div class="queselected">
			<a href="showUsers" id="showAllUs" style="color:#492a26"> Show All Users |</a>
			<a href="javascript:showSelected();" id="showSelected" style="color:#228B22"> Show Selected |</a>
			
			<a href="removeAllUsers" style="color:red">Clear All</a>
                        </div>
            </div>
            <div class="questiontablelist" style="height: 550px;overflow-x:auto;padding-top: 0;">
                <table class="table">
                    <thead>
                        <tr>
                            
                            <th>Name</th>
                            <th>Email</th>
                            <th>Department</th>
                            <th>Group</th>
                            <th>Grade</th>
                        </tr>
                    </thead>
                    <tbody>
						    					       
						       <c:forEach  items="${users}" var="us" >   
							<tr bgcolor="${us.selected? '#33FFF9':'transparent'}">
								
										
								<td>${us.firstName} ${us.lastName} </td>
								
								<td> ${us.email}</td>
								<td>${us.department}   </td>
								
								<td>${us.groupOfUser}</td>
								
								<td style="${us.selected? 'display: none;':''}"><a href="javascript:addU('${us.id}');">Click to Add</a> </td>
								<td style="${us.selected? '':'display: none;'}"><a href="javascript:removeU('${us.id}');">Click to Remove</a></td>
							</tr>
							
							</c:forEach>   
						      </tbody>
			   
			</tbody>
                </table>


                <div class="step3userinfo" style="display: none;">
                    <div class="addqueform">
                        <form>
                            <div class="formfield">
                                <label>First Name</label>
                                <input type="text">
                            </div>
                            <div class="formfield">
                                <label>Last Name</label>
                                <input type="text">
                            </div>
                            <div class="formfield">
                                <label>Email Address</label>
                                <input type="email">
                            </div>
                            <div class="formfield">
                                <label>Mobile Number</label>
                                <input type="text">
                            </div>
                            <div class="formfield">
                                <label>Department</label>
                                <select class="quequery">
                                    <option>Select Department</option>
                                </select>
                            </div>
                            <div class="formfield">
                                <label>Group</label>
                                <select class="quequery">
                                    <option>Select Group</option>
                                </select>
                            </div>
                            <div class="formfield">
                                <label>Grade</label>
                                <select class="quequery">
                                    <option>Select Grade</option>
                                </select>
                            </div>
                        </form>
                    </div>
                </div>

            </div>

        </div>


    </div>

    <div class="nextbuttons">
        <a class="cancelbtn" href="testlist">Cancel</a>
        <a class="backbtn backstep2" href="addteststep2">Back</a>
        <a class="nextbtn add_test_step4" href="addteststep4">Next</a>
    </div>

</div>
<!-- /main contain -->

 </div>
            </div>
        </div>
	
	<script>
	$('#search').on('click',function(){
	    var text = document.getElementById("searchText").value;
		if(text.length != 0){
		window.location="searchUsers?searchText="+text;
		}
	    });
	
	function addU(uid){
	
			    
		window.location = "addUserToTest?userId="+uid;
		    
	}

function removeU(uid){
	window.location = "removeUserToTest?userId="+uid;
	    
}

function showSelected(){
	
	window.location = "showSelectedUsers";
}
	
	</script>

    </body>
</html>

