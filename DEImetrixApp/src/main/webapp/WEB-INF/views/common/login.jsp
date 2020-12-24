<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Login</title>
  <link rel="stylesheet" href="resources/css/login.css">
    <link rel="stylesheet" type="text/css" href="resources/web-plugins/Validation_Engine/validationEngine.jquery.css"/>
  	<!--- Bootstrap CSS File-------->
  	<link rel="stylesheet" href="resources/web-plugins/bootstrap/css/bootstrap.min.css">
  	<!------ Fonts CSS File--------->
  	<link rel="stylesheet" href="resources/web-plugins/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
  	<link rel="icon" type="image/png" href="resources/img/fav_icon.png" />
</head>
<body class="register" style="overflow-y: scroll;">
	<div class="container">
		<div class="row col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
			 <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 register-left" align="center">
				<img src="resources/img/logo.png" alt="DEImetrix" style="object-fit: contain;width: 100%">
			</div>
			<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
			
			</div>
		</div>
       <div class="row">
           <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 register-left">
           		<img src="resources/img/logo_white.png" alt="">
               <h3>Welcome</h3>
               <p>DEImetrix&trade; is an exploration of your beliefs, attitudes and preferences toward Diversity, Equity & Inclusion in your daily interactions in the workplace.</p>
           </div>
           <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9 register-right">
               <ul class="nav nav-tabs nav-justified" id="myTab" role="tablist"> 
                   <li class="nav-item">
                 		<c:choose>
		               	  	<c:when test="${key_name eq 'register'}">
			                   <a class="nav-link" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Login</a>
		               	  	</c:when>
		               	  	<c:otherwise>
		    	  			    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Login</a>
		               	  	</c:otherwise>
		               	  </c:choose>
                       
                   </li>
                   <li class="nav-item">
                   		<c:choose>
	               	  	<c:when test="${key_name eq 'register'}">
	               	  		<a class="nav-link active" style="font-size: 16px;" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Register</a>
	               	  	</c:when>
	               	  	<c:otherwise>	
	               	  		<a class="nav-link" style="font-size: 16px;" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Register</a>
	               	  	</c:otherwise>
	               	  </c:choose>
                   </li>
               </ul>
               <br>
               <div class="tab-content" id="myTabContent">
               	  <c:choose>
               	  	<c:when test="${key_name eq 'register'}">
	                   <div class="tab-pane fade" id="home" role="tabpanel" aria-labelledby="home-tab">
               	  	</c:when>
               	  	<c:otherwise>
               	  		<div class="tab-pane fade active show" id="home" role="tabpanel" aria-labelledby="home-tab">
               	  	</c:otherwise>
               	  </c:choose>
                   		<h3 class="register-heading">Login for Survey</h3>
                   		<form class="form-horizontal" action="./logincontroller" name="loginform" id="loginform" method="post">
	                       <div class="row register-form">
	                           <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
	                               <div class="form-group">
	                               </div>
	                           </div>
	                           <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
	                               <div class="form-group">
	                                   <input type="email" class="form-control validate[required,custom[email]]" id="trans_email_id" name="trans_email_id" placeholder="Your Email *" value="">
	                               </div>
	                               <button class="btnRegister" type="submit" style="font-weight: bolder;background-color: #000000;color: #d59d2b;">
										Login&nbsp;&nbsp;<i class="fa fa-sign-in" aria-hidden="true"></i>
									</button>
	                           </div>
                       		</div>
                       	</form>
                   </div>
                   <c:choose>
               	  	<c:when test="${key_name eq 'register'}">
	                   <div class="tab-pane fade active show" id="profile" role="tabpanel" aria-labelledby="profile-tab">
               	  	</c:when>
               	  	<c:otherwise>
    	  			    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
               	  	</c:otherwise>
               	  </c:choose>
                       <h3 class="register-heading">Register as a User</h3>
                       <form class="form-horizontal" action="./register" id="registerform" name="registerform" method="post">
	                       <div class="row register-form">
	                           <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
	                               <div class="form-group">
	                               </div>
	                           </div>
	                           <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
	                               <div class="form-group">
	                                   <input type="text" class="form-control validate[required]" id="firstname" name="firstname" placeholder="First Name *" value="">
	                               </div>
	                               <div class="form-group">
	                                   <input type="text" class="form-control validate[required]" id="lastname" name="lastname" placeholder="Last Name *" value="">
	                               </div>
	                               <div class="form-group">
	                                   <input type="email" class="form-control validate[required,custom[email]]" id="email_id" name="email_id" placeholder="Email *" value="">
	                               </div>
	                               <button class="btnRegister" type="submit" style="font-weight: bolder;background-color: #000000;color: #d59d2b;">
										Register&nbsp;&nbsp;<i class="fa fa-user-plus" aria-hidden="true"></i>
									</button>
	                           </div>
	                       </div>
	                  </form>
                   </div>
                   <div align="center">
		               <label style="color: #F06085;font-weight: bolder;" id="info_test">
						  ${mail_status}
						  ${invalidusername}
						  ${register_msg}
		               </label>
	               </div>
               </div>
           </div>
       </div>
   </div>
			
	<!--- Jquery File-------->
	<script type="text/javascript" src="resources/web-plugins/jquery/jquery-3.3.1.min.js"></script>
	<!---- Bootstrap Js File ------->
	<script type="text/javascript" src="resources/web-plugins/Validation_Engine/languages/jquery.validationEngine-en.js"></script>
	<script type="text/javascript" src="resources/web-plugins/Validation_Engine/jquery.validationEngine.js"></script>
	<script type="text/javascript" src="resources/web-plugins/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">

		$( "#loginform" ).submit(function( event ) {
			if(!$('#loginform').validationEngine('validate')) 
			{
				  event.preventDefault();
				return false;
			}
		});

		$( "#registerform" ).submit(function( event ) {
			if(!$('#registerform').validationEngine('validate')) 
			{
				  event.preventDefault();
				return false;
			}
		});
	</script>
</body>
</html>