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


	<script>
	function goback(){
	window.location = "listUsers";
	}
	</script>
	
	<style>
	
	.rightsideU{
	    float: left;
	    width: 100%;
	}
	
	rightdivU .leftdivU{
	    float: left;
	    width: 70%;
	}
	.rightdivU .rightdivU{
	    width: 30%;
	    float: left;
	    background-color: #eef5fb;
	    padding-top: 10px;
	    padding-left: 10px;
	    padding-right: 10px;
	    /*padding-bottom: 50px;*/ 
	    padding-bottom: 0px; 
	}
	.rightdivU .rightdivU h3.heading{
	    font-size: 46px;
	    line-height: 20px;
	    color: rgb(51,51,51);
	    font-family: "Segoe UI";
	    font-weight: 300;
	    padding-bottom: 50px;
	}



.rightdivU .rightdivU .addqueform{
    float: left;
    width: 100%;
}
.rightdivU .rightdivU .addqueform .formfield{
    float: left;
    width: 100%;
    padding-top: 10px;
    padding-bottom: 10px;
}
.rightdivU .rightdivU .addqueform label{
    float: left;
    width: 100%;
    font-size: 15px;
    color: rgb(0,0,0);
    font-family: "Segoe UI";
    padding-bottom: 9px;
}
.rightdivU .rightdivU .addqueform textarea{
    width: 100%;
    height: 100px;
    border: 2px solid #8f9396;
    padding: 5px;
}
.rightdivU .rightdivU .addqueform input[type="text"],
.rightdivU .rightdivU .addqueform input[type="email"]{
    border: 2px solid #8f9396;
    padding: 5px;
    width: 50%;
}
.rightdivU .rightdivU .addqueform select{
    background-color: #fff;
    border: 2px solid #8f9396;
    padding: 5px;
    width: 45%;
    font-size: 13px;
    color: rgb(0,0,0);
    font-family: "Segoe UI";
}
.rightdivU .rightdivU .addqueform select.quequery{
    background-color: #bfc4ca;
    border: none;
}

.rightdivU .rightdivU .addqueform .selectoptions{
    width: 75%;
}
.rightdivU .rightdivU .addqueform .selectoptions span{
    font-size: 15px;
    color: rgb(0,0,0);
    font-family: "Segoe UI";
    padding-bottom: 15px;
}
.rightdivU .rightdivU .addqueform .selectoptions .option{
    float: left;
    width: 100%;
    padding-bottom: 9px;
}
.rightdivU .rightdivU .addqueform .selectoptions .option .choice{
    float: right;
}
.rightdivU .rightdivU .addqueform .selectoptions .option span{
    width: 10%;
    float: left;
}
.rightdivU .rightdivU .addqueform .selectoptions .option input[type="text"]{
    border: 2px solid #8f9396;
    padding: 5px;
    color: rgb(0,0,0);
    font-family: "Segoe UI";
    width: 60%;
}

.rightdivU .rightdivU .addqueform .selectoptions .option .choice input{
    margin-right: 50px;
}
.rightdivU .rightdivU .addqueform .selectoptions .option .choice a {
    float: right;
    padding: 1px 8px;
    background-color: #e54020;
    text-align: center;
    color: #fff;
    border-radius: 50%;
    font-weight: bold;
}

.rightdivU .rightdivU .addqueform .addanother{
    float: left;
    width: 100%;
}
.rightdivU .rightdivU .addqueform .addanother a{
    font-size: 18px;
    color: rgb(0,0,0);
    font-family: "Segoe UI";
    text-decoration: none;
}
.rightdivU .rightdivU .addqueform .addanother span {
    padding: 2px 8px;
    background-color: #7dba5b;
    text-align: center;
    color: #fff;
    border-radius: 50%;
    font-weight: bold;
    margin-right: 20px;
}
.rightdivU .rightdivU .addqueform .addimagevideo{
    padding-top: 20px;
}
.rightdivU .rightdivU .addqueform .addimagevideo a{
    background-color: #bfc4ca;
    padding: 5px 20px;
    font-size: 15px;
    color: rgb(0,0,0);
    font-family: "Segoe UI";
    float: left;
    margin-right: 8px;
    text-decoration: none;
}

.rightdivU .rightdivU .addqueform .savebtn input{
    background-color: #bfc4ca;
    padding: 5px 20px;
    border: none;
    margin-right: 15px;
}
.rightdivU .rightdivU .addqueform .savebtn .save{
    background-color: #bedbfd;
}

.rightdivU .leftdivU{
    float: left;
    width: 70%;
}

.questiontablelistU{
    float: left;
    width: 100%;
    padding-top: 5px;
     height: 720px;
    overflow-x: scroll !important;
}

.leftdivU .topmenu a{
    font-size: 20px;
    line-height: 20px;
    color: rgb(102,102,102);
    font-family: "Segoe UI" !important;
    /*font-weight: 300;*/
    padding: 0 10px;
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

                        <div class="rightdivU" >

                            <div class="leftdivU" >

                                <div class="topmenu text-right">
				<a href="javascript:showFileDialog();" id="uploadUsersLink">Bulk Import</a>
				
					
				<form id="fileFormUsers" method="POST" enctype="multipart/form-data" >
					<input type="file" name="fileFromUserForm" id="fileFromUserForm" style="display:none" />
				</form>
                                   
                                    <a href="javascript:notify('Information', 'Feature Coming Soon');"><i class="fa fa-arrow-left"></i></a>
                                        4/40
                                        <a href="javascript:notify('Information', 'Feature Coming Soon');"><i class="fa fa-arrow-right"></i></a>
                                </div>

                                <div class="questiontable" >
                                    <div class="questionheading">
                                        <div class="left">
                                            <h4>User Repository</h4>
                                        </div>
                                        <div class="right">
                                            <div class="searchdata">
                                                <input type="text" placeholder="Search a User" name="searchText" id="searchText">
                                            <i class="fa fa-search" id="search"></i>
                                            </div>

                                            <div class="filter">
                                                <a href="javascript:notify('Information', 'Feature coming soon')"><img src="images/ic_sort.png">Sort</a>
                                                <a href="javascript:notify('Information', 'Feature coming soon')"><img src="images/ic_filter.png">Filter</a>
                                            </div>

                                        </div>
                                    </div>


                                    <div class="questiontablelistU" >
                                    <table class="table" >
                                        <thead>
                                            <tr>
                                            
                                                <th>First Name</th>
						<th>Last Name</th>
                                                <th  style="white-space:nowrap;">Email</th>
                                                <th>Department</th>
                                                
                                            </tr>
                                        </thead>
                                        <tbody>
					<tbody>
						                     
						                       <c:forEach  items="${users}" var="user" >   
						                      	<tr>

												
						                      		<td>${user.firstName} </td>
						                      		<td> ${user.lastName}</td>
						                      		<td>${user.email}  </td>
						                      		<td>${user.department}   </td>
						                      		
						                      	</tr>
						                      	</c:forEach>   
						                      </tbody>
                                           
                                        </tbody>
                                    </table>
                                </div>

                                </div>

                            </div>	

                            <div class="rightdivU" >
                                <h4 class="heading">Add New User</h4>
                                <div class="addqueform" >
                                     <form name="userForm"  method="post" modelAttribute="usr" action="saveUser">
                                        <div class="formfield">
                                            <label>First Name</label>
                                            
					    <form:input path="usr.firstName" name="firstName" id="firstName" required="true"/>
                                        </div>

                                        <div class="formfield">
                                            <label>Last Name</label>
                                            
					    <form:input path="usr.lastName" name="lastName" id="lastName" required="true"/>
                                        </div>

                                        <div class="formfield">
                                            <label>Email</label>
                                            
					    <form:input path="usr.email" name="email" id="email" required="true"/>
                                        </div>
					
					<div class="formfield">
                                            <label>Password</label>
                                            
					    <form:password path="usr.password" name="password" id="password"  required="true"/>
                                        </div>

                                        <div class="formfield">
                                            <label>Mobile</label>
                                            
					    <form:input path="usr.mobileNumber" name="mobileNumber" id="mobileNumber" />
                                        </div>


                                        <div class="formfield">
                                            <label>Department</label>
                                            
					    <form:input path="usr.department" name="department" id="department" />
                                        </div>
					
					<div class="formfield">
                                            <label>User Group</label>
                                            
					    <form:input path="usr.groupOfUser" name="groupOfUser" id="groupOfUser" />
                                        </div>
                                        
                                        <div class="formfield">
                                            <label>User Grade</label>
                                            
					    <form:input path="usr.grade" name="grade" id="grade" />
                                        </div>


                                         <div class="formfield">
                                            <label>Is Internal User?</label>
                                            
					    <form:checkbox path="usr.internalUser" />
                                        </div>


                                        <div class="formfield savebtn">
                                            <input class="save" type="submit" value="Save User">
                                         <!--   <input type="submit" value="Save and add another"> -->
                                            <input type="button" value="Cancel" onClick="goback()">
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
            /* off-canvas sidebar toggle */
            $('[data-toggle=offcanvas]').click(function () {
                $('.row-offcanvas').toggleClass('active');
                $('.collapse').toggleClass('in').toggleClass('hidden-xs').toggleClass('visible-xs');
            });
        </script>

        <script type="text/javascript">
            $(document).on('click', '#addanother', function () {
                var alphabet = nextString($("#maindivforaddmore").children().last().children().first().text());
                $("#maindivforaddmore").append("<div class='option'><span>" + alphabet + "</span><input type='text'><div class='choice'><input name='option' type='radio'><a href='javascript:void(0);' class='removenewdiv'>-</a></div></div>");
            });

            $(document).on('click', '.removenewdiv', function () {
                $(this).parent().parent().remove();
            });

            function nextString(str) {
                if (!str)
                    return 'A'  // return 'A' if str is empty or null

                let tail = ''
                let
                i = str.length - 1
                let
                char = str[i]
                // find the index of the first character from the right that is not a 'Z'
                while (char === 'Z' && i > 0) {
                    i--
                    char = str[i]
                    tail = 'A' + tail   // tail contains a string of 'A'
                }
                if (char === 'Z')   // the string was made only of 'Z'
                    return 'AA' + tail
                // increment the character that was not a 'Z'
                return str.slice(0, i) + String.fromCharCode(char.charCodeAt(0) + 1) + tail
            }

        </script>

        <script>
	
	
	 var isXlsx = function(name) {
	    return name.match(/xlsx$/i)
	    };
	    
	    $("#btnfile").click(function () {
	    $("#uploadfile").click();
	});
	
	function showFileDialog(){
	$("#fileFromUserForm").click();
	}
	
	
	 $(document).ready(function() {
	    
	    var file = $('[name="fileFromUserForm"]');
	    var imgContainer = $('#imgContainer');
	    
	   
		
		
		
	var fileU = document.getElementById('fileFromUserForm');
	fileU.addEventListener("change", function () {
		  if (fileU.files.length > 0) {
		   var filename = $.trim(file.val());
		
		if (!(isXlsx(filename) )) {
		    notify('Error', 'Please select an xlsx file to upload');
		    return;
		}
		
		$.ajax({
		   xhr: function() {
		    var xhr = new window.XMLHttpRequest();

		    return xhr;
		  },
		   url: 'uploadUsers',
		    type: "POST",
		    data: new FormData(document.getElementById("fileFormUsers")),
		    enctype: 'multipart/form-data',
		    processData: false,
		    contentType: false
		  }).done(function(data) {
		    notify('Success', 'File Upload Successful');
		   
		  }).fail(function(jqXHR, textStatus) {
		      notify('Failure', 'File Upload Failed. Please contact Administrator');
		  });
		  document.getElementById('fileQuestions').value = null;
		    return;
		  }
		 
		});
	  
	});
	
	
	
	
          

	    
	      $('#search').on('click',function(){
	    var text = document.getElementById("searchText").value;
		if(text.length != 0){
		window.location="searchUsrs?searchText="+text;
		}
	    });
	    
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
