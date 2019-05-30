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
	<link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>
	
	
	
	<script>
	function goback(){
	window.location = "goback";
	}
	</script>
	
    </head>
    <body>
   <div>
      <div style="background:#f9f9f9;color:#373737;font-family:&#39;Helvetica Neue&#39;,Helvetica,Arial,sans-serif;font-size:17px;line-height:24px;max-width:100%;width:100%!important;margin:0 auto;padding:0">
         <table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse:collapse;line-height:24px;margin:0;padding:0;width:100%;font-size:17px;color:#373737;background:#f9f9f9">
            <tbody>
               <tr>
                  <td valign="top" style="font-family:&#39;Helvetica Neue&#39;,Helvetica,Arial,sans-serif!important;border-collapse:collapse">
                     <table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse:collapse">
                        <tbody>
                           <tr>
                              <td valign="bottom" style="font-family:&#39;Helvetica Neue&#39;,Helvetica,Arial,sans-serif!important;border-collapse:collapse;padding:20px 16px 12px">
                                 <div style="text-align:center">
                                    <a href="http://www.iiht.com" style="color:#439fe0;font-weight:bold;text-decoration:none;word-break:break-word" target="_blank">
                                    <img src="http://grovenue.com/grovenue/images/Logo.png" width="120" height="36" style="outline:none;text-decoration:none;border:none"></a>
                                 </div>
                              </td>
                           </tr>
                        </tbody>
                     </table>
                  </td>
               </tr>
               <tr>
                  <td valign="top" style="font-family:&#39;Helvetica Neue&#39;,Helvetica,Arial,sans-serif!important;border-collapse:collapse">
                     <table cellpadding="32" cellspacing="0" border="0" align="center" style="border-collapse:collapse;background:white;border-radius:0.5rem;margin-bottom:1rem">
                        <tbody>
                           <tr>
                              <td width="546" valign="top" style="font-family:&#39;Helvetica Neue&#39;,Helvetica,Arial,sans-serif!important;border-collapse:collapse">
                                 <div style="max-width:600px;margin:0 auto">
                                    <div style="background:white;border-radius:0.5rem;margin-bottom:1rem">
                                       <h3 style="color:#2ab27b;line-height:30px;margin-bottom:12px;margin:0 0 12px">
                                          Hi ${user.firstName} ${user.lastName},			
                                       </h3>
                                       <p style="font-size:17px;line-height:24px;margin:0 0 16px">
                                          Thanks for attempting the following test - 
                                       </p>
                                       <p style="font-size:17px;line-height:24px;margin:0 0 16px">
                                         Test Name - ${test.testName} <br/>
					 Test Created By - ${test.createdBy} <br/>
					 Test Introduction - ${test.intro}  <br/>
					 Test Pass Percent - ${test.passPercent}
                                          <img src="https://ci4.googleusercontent.com/proxy/D7YUk07wkOB2Hk8cwTbMlq7lrwunpq8m24F9OaA0d4fVbI1LIZd0LJlvU21exyHXC_fEbQT0NuGKd7uKzD4K6YBoDpQ0g4rTm9hZ5ZJuI-2D0K-v45vGLTA=s0-d-e1-ft#https://a.slack-edge.com/f5d6f/img/emoji_2016_06_08/apple/1f389.png" width="18" height="18" style="outline:none;text-decoration:none">
                                       </p>
                                       
                                      <div style="padding:12px 0 4px 0;font-weight:bold;font-size:21px;line-height:1.4em;text-align:center">
                                          Coming Soon!		
                                       </div>

                                       
                                       <table width="100%" style="border-collapse:collapse">
                                          <tbody>
                                             <tr>
                                                <td style="font-family:&#39;Helvetica Neue&#39;,Helvetica,Arial,sans-serif!important;border-collapse:collapse;height:1rem"> </td>
                                             </tr>
                                             <tr>
                                                <td style="font-family:&#39;Helvetica Neue&#39;,Helvetica,Arial,sans-serif!important;border-collapse:collapse;height:1rem;border-top:1px solid #ececec"> </td>
                                             </tr>
                                          </tbody>
                                       </table>
                                       <p style="font-size:17px;line-height:24px;margin:0 0 16px">
                                        We are working on this feature - to take the test. We would release it on or before  <b>10th August 2018. </b>
                                       </p>
                                       <p style="font-size:17px;line-height:24px;margin:0 0 16px">
                                          Please feel free to contact <a href="mailto:jatin.sutaria@thev2technologies.com">Admin</a> for any queries.	
                                       </p>
                  
                                       <p style="font-size:17px;line-height:24px;margin:0 0 16px">
                                          Cheers,<br>
                                          The team at IIHT
                                       </p>
                                    </div>
                                 </div>
                              </td>
                           </tr>
                        </tbody>
                     </table>
                  </td>
               </tr>
               <tr>
                  <td style="font-family:&#39;Helvetica Neue&#39;,Helvetica,Arial,sans-serif!important;border-collapse:collapse">
                     <table width="100%" cellpadding="0" cellspacing="0" border="0" align="center" style="border-collapse:collapse;margin-top:1rem;background:white;color:#989ea6">
                        <tbody>
                           <tr>
                              <td style="font-family:&#39;Helvetica Neue&#39;,Helvetica,Arial,sans-serif!important;border-collapse:collapse;height:5px;background-image:url(&#39;http://localhost/assessment/images/logoiiht.png;);background-repeat:repeat-x"></td>
                           </tr>
                           <tr>
                              <td valign="top" align="center" style="font-family:&#39;Helvetica Neue&#39;,Helvetica,Arial,sans-serif!important;border-collapse:collapse;padding:16px 8px 24px">
                                 <div style="max-width:600px;margin:0 auto">
                                    <p style="font-size:12px;line-height:20px;margin:0 0 16px;margin-top:16px">
                                       Made by <a href="http://iiht.com/" style="color:#439fe0;font-weight:bold;text-decoration:none;word-break:break-word" target="_blank">IIHT, Inc</a>
                                        ? 
                                       <a href="http://iiht.com/" style="color:#439fe0;font-weight:bold;text-decoration:none;word-break:break-word" target="_blank">
                                       eAssess Platform </a><br><a href="#0.1_m_-5916077829148442159_" style="color:#989ea6;font-weight:normal;text-decoration:none;word-break:break-word">
                                       #15, 4th Floor, Laxmi Complex, St Marks Rd, Shanthala Nagar, Ashok Nagar, Bengaluru, Karnataka 560001
                                       </a>
                                    </p>
                                 </div>
                              </td>
                           </tr>
                        </tbody>
                     </table>
                  </td>
               </tr>
            </tbody>
         </table>
      </div>
   </div>
</body>
</html>