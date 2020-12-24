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
               <ul class="nav nav-tabs nav-justified" id="myTab" role="tablist" style="display: none;">
                   <li class="nav-item">
                       <a class="nav-link active" style="font-size: 16px;" id="adminprofile-tab" data-toggle="tab" href="#admin_profile" role="tab" aria-controls="admin_profile" aria-selected="false">Admin</a>
                   </li>
               </ul>
               <br>
               <div class="tab-content" id="myTabContent">
                   <div class="tab-pane fade active show" id="admin_profile" role="tabpanel" aria-labelledby="adminprofile-tab">
                       <h3 class="register-heading">Login as an Admin</h3>
                       <form class="form-horizontal" action="./logincontroller" id="admin_loginform" name="admin_loginform" method="post">
	                       <div class="row register-form">
	                           <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
	                               <div class="form-group">
	                               </div>
	                           </div>
	                           <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
	                               <div class="form-group">
	                                   <input type="text" id="adminusername" name="adminusername" class="form-control validate[required]" placeholder="User Name *" value="">
	                               </div>
	                               <div class="form-group">
	                                   <input type="password" class="form-control validate[required,minSize[8],maxSize[15]]" id="adminpasswordname" name="adminpasswordname" placeholder="Password *" value="">
	                               </div>
	                               <button class="btnRegister" type="submit" style="font-weight: bolder;background-color: #000000;color: #d59d2b;">
										Admin Login&nbsp;&nbsp;<i class="fa fa-sign-in" aria-hidden="true"></i>
									</button>
	                           </div>
	                       </div>
	                   </form>
                   </div>
                   
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
			
	<!--- Jquery File-------->
	<script type="text/javascript" src="resources/web-plugins/jquery/jquery-3.3.1.min.js"></script>
	<!---- Bootstrap Js File ------->
	<script type="text/javascript" src="resources/web-plugins/Validation_Engine/languages/jquery.validationEngine-en.js"></script>
	<script type="text/javascript" src="resources/web-plugins/Validation_Engine/jquery.validationEngine.js"></script>
	<script type="text/javascript" src="resources/web-plugins/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">

		$( "#admin_loginform" ).submit(function( event ) {
			if(!$('#admin_loginform').validationEngine('validate')) 
			{
				  event.preventDefault();
				return false;
			}
		});
		
		$("#info_test").show();
		setTimeout(function() {
			$("#info_test").css("display","none");
	      }, 3000);
		
	</script>
</body>
</html>