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
                                <h2class="heading">Create New Setting</h2>
                                <div class="addqueform" >
                                     <form name="profileForm"  method="post" modelAttribute="params" action="saveProfileParams">
                                       

                                       
					
					<div class="formfield">
                                            <div class="selectoptions">
                                             

                                                <div id="maindivforaddmore">
													 <div class="option">
                                                   <label>Select Context</label>
                                            
													<form:select id="context" path="params.context" onchange="changeContext()">
						  
														 <form:options items="${qualifiers}"  />
													</form:select>
                                                    </div>
												
                                                    <div class="option">
                                                   <label>Qualifier 1</label>
                                            
													<form:input path="params.qualifier1" name="qualifier1" id="qualifier1" readonly="true"/>
                                                    </div>
                                                     <div class="option">
                                                   <label>Qualifier 2</label>
                                            
													<form:input path="params.qualifier2" name="qualifier2" id="qualifier2" readonly="true"/>
                                                    </div>
                                                    <div class="option">
                                                   <label>Qualifier 3</label>
                                            
													<form:input path="params.qualifier3" name="qualifier3" id="qualifier3" readonly="true"/>
                                                    </div>
                                                    <div class="option">
                                                   <label>Qualifier 4</label>
                                            
													<form:input path="params.qualifier4" name="qualifier4" id="qualifier4" readonly="true"/>
                                                    </div>
													<div class="option">
                                                   <label>Qualifier 5</label>
                                            
													<form:input path="params.qualifier5" name="qualifier5" id="qualifier5" readonly="true"/>
                                                    </div>                                                 
													<div class="option">
                                                   <label>LESS_THAN_TWENTY_PERCENT</label>
                                            
													<form:textarea path="params.LESS_THAN_TWENTY_PERCENT" name="LESS_THAN_TWENTY_PERCENT" id="LESS_THAN_TWENTY_PERCENT" rows="5" cols="60"/>
                                                    </div>
						    
													<div class="option">
                                                   <label>BETWEEN_TWENTY_AND_FIFTY</label>
                                            
													<form:textarea path="params.BETWEEN_TWENTY_AND_FIFTY" name="BETWEEN_TWENTY_AND_FIFTY" id="BETWEEN_TWENTY_AND_FIFTY" rows="5" cols="60"/>
                                                    </div>
													<div class="option">
                                                   <label>BETWEEN_FIFTY_AND_SEVENTYFIVE</label>
                                            
													<form:textarea path="params.BETWEEN_FIFTY_AND_SEVENTYFIVE" name="BETWEEN_FIFTY_AND_SEVENTYFIVE" id="BETWEEN_FIFTY_AND_SEVENTYFIVE" rows="5" cols="60"/>
                                                    </div>
													<div class="option">
                                                   <label>BETWEEN_SEVENTYFIVE_AND_NINETY</label>
                                            
													<form:textarea path="params.BETWEEN_SEVENTYFIVE_AND_NINETY" name="BETWEEN_SEVENTYFIVE_AND_NINETY" id="BETWEEN_SEVENTYFIVE_AND_NINETY" rows="5" cols="60"/>
                                                    </div>
													<div class="option">
                                                   <label>MORE_THAN_NINETY</label>
                                            
													<form:textarea path="params.MORE_THAN_NINETY" name="MORE_THAN_NINETY" id="MORE_THAN_NINETY" rows="5" cols="60"/>
                                                    </div>
						    
													 <div class="formfield savebtn">
													<input class="save" type="submit" value="Save Setting">
												
													
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
           
		 function changeContext(){
		var  selectedValue= $("#context").val();
		console.log(selectedValue);
		selectedValue = encodeURIComponent(selectedValue);
		console.log(selectedValue);
			window.location="showProfileParams?qual="+selectedValue;
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
