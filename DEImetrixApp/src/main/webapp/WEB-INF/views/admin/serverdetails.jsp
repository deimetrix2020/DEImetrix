<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link rel="icon" type="image/png" href="resources/img/logo.png" />
        <title>Server Configuration</title>
    </head>
    <body class="sb-nav-fixed">
    <jsp:include page="../common/header.jsp"></jsp:include>
        <nav class="sb-topnav navbar navbar-expand shadow navbar-light bg-light">
            <img src="resources/img/logo.png" alt="DEImetrix" width="200">
            <button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
                <div class="input-group">
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ml-auto ml-md-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                        <a class="dropdown-item" href="#">${sessionScope.common.sName}</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="./logout">Logout</a>
                    </div>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-light" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading" style="font-size: 17px;">Masters</div>
                            <a class="nav-link" style="font-size: 16px;" style="font-size: 16px;" href="./reg_user_list">
                                <span class="sb-nav-link-icon"><i class="fas fa-users" style="font-size: 16px;"></i></span>
                                Registered Users
                            </a>
                            <a class="nav-link" style="font-size: 16px;" href="./server_config">
                                <span class="sb-nav-link-icon"><i class="fas fa-server" style="font-size: 16px;"></i></span>
                                Server Configuration
                            </a>
                            <a class="nav-link" style="font-size: 16px;" href="./demographics">
                                <span class="sb-nav-link-icon"><i class="fas fa-table" style="font-size: 16px;"></i></span>
                                Demographics
                            </a>
                            <a class="nav-link" style="font-size: 16px;" href="./domains">
                                <span class="sb-nav-link-icon"><i class="fas fa-table" style="font-size: 16px;"></i></span>
                                Domains
                            </a>
                            <a class="nav-link" style="font-size: 16px;" href="./dimensions">
                                <span class="sb-nav-link-icon"><i class="fas fa-table" style="font-size: 16px;"></i></span>
                                Dimensions
                            </a>
                            <a class="nav-link" style="font-size: 16px;" href="./survey_questions">
                                <span class="sb-nav-link-icon"><i class="fas fa-question-circle" style="font-size: 16px;"></i></span>
                                Survey Questionnaire
                            </a>
                            <a class="nav-link" style="font-size: 16px;" href="./invite_list">
                                <span class="sb-nav-link-icon"><i class="fas fa-question-circle" style="font-size: 16px;"></i></span>
                                Invite Users
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as:</div>
                        ${sessionScope.common.sName}
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
            	<header class="page-header page-header-dark bg-gradient-primary-to-secondary pb-10">
                    <div class="container">
                        <div class="page-header-content pt-4">
                            <div class="row align-items-center justify-content-between">
                                <div class="col-auto mt-4">
                                    <h1 class="page-header-title">
                                        <div class="page-header-icon"><i data-feather="bar-chart"></i></div>
                                        Server Configuration
                                    </h1>
                                    <div class="page-header-subtitle"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </header>
                 <div class="container mt-n10">
                    <!-- Area chart example-->
                    <div class="row">
                    	<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
	                    	<div class="card mb-4">
		                        <div class="card-header bg-secondary" style="color: #000000;font-weight: bolder;">Mail Configuration</div>
		                        <form id="maildirctory" name="maildirctory">
			                        <div class="card-body">
			                         	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<label class="cus-label">From Mail Id</label>
											<div class="input-group mb-3">
											  <div class="input-group-prepend">
											    <span class="input-group-text" id="basic-addon1"><i class="fa fa-envelope-o" aria-hidden="true"></i></span>
											  </div>
											  <input type="text" id="frommailid" name="frommailid" class="form-control validate[required,custom[email]]" value="${requestScope.serverconfig.sFromMailId}" autocomplete="off" placeholder="Email Id" aria-describedby="basic-addon1">
											  <input type="hidden" value="${requestScope.serverconfig.id}" id="serverconfigid" name="serverconfigid">
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<label class="cus-label">Mail Password</label>
											<div class="input-group mb-3">
											  <div class="input-group-prepend">
											    <span class="input-group-text" id="basic-addon1"><i class="fa fa-key" aria-hidden="true"></i></span>
											  </div>
											  <input type="password" id="mailpasswsord" name="mailpasswsord" class="form-control validate[required]" value="${requestScope.serverconfig.sFromMailPassword}" autocomplete="off" placeholder="Email Password" aria-describedby="basic-addon1">
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<label class="cus-label">SMTP Host Name</label>
											<div class="input-group mb-3">
											  <div class="input-group-prepend">
											    <span class="input-group-text" id="basic-addon1"><i class="fa fa-anchor" aria-hidden="true"></i></span>
											  </div>
											  <input type="text" id="smtphostname" name="smtphostname" class="form-control validate[required]" value="${requestScope.serverconfig.sSMTPHostName}" autocomplete="off" placeholder="Email SMTP" aria-describedby="basic-addon1">
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<label class="cus-label">SMTP Port</label>
											<div class="input-group mb-3">
											  <div class="input-group-prepend">
											    <span class="input-group-text" id="basic-addon1"><i class="fa fa-microchip" aria-hidden="true"></i></span>
											  </div>
											  <input type="text" id="smtpport" name="smtpport" class="form-control validate[required,custom[integer]]" value="${requestScope.serverconfig.sSMTPHostNumber}" autocomplete="off" placeholder="Email SMTP Host" aria-describedby="basic-addon1">
											</div>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<label class="cus-label">Verification timeout (in minutes)</label>
											<div class="input-group mb-3">
											  <div class="input-group-prepend">
											    <span class="input-group-text" id="basic-addon1"><i class="fa fa-clock-o" aria-hidden="true"></i></span>
											  </div>
											  <input type="text" id="verifytimeout" name="verifytimeout" class="form-control validate[required,custom[integer],max[60]]" value="${requestScope.serverconfig.iTimeout}" autocomplete="off" placeholder="Mail verification timout" aria-describedby="basic-addon1">
											</div>
										</div><br/>
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="right">
											<button class="btn btn-md" type="button" onclick="mailformsubmit()" style="font-weight: bolder;background-color: #000000;color: #d59d2b;">
												<i class="fa fa-cloud" aria-hidden="true"></i>&nbsp;&nbsp;Update Mail Config
											</button>
										</div>
			                        </div>
			                        <div class="card-footer medium text-muted" align="center">
			                        	<label id="mailupdatesucssBandMsg" class="errorBandSucsMsg hiddenClass" align="center" style="color: #208e35; font-weight: bolder;display: none;">
											Record Updated Successfully.
										</label>
										<label id="mailupdateerrBandMsg" class="errorBandErrMsg hiddenClass" align="center" style="color: #ce1430; font-weight: bolder;display: none;">
											Record Not Saved.  Please try again.
										</label>
										<label class="control-label labelTxt" style="padding-left: 5px;" id="userlabel"></label>
			                        </div>
			                     </form>
		                    </div>
	                  	</div>
	                  	<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
	                    	<div class="card mb-4">
		                        <div class="card-header bg-secondary" style="color: #000000;font-weight: bolder;">Admin Credentials</div>
		                        <form id="admindirctory" name="admindirctory">
			                        <div class="card-body">
			                         	<label class="cus-label">User Name</label>
			                            <div class="input-group mb-3">
										  <div class="input-group-prepend">
										    <span class="input-group-text" id="basic-addon1"><i class="fa fa-user" aria-hidden="true"></i></span>
										  </div>
										  <input type="text" class="form-control validate[required]" autocomplete="off" value="${requestScope.adminlogindetails.sUserName}" id="username" name="username" placeholder="User Name" aria-describedby="basic-addon1">
										  <input type="hidden" value="${requestScope.adminlogindetails.id}" id="adminuserid" name="adminuserid">
										</div>
										<label class="cus-label">Password</label>
			                            <div class="input-group mb-3">
										  <div class="input-group-prepend">
										    <span class="input-group-text" id="basic-addon1"><i class="fa fa-key" aria-hidden="true"></i></span>
										  </div>
										  <input type="password" class="form-control validate[minSize[8],maxSize[15]]" id="userpassword" name="userpassword" placeholder="Password" aria-describedby="basic-addon1">
										</div>
										<label class="cus-label">Confirm Password</label>
			                            <div class="input-group mb-3">
										  <div class="input-group-prepend">
										    <span class="input-group-text" id="basic-addon1"><i class="fa fa-key" aria-hidden="true"></i></span>
										  </div>
										  <input type="password" class="form-control validate[equals[userpassword],minSize[8],maxSize[15]]" id="conformpassword" name="conformuserpassword" placeholder="Confirm Password" aria-describedby="basic-addon1">
										</div>
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: 20px;" align="right">
											<button class="btn btn-md" type="button" onclick="adminformsubmit()" style="font-weight: bolder;background-color: #000000;color: #d59d2b;">
												<i class="fa fa-cloud" aria-hidden="true"></i>&nbsp;&nbsp;Update Admin Details
											</button>
										</div>
			                        </div>
			                        <div class="card-footer medium text-muted" align="center">
			                        	<label id="adminupdatesucssBandMsg" class="errorBandSucsMsg hiddenClass" align="center" style="color: #208e35; font-weight: bolder;display: none;">
											Record Updated Successfully.
										</label>
										<label id="adminupdateerrBandMsg" class="errorBandErrMsg hiddenClass" align="center" style="color: #ce1430; font-weight: bolder;display: none;">
											Record Not Saved.  Please try again.
										</label>
			                        </div>
			                     </form>
		                    </div>
	                  	</div>        
                    </div>
                    <div class="row">
                    	<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
	                    	<div class="card mb-4">
		                        <div class="card-header bg-secondary" style="color: #000000;font-weight: bolder;">Survey Questions Maintenance Period</div>
		                        <form id="surveydirctory" name="surveydirctory">
			                        <div class="card-body">
			                         	
			                         	<label class="cus-label">DateTime Range</label>
			                            <div class="input-group mb-3">
										  <div class="input-group-prepend">
										    <span class="input-group-text" id="basic-addon1"><i class="fa fa-calendar-check-o" aria-hidden="true"></i></span>
										  </div>
										  <input type="text" class="form-control validate[required]" readonly="readonly" 
										  	autocomplete="off" id="question_start_date" name="question_start_date" 
										  	placeholder="Date Range" aria-describedby="basic-addon1"
										  	value="<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${requestScope.serverconfig.dMaintainStartDate}" pattern='MM/dd/yyyy HH:mm'/> - <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${requestScope.serverconfig.dMaintainEndDate}" pattern='MM/dd/yyyy HH:mm'/>" />
	  									  <input type="hidden" value="${requestScope.serverconfig.id}" id="configid" name="configid">
										</div>
					                  	<label class="cus-label">Alert showing hour</label>
			                            <div class="input-group mb-3">
										  <div class="input-group-prepend">
										    <span class="input-group-text" id="basic-addon1"><i class="fa fa-clock-o" aria-hidden="true"></i></span>
										  </div>
										  <input type="text" id="ques_timeout" autocomplete="off" name="ques_timeout" class="form-control validate[required,custom[integer],max[24]]" 
			   										value="${requestScope.serverconfig.iQuestions_Timeout}" placeholder="Alert time" aria-describedby="basic-addon1">
										</div>
										
										<label class="cus-label">Survey total minutes</label>
			                            <div class="input-group mb-3">
										  <div class="input-group-prepend">
										    <span class="input-group-text" id="basic-addon1"><i class="fa fa-clock-o" aria-hidden="true"></i></span>
										  </div>
										  <input type="text" id="ques_total_time" autocomplete="off" name="ques_total_time" class="form-control validate[required,custom[integer],min[5],max[60]]" 
			   										value="${requestScope.serverconfig.iTotalSurveyTime}" placeholder="Total Minutes" aria-describedby="basic-addon1">
										</div>
					                  	
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: 20px;" align="right">
											<button class="btn btn-md" type="button" onclick="surveyformsubmit()" style="font-weight: bolder;background-color: #000000;color: #d59d2b;">
												<i class="fa fa-cloud" aria-hidden="true"></i>&nbsp;&nbsp;Update Maintenance 
											</button>
										</div>
			                        </div>
			                        <div class="card-footer medium text-muted" align="center">
			                        	<label id="surveyupdatesucssBandMsg" class="errorBandSucsMsg hiddenClass" align="center" style="color: #208e35; font-weight: bolder;display: none;">
											Record Updated Successfully.
										</label>
										<label id="surveyupdateerrBandMsg" class="errorBandErrMsg hiddenClass" align="center" style="color: #ce1430; font-weight: bolder;display: none;">
											Record Not Saved.  Please try again.
										</label>
			                        </div>
			                     </form>
		                    </div>
	                  	</div>
                    </div>
                </div>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">&copy; 2020 <a href="https://ingenium.global/" target="_blank">Ingenium Global.</a> ALL RIGHTS RESERVED.</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
    </body>
    <script type="text/javascript">
		 // Toggle the side navigation
		    (function($) {
		    "use strict";
		
		    // Add active state to sidbar nav links
		    var path = window.location.href; // because the 'href' property of the DOM element is the absolute path
		        $("#layoutSidenav_nav .sb-sidenav a.nav-link").each(function() {
		            if (this.href === path) {
		                $(this).addClass("active");
		            }
		        });
		
		    // Toggle the side navigation
		    $("#sidebarToggle").on("click", function(e) {
		        e.preventDefault();
		        $("body").toggleClass("sb-sidenav-toggled");
		    });
		})(jQuery);

    	$(function() 
    	{
        	console.log("${server_date}");
        	
		    var today = "${server_date}";
				
		  	$('input[name="question_start_date"]').daterangepicker({
			    timePicker: true,
			    minDate:today,
			    showTimezone: true,
			    locale: {
			      format: 'MM/DD/YYYY HH:mm'
			    }
		  	});
		});
		 
		/*Server Configuration Details*/
		function adminformsubmit()
		{
			if(!$('#admindirctory').validationEngine('validate'))
			{
				return false;
			}
			var adform = $("#admindirctory").serialize();
			$("#admindirctory :input").prop("disabled", true);
			$.ajax({
				type:'POST',
				url: "./serverconfig/admincreateRecord",
				data: adform,
				success: function(data)
				{
					$("#admindirctory :input").prop("disabled", false);
					if(data)
					{
						$("#adminupdatesucssBandMsg").show();
						setTimeout(function() {
					         $("#adminupdatesucssBandMsg").fadeOut(100);
					         location.reload();
					      }, 1000);
						$("#adminupdateerrBandMsg").css("display","none");
					} else
					{
						$("#adminupdatesucssBandMsg").show();
						setTimeout(function() {
					         $("#adminupdateerrBandMsg").fadeOut(100);
					      }, 1000);
						$("#adminupdatesucssBandMsg").css("display","none");
					}
				}
			});
		}

		function surveyformsubmit()
		{
			if(!$('#surveydirctory').validationEngine('validate'))
			{
				return false;
			}
			var survey_form = $("#surveydirctory").serialize();
			$("#surveydirctory :input").prop("disabled", true);
			$.ajax({
				type:'POST',
				url: "./serverconfig/surveycreateRecord",
				data: survey_form,
				success: function(data)
				{
					$("#surveydirctory :input").prop("disabled", false);
					if(data)
					{
						$("#surveyupdatesucssBandMsg").show();
						setTimeout(function() {
					         $("#surveyupdatesucssBandMsg").fadeOut(100);
					         location.reload();
					      }, 1000);
						$("#surveyupdateerrBandMsg").css("display","none");
					} else
					{
						$("#surveyupdateerrBandMsg").show();
						setTimeout(function() {
					         $("#surveyupdateerrBandMsg").fadeOut(100);
					      }, 1000);
						$("#surveyupdatesucssBandMsg").css("display","none");
					}
				}
			});
		}
		
		function mailformsubmit()
		{
			if(!$('#maildirctory').validationEngine('validate'))
			{
				return false;
			}
			var adform = $("#maildirctory").serialize();
			$("#maildirctory :input").prop("disabled", true);
			$.ajax({
				type:'POST',
				url: "./serverconfig/mailcreateRecord",
				data: adform,
				success: function(data)
				{
					$("#maildirctory :input").prop("disabled", false);
					if(data)
					{
						$("#mailupdatesucssBandMsg").show();
						setTimeout(function() {
					         $("#mailupdatesucssBandMsg").fadeOut(100);
					         location.reload();
					      }, 1000);
						$("#mailupdateerrBandMsg").css("display","none");
					} else
					{
						$("#mailupdateerrBandMsg").show();
						setTimeout(function() {
					         $("#mailupdateerrBandMsg").fadeOut(100);
					         location.reload();
					      }, 1000);
						$("#mailupdatesucssBandMsg").css("display","none");
					}
				}
			});
		}
		
		$( "input" ).click(function() 
		{
			 $('#maildirctory').validationEngine('hide');
			 $('#admindirctory').validationEngine('hide'); 
		});
	</script>
</html>