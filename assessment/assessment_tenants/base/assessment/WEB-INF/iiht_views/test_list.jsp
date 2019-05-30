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
    </head>

    <body>

        <div class="maincontainer">            

            <div class="wrapper">
                <div class="row row-offcanvas row-offcanvas-left">
                    <!-- sidebar -->
		   <jsp:include page="side.jsp" /> 
                 
                    <!-- /sidebar -->

                   <div class="column col-sm-10 col-xs-11" id="main">
                        <div class="rightside">
                            <div class="topmenu text-right">
                                <a class="add_test" href="addtest">Add New</a>
                                <a href="javascript:notify('Information', 'Feature to upload Tests coming soon. Right now we have capability to upload Users and Questions.');">Import</a>
				<a href="signoff">Sign Off</a>
                                <div class="pagination">
                                     <c:if test="${showPreviousPage}">
                                    	<a href="${callingMethod}?page=${previousPage}${queryParam}"><i class="fa fa-arrow-left"></i></a>
                                    </c:if>
				    
				     <c:if test="${selectedPage != null &&  selectedPage > 0}">
	                                    ${selectedPage} / ${totalNumberOfPages}
                                    </c:if>
                                    
                                    <c:if test="${showNextPage}">
	                                    <a href="${callingMethod}?page=${nextPage}${queryParam}"><i class="fa fa-arrow-right"></i></a>
                                    </c:if>
                                </div>
                            </div>
                            <div class="questiontable">
                                <div class="questionheading">
                                    <div class="left">
                                        <h3>Test Bank</h3>
                                    </div>
                                    <div class="right">
                                        <div class="searchdata">
                                            
					     <input type="text" placeholder="Search a Test" name="searchText" id="searchText">
                                            <i class="fa fa-search" id="search"></i>
                                        </div>
                                        <div class="filter">
                                            <a href="javascript:notify('Information', 'Feature coming soon')"><img src="images/ic_sort.png">Sort</a>
                                            <a href="javascript:notify('Information', 'Feature coming soon')"><img src="images/ic_filter.png">Filter</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="questiontablelist" style="overflow-x:auto;">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th><input type="checkbox"></th>
                                                <th><img src="images/icon-selectionmode.png">Test Title</th>
                                                
                                                <th>Category</th>
                                                <th>Test Time In Minutes</th>
						<th>Pass Percentage </th>
                                                <th>Created By</th>
						<th>Last Update</th>
						<th>Expire Test</th>
						<th>Update Test</th>
						<th>Duplicate Test</th>
						<th>Share Test</th>
                                            </tr>
                                        </thead>
                                         <tbody>
					<tbody>
						                     
						                       <c:forEach  items="${tests}" var="test" >   
						                      	<tr>
										<td><input type="checkbox"></td>
												
						                      		<td><c:out value="${test.testName}"></c:out>  </td>
										
						                      		<td> ${test.category}</td>
										<td> ${test.testTimeInMinutes}</td>
										<td> ${test.passPercent}</td>
										
						                      		<td><c:out value="${test.cDate}"></c:out>   </td>
						                      		<td><c:out value="${test.uDate}"></c:out>   </td>
						                      		<td> <a onClick="confirm(${test.id}); return false;" href="#">Click to Expire</a>  </td>
										<td> <a  href="updateTest?testId=${test.id}">Click to Update</a>  </td>
								<td><a href="javascript:void(0);" class="testname" data-name="${test.testName}" data-toggle="modal" onClick="javascript:duplicateOpen('${test.testName}', '${test.companyId}')"><i class="fa fa-copy"></i></a></td>
								<td><a href="javascript:void(0);" class="testname" data-name="${test.testName}" data-toggle="modal" onClick="javascript:shareOpen('${test.testName}', '${test.publicUrl}', '${test.id}')"><i class="fa fa-share-alt"></i></a></td>
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
	
	 <!-- Duplicate Test Popup -->
        <div id="modalcopy" class="modal fade modalcopy" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Duplicate Test</h4>
                    </div>
                    <div class="modal-body">
                        <form method="GET" action="duplicateTest" >
                            <label>Existing test name</label>
                            <input id="existing_name" type="text" disabled/>
                            <label>New Test Name</label>
                            <input id="newTest" type="text" required/>
                            <label>Qualifier 1</label>
                            <input id="newQual1" type="text" required/>
                            <label>Qualifier 2</label>
                            <input   id="newQual2" type="text"/>
                            <div class="buttons" style="padding-top: 20px;">
                                <input type="button" value="Duplicate" onClick="javascript:dup()" />
                                <input type="button" data-dismiss="modal" value="Cancel"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Share Test Popup -->
        <div id="modalshare" class="modal fade modalcopy" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Share Test</h4>
                    </div>
                    <div class="modal-body">
                        <form method="POST" action="sharePublicTest">
                            <label>Existing test name</label>
                            <input id="existing_name1" type="text"/>
                            <label>First Name</label>
                            <input id="firstName" type="text"/>
                            <label>Last name</label>
                            <input id="lastName"  type="text"/>
                            <label>Email Id</label>
                            <input id="userEmail" type="text"/>
                            <label>Public Test URL</label>
                            <input id="publicTestUrl" type="text"/>
                           
			    <input type="hidden" name="testId" id="testId" value="" />
                            <div class="buttons" style="padding-top: 20px;">
                                <input type="button" value="Copy in your Clipboard" onClick="javascript:copyUrlInClipBoard()"/>
                                <input type="button" value="Share" onClick="javascript:shareTest()"/>
				<input type="button" value="Close" onClick="javascript:copyUrlClose()"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
      
	<script>
	
	function dup(){
	 var existing_name = document.getElementById("existing_name").value;
	 var newTest = document.getElementById("newTest").value;
	var newQual1 = document.getElementById("newQual1").value;
	var newQual2 = document.getElementById("newQual2").value;
		if(newTest == '' || newTest == null){
		notify('Info', 'Enter a name for the new Test');
		}
		else if(newQual1 == '' || newQual1 == null){
		notify('Info', 'Enter a Qualifier name for the new Test');
		}
		else{
		window.location = "duplicateTest?existing_name="+existing_name+"&newTest="+newTest+"&newQual1="+newQual1+"&newQual2="+newQual2;
		}
	
	}
	
	function duplicateOpen(testName, tenantId){
	   var name = $(this).attr('data-name');
		 console.log('here '+testName);
		 console.log(tenantId);
		 document.getElementById("existing_name").value=testName;
	$('#modalcopy').modal('show');
	$('#modalshare').modal('hide');
	}

	function shareOpen(testName, testPublicUrl, testId){
	   var name = $(this).attr('data-name');
		 console.log('here '+testName);
		
		 document.getElementById("existing_name1").value=testName;
		 document.getElementById("publicTestUrl").value=testPublicUrl;
		  document.getElementById("testId").value=testId;
	$('#modalcopy').modal('hide');
	$('#modalshare').modal('show');
	}
	
	function copyUrlInClipBoard(){
	el = document.createElement('textarea');
	  el.value = document.getElementById("publicTestUrl").value;
	  document.body.appendChild(el);
	  el.select();
	  document.execCommand('copy');
	  document.body.removeChild(el);
	//$('#modalshare').modal('hide');
	}
	
	function copyUrlClose(){
	$('#modalshare').modal('hide');
	}
	
	function shareTest(){
	 var existing_name1 = document.getElementById("existing_name1").value;
	 var firstName = document.getElementById("firstName").value;
	var lastName = document.getElementById("lastName").value;
	var userEmail = document.getElementById("userEmail").value;
	var testId = document.getElementById("testId").value;
		if(firstName == '' || firstName == null){
			notify('Info', 'First Name can not be blank');
		}
		else if(lastName == '' || lastName == null){
		notify('Info', 'Last Name can not be blank');
		}
		else if(userEmail == '' || userEmail == null){
		notify('Info', 'Email can not be blank');
		}
		else if(!validateEmail(userEmail)){
		notify('Info', 'Enter a valid email');
		}
		else{
		window.location = "sharePublicTest?existing_name1="+existing_name1+"&firstName="+firstName+"&lastName="+lastName+"&userEmail="+userEmail+"&testId="+testId;
		}
	
	}
	
	function validateEmail(email) {
	  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	  return re.test(email);
	}
	
	 $('#search').on('click',function(){
	    var text = document.getElementById("searchText").value;
		if(text.length != 0){
		window.location="searchTests?searchText="+text;
		}
	    });
	    
	
	
	function confirm(id) {
           (new PNotify({
		    title: 'Confirmation Needed',
		    text: 'Are you sure? Students having the link to this exam may no longer be able to take the exam',
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
		    window.location = "retireTest?testId="+id;
		}).on('pnotify.cancel', function() {
		   
		});
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
