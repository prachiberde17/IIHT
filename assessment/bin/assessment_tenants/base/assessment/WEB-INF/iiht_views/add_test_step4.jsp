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
	<script type="text/javascript" src="scripts/custom.js"></script>
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
    <div class="rightdiv settest step1" style="width: 100%;padding-right: 15px;height: 550px;">

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
            <div class="steps">
                <span>3</span>
                <label><img src="images/u1114.png">Add Candidates</label>
            </div>
            <div class="steps line">
                <img src="images/u1102.png">
            </div>
            <div class="steps active">
                <span>4</span>
                <label><img src="images/u1106.png">Send Invitation</label>
            </div>
        </div>
<%

Test test = (Test) request.getSession().getAttribute("test");

%>

        <div class="invitecandidates">
            <div class="left">
                 <label>Invited Candidates - <%= test.getUsers().size() %></label>
                <ul>
                     <c:forEach var="user" items="${selectedusers}">
                    <li>${user.firstName} ${user.lastName}</li>
                </c:forEach>
                </ul>
            </div>
            <div class="right">
               <h4>Test: <%= test.getTestName() %>. Click on Section below to see a preview</h4>
                <p>Category: <%= test.getQualifier1() %> / <%= test.getQualifier2() %> /<%= test.getQualifier3() %> &nbsp;&nbsp;   Skills: Java  &nbsp;&nbsp;   </p>

                <div class="accordiansections">
		<c:forEach var="section"  items="${test.sectionDtos}" >
                    <button class="accordion">${section.sectionName}</button>
                    <div class="panel">
		    <%
		    int count = 1;
		    %>
			<c:forEach var="ques" varStatus="status" items="${section.questions}" >
				<div class="title">
				    <span><%= count %></span>
				    <p>Question - ${ques.questionText}  </p>
				</div>
                        <div class="options">
                            <ul>
                                <li style="${ques.choice1 == null || ques.choice1.length() == 0? 'display: none;':''}">Choice 1: &nbsp;&nbsp;  ${ques.choice1}</li>
                                <li style="${ques.choice2 == null || ques.choice2.length() == 0? 'display: none;':''}">Choice 2: &nbsp;&nbsp;  ${ques.choice2}</li>
                                <li style="${quest.choice3 == null || quest.choice3.length() == 0? 'display: none;':''}">Choice 3: &nbsp;&nbsp;  ${ques.choice3}</li>
                                <li style="${ques.choice4 == null || ques.choice4.trim().length() == 0? 'display: none;':''}">Choice 4:  &nbsp;&nbsp;  ${ques.choice4}</li>
                                <li style="${ques.choice5 == null || ques.choice5.trim().length() == 0? 'display: none;':''}">Choice 5:  &nbsp;&nbsp;  ${ques.choice5}</li>
				<li style="${ques.choice6 == null || ques.choice6.length() == 0? 'display: none;':''}">Choice 6:  &nbsp;&nbsp;  ${ques.choice6}</li>
                            </ul>
                            Answer: ${ques.rightChoices} 
                        </div>
			<%
				count ++;
			%>
			</c:forEach>
		    
                        
			
			 
                    </div>
		      </c:forEach>           

                   

                </div>
		

            </div>
        </div>


    </div>

    <div class="nextbuttons">
    
        <a class="cancelbtn" href="testlist">Cancel...</a>
        <a class="backbtn backstep3" href="showUsers">Back</a>
        <a class="nextbtn" href="shareTestWithUsers">Send Invitation</a>
    </div>

</div>

<script>
    
</script>

<!-- /main contain -->

</div>
            </div>
        </div>
      
    </body>
</html>

