		
<link href="https://fonts.googleapis.com/css?family=Segoe+UI" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link href="css/font-awesome.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/responsive.css" rel="stylesheet" type="text/css">
		
<div class="column col-sm-2 col-xs-1 sidebar-offcanvas" id="sidebar">
	<ul class="nav" id="menu">
	    <li class="logolight"><a href="http://www.iiht.com" data-toggle="offcanvas"><i class="fa fa-bars fa-2x"></i><span class="collapse in hidden-xs"><img src="images/logoiiht.png" alt="logo"></span></a></li>
	    <li class="active"><a href="javascript:notify('Information', 'We will release the feature pretty soon! Pease wait for our next release');"><img src="images/dashboard_icon.png" alt="logo"> <span class="collapse in hidden-xs">Dashboard</span></a></li>
	    <li><a href="question_list"><img src="images/question_icon.png" alt="logo"> <span class="collapse in hidden-xs">Question Bank</span></a></li>
	    <li><a href="testlist"><img src="images/test_icon.png" alt="logo"> <span class="collapse in hidden-xs">Tests</span></a></li>
	    <li><a href="showReports"><img src="images/results_icon.png" alt="logo"> <span class="collapse in hidden-xs">Results</span></a></li>
	 <li><a href="listUsers"><img src="images/candidates_icon.png" alt="logo">  <span class="collapse in hidden-xs">Users</span></a></li>
	    <!--    <li><a href="javascript:callUpload();" id="uploadLink"><img src="images/candidates_icon.png" alt="logo"> <span class="collapse in hidden-xs">Candidates</span></a></li>
	    <form id="fileForm" method="POST" enctype="multipart/form-data" >
		<input type="file" name="fileFromUserForm_rem" id="fileFromUserForm_rem" style="display:none" />
	</form>  -->
	</ul>
    </div>
    
    
    <script>
    
    function callUpload(){
     $("#fileFromUserForm_rem").click();
    }
    
    function notDone(){
    
    }
    
    
    $(document).ready(function() {
	    
	   var file = $('[name="fileFromUserForm_rem"]');
	   var imgContainer = $('#imgContainer');
	   
		
		
	var fileU = document.getElementById('fileFromUserForm_rem');
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
		    data: new FormData(document.getElementById("fileForm")),
		    enctype: 'multipart/form-data',
		    processData: false,
		    contentType: false
		  }).done(function(data) {
		    notify('Success', 'File Upload Successful');
		   
		  }).fail(function(jqXHR, textStatus) {
		      notify('Failure', 'File Upload Failed. Please contact Administrator');
		  });
		  document.getElementById('fileFromUserForm_rem').value = null;
		    return;
		  }
		 
		});
	  
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