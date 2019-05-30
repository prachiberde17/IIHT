<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.assessment.data.*, java.text.*, java.util.*" %>
<html class="no-js" lang="">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="x-ua-compatible" content="ie=edge">
      <title>Yaksha</title>
      <meta name="description" content="">
      <meta name="viewport" content="width=device-width, initial-scale=1">
	  <link rel="stylesheet" href="css/bootstrap_only_login_new.css">
	  
      <link rel="stylesheet" href="css/flexslider.css">
	  <link rel="stylesheet" href="css/animate.css">
	  <link rel="stylesheet" href="css/custom-styles.css">
	  <link rel="stylesheet" href="css/custom-styles.css">
       <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
   	
	<link href='https://fonts.googleapis.com/css?family=Roboto:300,400,700'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Muli:300,400,700'
	rel='stylesheet' type='text/css'>




<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="scripts/custom.js"></script>

	<script type="text/javascript" src="scripts/pnotify.custom.min.js"></script>

<script type="text/javascript" src="scripts/pnotify.nonblock.js"></script>
<script type="text/javascript" src="scripts/pnotify.buttons.js"></script>

<link href="css/pnotify.custom.min.css" media="all" rel="stylesheet" type="text/css">
</head>
   <body>
      <!-- Header -->
      <section class="custom-header">
         <nav class="navbar navbar-expand-lg navbar-light">
            <a class="navbar-brand" href="#"><img src="images/Logo.png" alt="logo"></a>
            <!-- <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
               <span class="navbar-toggler-icon"></span>
               </button> -->
            <div class="nav-custom" id="navbarNav">
               <ul class="navbar-nav ml-auto">
                  <li class="nav-item nav-icon">
                     <a class="nav-link" href="#about" data-toggle="tooltip" data-placement="bottom" title="Mail"><i class="fas fa-envelope"></i></a>
                  </li>
                  <li class="nav-item nav-icon2">
                     <a class="nav-link mr-3" href="#" data-toggle="tooltip" data-placement="bottom" title="Contact"><i class="fas fa-phone"></i></a>
                  </li>
                  <li class="nav-item" id="lg-web">
                     <a class="nav-link login-btn" href="#" data-toggle="modal" data-target="#loginPop">Login</a>
                  </li>
                  <li class="nav-item nav-icon" id="lg-mob">
                     <a class="nav-link" href="#" data-toggle="modal" data-target="#loginPop"><i class="fas fa-sign-in-alt"></i></a>
                  </li>
               </ul>
            </div>
         </nav>
         <!-- /header -->
      </section>
      <!-- banner -->
      <section class="yaksha-online">
         <div class="container-fluid">
            <div class="row">
               <div class="col-md-7 hide-mob">
                  <div class="menu-dna">
                     <div class="dna-img">
                        <div class="action-tb">
                           <a href="#" class="btn btn-custom-pink tab-link" data-tab="tab-2">Automated Recruitment Solutions</a>
                           <a href="#" class="btn btn-custom-green tab-link" data-tab="tab-3">Skill Development & Evaluation</a>
                           <a href="#" class="btn btn-custom-purple tab-link" data-tab="tab-4">Talent Acquisition</a>
                        </div>
                        <div class="dna-items">
                           <a href="#" class="incorp-link tab-link" data-tab="tab-1">For Incorporated</a>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="col-md-5">
                  <div class="page-content">
                     <div class="home">
                        <h1 class="section-title animated fadeInDown">Yaksha Online</h1>
                        <div class="about-card mt-4 animated fadeInUp">
                           <h4 class="card-title">Talent & Skill As A Service</h4>
                           <p class="card-text mt-35"> Technology creates newer Disruptions &  Enterprises seek a Transition to Emerging Trends. Talent needs Job-Readiness & Workforce must become a Specialized Marketplace of Skills.
                              Know-How needs a Show-How Acumen.
                           </p>
                           <p class="card-text">
                              Whether you need Talent On Demand to Recruit (or)
                              Employable Skill-sets, Yaksha Is Online for You!
                           </p>
                           <a href="#" class="card-link mt-4">Get Skilled & Get Billed</a>
                        </div>
                     </div>
                     <div id="tab-1" class="tab-content incorporated">
                        <div class="section-flex">
                           <h1 class="section-title">Incorporated</h1>
                           <a href="#" class="body-close mr-2"><i class="fas fa-times"></i></a>
                        </div>
                        <div class="section-content mr-2">
                           <div>
                              <h4 class="content-title">Skill & Scale</h4>
                              <p class="content-text mt-35">Use Yaksha to Skill your Employees. Eventually they will Scale to meet your Business Demands</p>
                           </div>
                           <div class="mt-5">
                              <h4 class="content-title">Precision & Performance </h4>
                              <p class="content-text mt-35">Our Platform is AI Powered. A Highly Available and Resilient Cluster of Systems ensure Performance coupled with Precision</p>
                           </div>
                           <div class="mt-5">
                              <h4 class="content-title">Experience & Expertise  </h4>
                              <p class="content-text mt-35">We strive to offer Visually/Functionally a fulfilling Experience using our Expertise of Customer/Learner Centric Philosophy</p>
                           </div>
                           <div class="mt-5">
                              <h4 class="content-title">Secure & Stable  </h4>
                              <p class="content-text mt-35">Yaksha is built using Saas & PaaS Data Protection & Privacy compliant Architecture. Containerized Services ensure Stable Usage Patterns</p>
                           </div>
                        </div>
                     </div>
                     <div id="tab-2" class="tab-content automated">
                        <div class="section-flex align-items-baseline">
                           <h1 class="section-title">Automated Recruitment Solutions</h1>
                           <a href="#" class="body-close mr-2"><i class="fas fa-times"></i></a>
                        </div>
                        <div class="section-content mr-2">
                           <div class="info-text mb-3">
                              <div class="row">
                                 <div class="col-md-4 pr-0">
                                    <h1 class="into-title">80<span>%</span></h1>
                                 </div>
                                 <div class="col-md-8 pl-0">
                                    <p class="content-text">of the Recruitment Processes using Yaksha Online. Roll-Out offers Virtually.Plug and Play Instances for Interviewers & CandidatesUse Visually rich and engaging Dashboards to manage Talent Pool
                                    </p>
                                 </div>
                              </div>
                           </div>
                           <div>
                              <h4 class="content-title">Trade Craft You Need</h4>
                              <ul class="custom-list mt-35">
                                 <li>
                                    <div class="collapse" id="collapseOne">
                                       <div class="card card-body">
                                          <div class="collapse-content">
                                             <div class="title-flex mb-3">
                                                <span class="icn"><img src="images/video.svg" alt="video"></span>
                                                <span class="li-content"> Live Video Proctoring </span>
                                             </div>
                                             <p class="collapse-text">Plug in our platform and watch how your Candidate performs. Use uniterrupted video analysis to test the Integrity of your Candidates</p>
                                          </div>
                                       </div>
                                    </div>
                                    <div class="collapse-flex">
                                       <span class="li-icon li-icon1"><img src="images/video.svg" alt="video"></span>
                                       <span class="li-content li-content1"> Live Video Proctoring </span>
                                       <span><a class="expand expand1"  data-toggle="collapse" href="#collapseOne" role="button" aria-expanded="false" aria-controls="collapseOne" ><img src="images/plus.svg" alt="add"></a></span>
                                    </div>
                                 </li>
                                 <li>
                                    <div class="collapse" id="collapseTwo">
                                       <div class="card card-body">
                                          <div class="collapse-content">
                                             <div class="title-flex mb-3">
                                                <span class="icn"><img src="images/gamefield.svg" alt="game"></span>
                                                <span class="li-content"> Gamified Whiteboards </span>
                                             </div>
                                             <p class="collapse-text">Whiteboards for Q & A's between Panels and Candidates. Monitor live responses with a Web Proctored Secure Platform</p>
                                          </div>
                                       </div>
                                    </div>
                                    <div class="collapse-flex">
                                       <span class="li-icon li-icon2"><img src="images/gamefield.svg" alt="game"></span>
                                       <span class="li-content li-content2"> Gamified Whiteboards </span>
                                       <span><a class="expand expand2"  data-toggle="collapse" href="#collapseTwo" role="button" aria-expanded="false" aria-controls="collapseOne" ><img src="images/plus.svg" alt="add"></a></span>
                                    </div>
                                 </li>
                                 <li>
                                    <div class="collapse" id="collapseThree">
                                       <div class="card card-body">
                                          <div class="collapse-content">
                                             <div class="title-flex mb-3">
                                                <span class="icn"><img src="images/interview.svg" alt="interview"></span>
                                                <span class="li-content"> Automated Interview Calls </span>
                                             </div>
                                             <ul class="collapse-text">
                                                <li>Schedule SME/HR/Business Head etc and Candidates for meticulous and planned interviews.</li>
                                                <li>Block mutually inclusive Calendars at the Click of your Mouse</li>
                                                <li>Mobile Friendly Messages, Mailers and IM Presence to coordinate seamless Interview Sessions</li>
                                             </ul>
                                          </div>
                                       </div>
                                    </div>
                                    <div class="collapse-flex">
                                       <span class="li-icon li-icon3"><img src="images/interview.svg" alt="interview"></span>
                                       <span class="li-content li-content3"> Automated Interview Calls </span>
                                       <span><a class="expand expand3"  data-toggle="collapse" href="#collapseThree" role="button" aria-expanded="false" aria-controls="collapseOne" ><img src="images/plus.svg" alt="add"></a></span>
                                    </div>
                                 </li>
                                 <li>
                                    <div class="collapse" id="collapseFour">
                                       <div class="card card-body">
                                          <div class="collapse-content">
                                             <div class="title-flex mb-3">
                                                <span class="icn"><img src="images/features.svg" alt="features"></span>
                                                <span class="li-content"> Features </span>
                                             </div>
                                             <ul class="collapse-text">
                                                <li>Video Conferencing / Proctoring</li>
                                                <li>Secure Whiteboards (To be used as Workspaces during Interviews)</li>
                                                <li>Calendar Blocking</li>
                                                <li>Talent Pool Oriented Business Dashboards</li>
                                                <li>AI Powered Candidate Profiling</li>
                                                <li>Communication Services</li>
                                             </ul>
                                          </div>
                                       </div>
                                    </div>
                                    <div class="collapse-flex">
                                       <span class="li-icon li-icon4"><img src="images/features.svg" alt="features"></span>
                                       <span class="li-content li-content4"> Features </span>
                                       <span><a class="expand expand4"  data-toggle="collapse" href="#collapseFour" role="button" aria-expanded="false" aria-controls="collapseOne" ><img src="images/plus.svg" alt="add"></a></span>
                                    </div>
                                 </li>
                              </ul>
                              <a href="#" class="know-more" data-toggle="modal" data-target="#requestDemo" style="visibility:hidden">Know more</a>
                           </div>
                        </div>
                     </div>
                     <div id="tab-3" class="tab-content skill">
                        <div class="section-flex align-items-baseline">
                           <h1 class="section-title">Skill Development & Evaluation (SKIDE)</h1>
                           <a href="#" class="body-close mr-2"><i class="fas fa-times"></i></a>
                        </div>
                        <div class="section-content mr-2">
                           <div class="info-text mb-5">
                              <p class="content-text">Cipher Nation is here!</p>
                              <p class="content-text">Auto-grade Full Stack Applications. Reap the benefits of 60 odd Compilers. Build native CI/CD pipes, Headless Containerization and Source Code Management with Yaksha App-forms
                              </p>
                           </div>
                           <div>
                              <h4 class="content-title">Problems We Solve</h4>
                              <ul class="custom-list mt-35">
                                 <li>
                                    <div class="collapse" id="collapseFive">
                                       <div class="card card-body">
                                          <div class="collapse-content">
                                             <div class="title-flex mb-3">
                                                <span class="icn"><img src="images/autograde.svg" alt="autograde"></span>
                                                <span class="li-content"> Auto-Grade Apps, Code Re-factoring </span>
                                             </div>
                                             <p class="collapse-text">Build Mobile Friendly / Web apps on any software architecture under the supervision of Web Proctored Systems. Get it Tested for Industry Standard Metrics. 
                                                Prove that your code can be Logical, Functional and Compliably Efficient
                                             </p>
                                          </div>
                                       </div>
                                    </div>
                                    <div class="collapse-flex">
                                       <span class="li-icon li-icon5"><img src="images/autograde.svg" alt="autograde"></span>
                                       <span class="li-content li-content5"> Auto-Grade Apps, Code Re-factoring </span>
                                       <span><a class="expand expand5"  data-toggle="collapse" href="#collapseFive" role="button" aria-expanded="false" aria-controls="collapseOne" ><img src="images/plus.svg" alt="add"></a></span>
                                    </div>
                                 </li>
                                 <li>
                                    <div class="collapse" id="collapseSix">
                                       <div class="card card-body">
                                          <div class="collapse-content">
                                             <div class="title-flex mb-3">
                                                <span class="icn"><img src="images/storyboard.svg" alt="storyboard"></span>
                                                <span class="li-content"> Story Boards </span>
                                             </div>
                                             <p class="collapse-text">AI powered Deep Code/App Analysis. Build a Proficiency Index of a Candidate or Cohorts using our BI Tools. Customer friendly Dashboards and Format friendly Reports</p>
                                          </div>
                                       </div>
                                    </div>
                                    <div class="collapse-flex">
                                       <span class="li-icon li-icon6"><img src="images/storyboard.svg" alt="storyboard"></span>
                                       <span class="li-content li-content6"> Story Boards </span>
                                       <span><a class="expand expand6"  data-toggle="collapse" href="#collapseSix" role="button" aria-expanded="false" aria-controls="collapseOne" ><img src="images/plus.svg" alt="add"></a></span>
                                    </div>
                                 </li>
                                 <li>
                                    <div class="collapse" id="collapseSeven">
                                       <div class="card card-body">
                                          <div class="collapse-content">
                                             <div class="title-flex mb-3">
                                                <span class="icn"><img src="images/code.svg" alt="code"></span>
                                                <span class="li-content"> code-pe diem </span>
                                             </div>
                                             <p class="collapse-text">Take part in Hack-Stack Coding Summits. Co-Develop Software with Peers and Leaders in the Industry. Automate App Testing using Yaksha's Development Engines.
                                                Take home our "Badge Of Honor"
                                             </p>
                                          </div>
                                       </div>
                                    </div>
                                    <div class="collapse-flex">
                                       <span class="li-icon li-icon7"><img src="images/code.svg" alt="code"></span>
                                       <span class="li-content li-content7"> code-pe diem </span>
                                       <span><a class="expand expand7"  data-toggle="collapse" href="#collapseSeven" role="button" aria-expanded="false" aria-controls="collapseOne" ><img src="images/plus.svg" alt="add"></a></span>
                                    </div>
                                 </li>
                                 <li>
                                    <div class="collapse" id="collapseEight">
                                       <div class="card card-body">
                                          <div class="collapse-content">
                                             <div class="title-flex mb-3">
                                                <span class="icn"><img src="images/features.svg" alt="features"></span>
                                                <span class="li-content"> Features </span>
                                             </div>
                                             <ul class="collapse-text">
                                                <li>Auto Evaluation of Applications</li>
                                                <li>Web Proctoring</li>
                                                <li>Headless Browser Friendly IDEs</li>
                                                <li>Dynamic Web Testing</li>
                                                <li>Recommendation Engine Based Reports</li>
                                                <li>Communication Services</li>
                                                <li>Business Workflow Automation</li>
                                                <li>Visually Rich Dashboards</li>
                                                <li>GDPR Compliant</li>
                                                <li>Secure Customer Tenants</li>
                                             </ul>
                                          </div>
                                       </div>
                                    </div>
                                    <div class="collapse-flex">
                                       <span class="li-icon li-icon8"><img src="images/features.svg" alt="features"></span>
                                       <span class="li-content li-content8"> Features </span>
                                       <span><a class="expand expand8"  data-toggle="collapse" href="#collapseEight" role="button" aria-expanded="false" aria-controls="collapseOne" ><img src="images/plus.svg" alt="add"></a></span>
                                    </div>
                                 </li>
                              </ul>
                              <a href="#" class="know-more" data-toggle="modal" data-target="#requestDemo">Know more</a>
                           </div>
                        </div>
                     </div>
                     <div id="tab-4" class="tab-content talent">
                        <div class="section-flex align-items-baseline">
                           <h1 class="section-title">Talent Acquisition</h1>
                           <a href="#" class="body-close mr-2"><i class="fas fa-times"></i></a>
                        </div>
                        <div class="section-content mr-2">
                           <div class="info-text mb-5">
                              <p class="content-text">Campus To Cubicle</p>
                              <p class="content-text">Hire when you need the most. Identify Talent or Skill it on a Need-Basis. Yaksha helps you roll out Assessments en-masse. Zero In on the most wanted Talent with compelling Leaderboards.
                              </p>
                           </div>
                           <div>
                              <h4 class="content-title">Solutions We Give</h4>
                              <ul class="custom-list mt-35">
                                 <li>
                                    <div class="collapse" id="collapseNine">
                                       <div class="card card-body">
                                          <div class="collapse-content">
                                             <div class="title-flex mb-3">
                                                <span class="icn"><img src="images/talent.svg" alt="talent"></span>
                                                <span class="li-content"> Talent On Demand </span>
                                             </div>
                                             <p class="collapse-text">Name the Input Criterias for Candidate Selection. Experience the benefits of a a World Class Integrated Platform for Graduate Skill Enhancement
                                             </p>
                                          </div>
                                       </div>
                                    </div>
                                    <div class="collapse-flex">
                                       <span class="li-icon li-icon9"><img src="images/talent.svg" alt="talent"></span>
                                       <span class="li-content li-content9"> Talent On Demand </span>
                                       <span><a class="expand expand9"  data-toggle="collapse" href="#collapseNine" role="button" aria-expanded="false" aria-controls="collapseOne" ><img src="images/plus.svg" alt="add"></a></span>
                                    </div>
                                 </li>
                                 <li>
                                    <div class="collapse" id="collapseTen">
                                       <div class="card card-body">
                                          <div class="collapse-content">
                                             <div class="title-flex mb-3">
                                                <span class="icn"><img src="images/hire.svg" alt="hire"></span>
                                                <span class="li-content"> Hire, Train & Deploy </span>
                                             </div>
                                             <ul class="collapse-text">
                                                <li>Customer Centric Training & Commercial Methodologies.</li>
                                                <li>Assured Learning Curve in Emerging Technologies.</li>
                                                <li>World Class Mentoring</li>
                                             </ul>
                                          </div>
                                       </div>
                                    </div>
                                    <div class="collapse-flex">
                                       <span class="li-icon li-icon10"><img src="images/hire.svg" alt="hire"></span>
                                       <span class="li-content li-content10"> Hire, Train & Deploy </span>
                                       <span><a class="expand expand10"  data-toggle="collapse" href="#collapseTen" role="button" aria-expanded="false" aria-controls="collapseOne" ><img src="images/plus.svg" alt="add"></a></span>
                                    </div>
                                 </li>
                                 <li>
                                    <div class="collapse" id="collapseEleven">
                                       <div class="card card-body">
                                          <div class="collapse-content">
                                             <div class="title-flex mb-3">
                                                <span class="icn"><img src="images/features.svg" alt="features"></span>
                                                <span class="li-content"> Features </span>
                                             </div>
                                             <ul class="collapse-text">
                                                <li>Mentors On Demand (Scheduled or On-The Fly)</li>
                                                <li>Leaderboards</li>
                                                <li>Geographical Clusters & Real Time Orientation</li>
                                             </ul>
                                          </div>
                                       </div>
                                    </div>
                                    <div class="collapse-flex">
                                       <span class="li-icon li-icon11"><img src="images/features.svg" alt="features"></span>
                                       <span class="li-content li-content11"> Features </span>
                                       <span><a class="expand expand11"  data-toggle="collapse" href="#collapseEleven" role="button" aria-expanded="false" aria-controls="collapseOne" ><img src="images/plus.svg" alt="add"></a></span>
                                    </div>
                                 </li>
                              </ul>
                              <a href="#" class="know-more" data-toggle="modal" data-target="#requestDemo">Know more</a>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </section>
      <!-- mobile-fab -->
      <div class="floatingButtonWrap">
         <div class="floatingButtonInner">
            <a href="#" class="floatingButton">
               <div id="nav-icon2">
                     <span></span>
                     <span></span>
                     <span></span>
                     <span></span>
                     <span></span>
                     <span></span>
                   </div>
            </a>
            <ul class="floatingMenu">
               <li>
                  <a href="#" class="tab-link fb-white" data-tab="tab-1">For Incorporated</a>
               </li>
               <li>
                  <a href="#" class="tab-link fb-pink" data-tab="tab-2">Automated Recruitment Solutions</a>
               </li>
               <li>
                  <a href="#" class="tab-link fb-green" data-tab="tab-3">Skill Development & Evaluation</a>
               </li>
               <li>
                  <a href="#" class="tab-link fb-purple" data-tab="tab-4">Talent Acquisition</a>
               </li>
            </ul>
         </div>
      </div>
      <div class="floating-backdrop"></div>
      <!-- modal-login -->
      <!-- Modal -->
      <div class="modal fade login-modal" id="loginPop" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
         <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
               <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel"><img src="images/login-logo.png" alt="lg-logo"></h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true"><i class="fas fa-times"></i></span>
                  </button>
               </div>
               <div class="modal-body">
                  <h4 class="modal-title">Login to your account</h4>
                  <form id="loginForm" method="post" modelAttribute="user" action="authenticate">
                     <div class="form-group mt-4">
                     <!--   <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Username"> -->    
					  <form:input type="email" path="user.email" name="email" id="username" cssClass="form-control" required="true"/>						
                     </div>
                     <div class="form-group">
                      <!--  <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"> -->
					  <form:password path="user.password" name="password" id="password" cssClass="form-control" required="true"/>
                     </div>
                     <div class="form-group">
                       <!-- <input type="text" class="form-control" aria-describedby="emailHelp" placeholder="Company">  -->
						<form:input  path="user.companyName" name="companyName" id="companyName" cssClass="form-control" required="true"/>					   
                     </div>
                     <div class="form-group">
                        <a href="#" class="btn btn-secondary" onclick="document.getElementById('loginForm').submit();">Login</a>   
                     </div>
                    
                  </form>
               </div>
            </div>
         </div>
      </div>
      <!-- ||modal-login -->
      <!-- modal-request-demo -->
      <div class="modal request-demo-modal fade" id="requestDemo" tabindex="-1" role="dialog" aria-labelledby="requestDemoLabel" aria-hidden="true">
         <div class="modal-dialog" role="document">
            <div class="modal-content">
               <div class="row">
                  <div class="col-md-6">
                     <div class="demo-banner"></div>
                     <!-- <img src="images/demobanner.png" alt="demobanner"> -->
                  </div>
                  <div class="col-md-6">
                     <div class="modal-header">
                        <h5 class="modal-title" id="requestDemoLabel"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true"><i class="fas fa-times"></i></span>
                        </button>
                     </div>
                     <div class="modal-body pt-0">
                        <div class="form-content">
                           <h5 class="rd-modal-title" id="requestDemoLabel">Request a demo</h5>
                           <p class="rd-info">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolorLorem ipsum dolor sit amet, consectetur </p>
                           <form action="" class="mt-5">
                              <div class="form-group mt-4">
                                 <input type="text" class="form-control"  placeholder="Your name">     
                              </div>
                              <div class="form-group">
                                 <input type="email" class="form-control" placeholder="Email address">     
                              </div>
                              <div class="form-group">
                                 <input type="text" class="form-control" placeholder="Phone #">     
                              </div>
                              <div class="form-group">
                                 <input type="text" class="form-control" placeholder="Company name">     
                              </div>
                              <div class="form-group">
                                 <textarea class="form-control" rows="3" placeholder="Tell us what you want to know"></textarea>
                              </div>
                              <div class="form-group">
                                 <a href="#" class="btn btn-secondary">Schedule Demo</a>   
                              </div>
                           </form>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!-- scripts -->
	  
      <script src="scripts_login/jquery-3.1.1.min.js"></script>
      <script src="scripts_login/popper.min.js"></script>
      <script src="scripts_login/bootstrap.min.js"></script>
      <script src="scripts_login/smooth-scroll.js"></script>
      <script src="scripts_login/wow.js"></script>
      <script src="scripts_login/custom.js"></script>
	  
	   <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript">
            function myFunction() {
                var x = document.getElementById("userpassword");
                if (x.type === "password") {
                    x.type = "text";
                } else {
                    x.type = "password";
                }
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