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
        <title>Domain</title>
        <link rel="icon" type="image/png" href="resources/img/fav_icon.png" />
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
                            <span class="sb-sidenav-menu-heading" style="font-size: 17px;">Masters</span>
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
                                        Domain
                                    </h1>
                                    <div class="page-header-subtitle"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </header>
                 
                 <div class="container mt-n10">
                    <!-- Area chart example-->
                    <div class="card mb-4">
                        <div class="card-header bg-secondary" style="color: #000000;font-weight: bolder;">Add/Update</div>
                        <form id="domainform" name="domainform">
	                        <div class="card-body">
	                         	<label class="cus-label">Domain</label>
	                            <div class="input-group mb-3">
								  <div class="input-group-prepend">
								    <span class="input-group-text" id="basic-addon1"><i class="fa fa-terminal" aria-hidden="true"></i></span>
								  </div>
								  <input type="text" id="domain_txt" name="domain_txt" class="form-control validate[required]" autocomplete="off" aria-label="Domain" placeholder="Domain" aria-describedby="basic-addon1">
								  <input type="hidden" id="domain_id" name="domain_id" class="form-control">
									<input type="hidden" id="domain_status" name="domain_status" class="form-control">
								</div>
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: 20px;" align="right">
									<button id="add" class="btn btn-md" type="button" onclick="domainformsubmit()" style="font-weight: bolder;background-color: #000000;color: #d59d2b;">
										<i class="fa fa-cloud" aria-hidden="true"></i>&nbsp;&nbsp;Add/Update Record
									</button>
								</div>
	                        </div>
	                        <div class="card-footer medium text-muted" align="center">
	                        	<label id="sucssBandMsg" class="errorBandSucsMsg hiddenClass" align="center" style="color: #14ABFF; font-weight: bolder;display: none;">
									Record Added Successfully.
								</label>
								<label id="errBandMsg" class="errorBandErrMsg hiddenClass" align="center" style="color: #14ABFF; font-weight: bolder;display: none;">
									Record Not Saved.  Please try again.
								</label>
								<label id="updatesucssBandMsg" class="errorBandSucsMsg hiddenClass" align="center" style="color: #14ABFF; font-weight: bolder;display: none;">
									Record Updated Successfully.
								</label>
								<label id="updateerrBandMsg" class="errorBandErrMsg hiddenClass" align="center" style="color: #14ABFF; font-weight: bolder;display: none;">
									Record Not Saved.  Please try again.
								</label>
								<label class="control-label labelTxt" style="padding-left: 5px;" id="userlabel"></label>
	                        </div>
	                     </form>
                    </div>
                    <div class="row">
                        <div class="table table-responsive loaderclass col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<table id="datatable" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
								<thead style="font-size: 13px;">
									<tr class="bg-secondary" style="color: #000000;font-weight: bolder;text-align: center;">
										<th class="labelsize">S.No</th>
						            	<th class="labelsize">Domain</th>
						            	<th class="labelsize">Created Date</th>
						            	<th class="labelsize">Created By</th>
										<th class="labelsize">Modified Date</th>
										<th class="labelsize">Modified By</th>
						            	<th class="labelsize">Actions</th>
									</tr>
								</thead>
								<tbody class="userdatatable">
						        	<c:forEach items="${requestScope.domain_list}" var="domainlist" varStatus="counter">
							            <tr>
							            	<td class="labelsize" style="text-align: center;">${counter.count}</td>
							            	<td class="labelsize">${domainlist.sDomainName}</td>
							            	<td class="labelsize">
							            		<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${domainlist.dCreatedDate}" pattern='MM-dd-yyyy HH:mm:ss'/>
							            	</td>
							            	<td class="labelsize">${domainlist.sCreatedBy}</td>
							            	<td class="labelsize">
							            		<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${domainlist.dUpdatedDate}" pattern='MM-dd-yyyy HH:mm:ss'/>
							            	</td>
							            	<td class="labelsize">${domainlist.sUpdatedBy}</td>
							            	<td class="labelsize" align="center">
												<a onclick="retrivedomainfrm('${domainlist.id}')" title="Click to edit record" href="#" style="color:#ffc107;">
													<i class="fa fa-wrench" aria-hidden="true"></i>
												</a>
												<a onclick="deletedomainfrm('${domainlist.id}', 'soft')" href="#" title="Delete" style="color:#FF0000;margin-left: 10px;">
													<i class="fa fa-minus-square" aria-hidden="true"></i>
												</a>
											</td>
							            </tr>
							         </c:forEach>
						        </tbody>
							</table>
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

		$(document).ready(function() {
			$('#datatable').DataTable();

			var lastValue = '';
			$("#domain_txt").on('change keyup paste mouseup', function() {
			    if ($(this).val() != lastValue) 
				{
			        lastValue = $(this).val();

			        var value_val = lastValue.replace(/['"`]/g, '');
					$('#domain_txt').val(value_val);
			        
			        var domain_form = $("#domainform").serialize();
			        $.ajax({
						type:'POST',
						url: "./domain_controller/check_duplicate",	
						data: domain_form,
						success: function(data)
						{
							$("#domainform :input").prop("disabled", false);
							if(data)
							{
								$("#domain_status").val(data);
							} else
							{
								$("#domain_status").val(data);
							}
						}
					});    
			    }
			});
		});

		/*Add*/
		function domainformsubmit()
		{
			if($("#domain_id").val().trim() == "")
			{
				if($("#domain_status").val() == "true")
				{
					$('#domain_txt').validationEngine('showPrompt', 'This Domain already exists.', 'load');
					return false;
				} else
				{
					$('#domain_txt').validationEngine('hide');				
				}
			}
			
			if(!$('#domainform').validationEngine('validate')) 
			{
				return false;
			}
			var domain_form = $("#domainform").serialize();
			$("#domainform :input").prop("disabled", true);
			$.ajax({
				type:'POST',
				url: "./domain_controller/createRecord",	
				data: domain_form,
				success: function(data)
				{
					$("#domainform :input").prop("disabled", false);
					if(data == 1)
					{
						$("#sucssBandMsg").show();
						$("#errBandMsg").css("display","none");
						setTimeout(function() {
							location.reload();
					      }, 1000);
					} else if(data == 2)
					{
						$("#errBandMsg").show();
						$("#sucssBandMsg").css("display","none");
					} else if(data == 3)
					{
						$.confirm({
							title: 'Alert',
							content: 'Sorry, the Master data should be edited only during Maintenance period..',
							type: 'red',
							typeAnimated: true,
							buttons: {
								tryAgain: 
								{
									text: '&nbsp;&nbsp;OK&nbsp;&nbsp;',
									btnClass: 'btn-red',
									action: function()
									{}
								}
							}
						});
					}
				}
			});
		}

		function retrivedomainfrm(id)
		{
			$.ajax({
				type:'POST',
				url: "./domain_controller/retriveRecord",	
				data: {"id" : id},
				success: function(data)
				{
					$("#domain_id").val(data.id);
					$("#domain_txt").val(data.sDomainName);
				}
			});
		}

		function deletedomainfrm(id, key)
		{
			$.confirm({
				title: 'Delete Record',
				content: 'Are you sure you want to delete this record?',
				type: 'red',
				typeAnimated: true,
				buttons: {
				tryAgain: {
					text: '&nbsp;&nbsp;OK&nbsp;&nbsp;',
					btnClass: 'btn-red',
					action: function()
					{
						deleterecord(id, key);
					}
				},
				close: function () { } }
			});
		}

		function deleterecord(id, key)
		{
			$.ajax({
				type:'POST',
				url: "./domain_controller/deleteRecord",	
				data: {"domain_id" : id, "key" : key},
				success: function(data)
				{
					if(data == 1)
					{
						setTimeout(function() {
							location.reload();
					      }, 1000);
					} else if(data == 3)
					{
						$.confirm({
							title: 'Alert',
							content: 'Sorry, the Master data should be edited only during Maintenance period..',
							type: 'red',
							typeAnimated: true,
							buttons: {
								tryAgain: 
								{
									text: '&nbsp;&nbsp;OK&nbsp;&nbsp;',
									btnClass: 'btn-red',
									action: function()
									{}
								}
							}
						});
					}
				}
			});
		}
	</script>
</html>