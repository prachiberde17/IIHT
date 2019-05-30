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

                            <div class="leftdiv" >

                                <div class="topmenu text-right">
                                   
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

                                <div class="questiontable" >
                                    <div class="questionheading">
                                        <div class="left">
                                            <h4>Question Bank</h4>
                                        </div>
                                        <div class="right">
                                            <div class="searchdata">
                                                <input type="text" placeholder="Search a Q" name="searchText" id="searchText">
                                            <i class="fa fa-search" id="search"></i>
                                            </div>

                                            <div class="filter">
                                                <a href="javascript:notify('Information', 'Feature coming soon')"><img src="images/ic_sort.png">Sort</a>
                                                <a href="javascript:notify('Information', 'Feature coming soon')"><img src="images/ic_filter.png">Filter</a>
                                            </div>

                                        </div>
                                    </div>


                                    <div class="questiontablelist" >
                                    <table class="table" >
                                        <thead>
                                            <tr>
                                                <th><b>No</b></th>
                                                <th>Question</th>
                                                <th  style="white-space:nowrap;">Category</th>
                                                <th>Difficulty Level</th>
                                                <th  style="white-space:nowrap;">Update</th>
						<th  style="white-space:nowrap;">Delete</th>
                                            </tr>
                                        </thead>
                                        <tbody>
					<tbody>
						                     
						                       <c:forEach  items="${qs}" var="question" varStatus="loop">   
						                      	<tr>

										<td>${loop.count}</td>		
						                      		<td><c:out value="${question.questionText}"></c:out>  </td>
						                      		<td> ${question.category}</td>
						                      		<td><c:out value="${question.difficultyLevel.level}"></c:out>   </td>
						                      		<td><a  href="addQuestion?qid=${question.id}">Click </a>   </td>
						                      		<td><a  href="javascript:confirm('${question.id}')">Click </a> </td>
						                      	</tr>
						                      	</c:forEach>   
						                      </tbody>
                                           
                                        </tbody>
                                    </table>
                                </div>

                                </div>

                            </div>	

                            <div class="rightdiv" >
                                <h4 class="heading">${question_label}</h4>
                                <div class="addqueform" >
                                     <form name="questionForm"  method="post" modelAttribute="question" action="saveQuestion">
                                        <div class="formfield">
                                            <label>Question</label>
                                            
					    <form:textarea path="question.questionText" required="true" />
					    <form:hidden path="question.id" />
                                        </div>

                                        <div class="formfield">
                                            <select>
                                                <option>Select Question Type</option>
						<option selected>Multi Choice Question</option>
						<option>Coding Assignment</option>
						<option>Project Assessment</option>
                                            </select>
					   
                                        </div>

                                        <div class="formfield">
                                            
					     <form:select path="question.level">
						  
						     <form:options items="${levels}" itemValue="level" itemLabel="level" />
						</form:select>
                                        </div>

                                        <div class="formfield">
                                            <div class="selectoptions">
                                                <span>Options</span>
                                                <span style="float: right;padding-right: 20px;">Correct Choice</span>

                                                <div id="maindivforaddmore">
                                                    <div class="option">
                                                        <span>A</span>
                                                       <form:input path="question.choice1" name="choice1" id="choice1" required="true"/>
                                                        <div class="choice">
                                                            <form:checkbox path="question.one" /> 
                                                        </div>
                                                    </div>
                                                    <div class="option">
                                                        <span>B</span>
                                                       <form:input path="question.choice2" name="choice2" id="choice2" required="true"/>
                                                        <div class="choice">
                                                            <form:checkbox path="question.two" /> 
                                                        </div>
                                                    </div>
                                                    <div class="option">
                                                        <span>C</span>
                                                        <form:input path="question.choice3" name="choice3" id="choice3" />
                                                        <div class="choice">
                                                            <form:checkbox path="question.three" /> 
                                                        </div>
                                                    </div>
                                                    <div class="option">
                                                        <span>D</span>
                                                        <form:input path="question.choice4" name="choice4" id="choice4" />
                                                        <div class="choice">
                                                            <form:checkbox path="question.four" /> 
                                                        </div>
                                                    </div>
                                                    <div class="option">
                                                        <span>E</span>
                                                        <form:input path="question.choice5" name="choice5" id="choice5" />
                                                        <div class="choice">
                                                            <form:checkbox path="question.five" /> 
                                                        </div>
                                                    </div>
						    
						    <div class="option">
                                                        <span>F</span>
                                                        <form:input path="question.choice6" name="choice6" id="choice6" />
                                                        <div class="choice">
                                                            <form:checkbox path="question.six" /> 
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
				
                                            
                                        </div>


                                        <div class="formfield addimagevideo">
                                            <a class="addimage" href="#">Add image</a>
                                            <a class="addaudio" href="#">Add audio</a>
                                            <a class="addvideo" href="#">Add Video</a>
                                        </div>
					
					<div class="formfield">
                                            <div class="selectoptions">
                                                <span>Choose Categories for Question</span>
                                               
                                                <div id="maindivforaddmore">
                                                    <div class="option">
                                                        <span>A</span>
                                                        <form:input path="question.qualifier1" name="qualifier1" id="qualifier1" required="true"/>
                                                        
                                                    </div>
                                                    <div class="option">
                                                        <span>B</span>
                                                        <form:input path="question.qualifier2" name="qualifier2" id="qualifier2" />
                                                       
                                                    </div>
                                                    <div class="option">
                                                        <span>C</span>
                                                        <form:input path="question.qualifier3" name="qualifier3" id="qualifier3" />
                                                       
                                                    </div>
                                                    
                                                </div>
                                            </div>
					</div>
                                        
                                        <label class="queimage"></label>
                                        <label class="queaudio"></label>
                                        <label class="quevideo"></label>
                                        
                                        <input type="file" name="addimage" id="addimage" style="display: none;">
                                        <input type="file" name="addaudio" id="addaudio" style="display: none;">
                                        <input type="file" name="addvideo" id="addvideo" style="display: none;">


                                        <div class="formfield">
                                            <label>Instructions, if any</label>
                                            <textarea></textarea>
                                        </div>


                                        <div class="formfield savebtn">
                                            <input class="save" type="submit" value="Save">
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
            $(function () {
                $(".addimage").on('click', function (e) {
                    e.preventDefault();
                    $("#addimage").trigger('click');
                });
                $(".addaudio").on('click', function (e) {
                    e.preventDefault();
                    $("#addaudio").trigger('click');
                });
                $(".addvideo").on('click', function (e) {
                    e.preventDefault();
                    $("#addvideo").trigger('click');
                });
            });


            $('#addimage').change(function () {
                var file = $('#addimage')[0].files[0].name;
                $('.queimage').text('Image: '+file);
            });
            $('#addaudio').change(function () {
                var file = $('#addaudio')[0].files[0].name;
                $('.queaudio').text('Audio: '+file);
            });
            $('#addvideo').change(function () {
                var file = $('#addvideo')[0].files[0].name;
                $('.quevideo').text('Video: '+file);
            });

	    
	      $('#search').on('click',function(){
	    var text = document.getElementById("searchText").value;
		if(text.length != 0){
		window.location="searchQuestions2?searchText="+text;
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
		    window.location = "removeQuestion?qid="+id;
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
