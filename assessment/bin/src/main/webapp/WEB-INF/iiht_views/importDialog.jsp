<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>IIHT</title>
<link href="https://fonts.googleapis.com/css?family=Segoe+UI"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link href="css/font-awesome.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/responsive.css" rel="stylesheet" type="text/css">
</head>
<body>

						<form  >
							<div class="form-group">
								<label>Select File</label> <input class="form-control"
									type="file" name="file">
							</div>
							<div class="form-group">
								<button class="btn btn-primary" type="submit" id= "upload">Upload</button>
								<!-- <button class="btn btn-primary" type="submit" id="close">close</button> -->
							</div>
						</form>
						<!-- Bootstrap Progress bar -->
						<div class="progress">
							<div id="progressBar" class="progress-bar progress-bar-success"
								role="progressbar" aria-valuenow="0" aria-valuemin="0"
								aria-valuemax="100" style="width: 0%">0%</div>
						</div>

						<!-- Alert -->
						<div id="alertMsg" style="color: red; font-size: 18px;"></div>


	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script type="text/javascript"> 
            function uploadExcelFile()
            {
                	debugger;
                    $.ajax({
                   		 type: "POST",
                        url:"./uploadQuestions", //method we are calling to upload questions
                        data: {id: id},
                        succes: function (data) {
                        	  alert('Yay! It worked!');
                              // Or if you are returning something
                              alert('I returned... ' + data);                    
                          },
                          error: function (result) {
                              alert('Oh no :(');
                          }
                    });
            }
            $.noConflict();
        	 $(function() {
        		debugger;
        		$('#upload').click(function(e) {
        			e.preventDefault();
        			//Disable submit button
        			$(this).prop('disabled',true);
        			
        			
        			var form = document.forms[0];
        			var formData = new FormData(form);
        				
        			// Ajax call for file uploaling
        			var ajaxReq = $.ajax({
        				url : "fileUpload",
        				type : 'POST',
        				enctype: 'multipart/form-data',
        				data : formData,
        				cache : false,
        				contentType : false,
        				processData : false,
        				
        				xhr: function(){
        					//Get XmlHttpRequest object
        					 var xhr = $.ajaxSettings.xhr() ;
        					
        					//Set onprogress event handler 
        					 xhr.upload.onprogress = function(event){
        						var perc = Math.round((event.loaded / event.total) * 100);
        						$('#progressBar').text(perc + '%');
        						$('#progressBar').css('width',perc + '%');
        					 };
        					 return xhr ;
        				},
        				beforeSend: function( xhr ) {
        					//Reset alert message and progress bar
        					$('#alertMsg').text('');
        					$('#progressBar').text('');
        					$('#progressBar').css('width','0%');
                        }
        			});

        			// Called on success of file upload
        			ajaxReq.done(function(msg) {
        				$('#alertMsg').text("Successfully Uploaded");
        				$('input[type=file]').val('');
        				$('button[type=submit]').prop('disabled',false);
        			});
        			
        			// Called on failure of file upload
        			ajaxReq.fail(function(jqXHR) {
        				$('#alertMsg').text("Error While Uploading Questions");
        				$('button[type=submit]').prop('disabled',false);
        			});
        			
        			/* $('#close').click(function () {
        			    $('#questionsImport').dialog('close');
        			    return false;
        			}); */
        		});
        	});
        </script>

</body>