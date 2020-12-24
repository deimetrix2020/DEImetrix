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
        <title>Registered Users List</title>
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
                                        Registered Users List
                                    </h1>
                                    <div class="page-header-subtitle"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </header>
                 
                 <div class="container mt-n10">
                 		<div style="margin-bottom: 50px;" align="right">
							<a href="./trans_controller/exportallusers" target="_blank" style="font-size: 20px; color: #ffffff; margin-left: 5px;font-weight: bolder;">
								<span class="control-label labelTxt" style="font-size: 15px;">Download All Users</span>&nbsp;&nbsp;<i class="fa fa-arrow-circle-down"></i>
							</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="./trans_controller/export_survey_dump" target="_blank" style="font-size: 20px; color: #ffffff; margin-left: 5px;font-weight: bolder;">
								<span class="control-label labelTxt" style="font-size: 15px">Download Survey Data</span>&nbsp;&nbsp;<i class="fa fa-arrow-circle-down"></i>
							</a>
                    	</div><br>
                    <!-- Area chart example-->
                    <div class="row">
                        <div class="table table-responsive loaderclass col-xs-12 col-sm-12 col-md-12 col-lg-12" style="border-color: #d59d2b">
							<table id="datatable" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
								<thead style="font-size: 13px;">
									<tr class="bg-secondary" style="color: #000000;font-weight: bolder;text-align: center;">
										<th class="labelsize" style="text-align: center;">S.No</th>
						            	<th class="labelsize">First Name</th>
						            	<th class="labelsize">Last Name</th>
						            	<th class="labelsize">Email ID</th>
						            	<th class="labelsize">Email Verification Date</th>
						            	<th class="labelsize">Email Verification</th>
						            	<th class="labelsize">Active</th>
						            	<th class="labelsize">Re-take Survey</th>
						            	<th class="labelsize">Created Date</th>
						            	<th class="labelsize">Created By</th>
										<th class="labelsize">Modified Date</th>
										<th class="labelsize">Modified By</th>
						            	<th class="labelsize">Actions</th>
									</tr>
								</thead>
								<tbody class="userdatatable">
						        	<c:forEach items="${requestScope.users_list}" var="userlist" varStatus="counter">
							            <tr>
							            	<td class="labelsize">${counter.count}</td>
							            	<td class="labelsize">${userlist.sFirstName}</td>
							            	<td class="labelsize">${userlist.sLastName}</td>
							            	<td class="labelsize">${userlist.sEmailId}</td>
							            	<td class="labelsize">
							            		<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${userlist.sEmailVerifiedAt}" pattern='MM-dd-yyyy HH:mm:ss'/>
							            	</td>
							            	<c:choose>
								            	<c:when test="${fn:contains(userlist.cIsEmailVerify,'Y')}">
									            	<td class="labelsize" style="text-align: center;">Yes</td>
								            	</c:when>
								            	<c:otherwise>
								            		<td class="labelsize" style="text-align: center;">No</td>
								            	</c:otherwise>
							            	</c:choose>
							            	<c:choose>
								            	<c:when test="${fn:contains(userlist.cIsActive,'Y')}">
									            	<td class="labelsize" style="text-align: center;">Yes</td>
								            	</c:when>
								            	<c:otherwise>
								            		<td class="labelsize" style="text-align: center;">No</td>
								            	</c:otherwise>
							            	</c:choose>
							            	<c:choose>
								            	<c:when test="${fn:contains(userlist.cIsBannedSurvey,'N')}">
									            	<td class="labelsize" style="text-align: center;">Yes</td>
								            	</c:when>
								            	<c:otherwise>
								            		<td class="labelsize" style="text-align: center;">No</td>
								            	</c:otherwise>
							            	</c:choose>
							            	<td class="labelsize">
							            		<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${userlist.dCreatedDate}" pattern='MM-dd-yyyy HH:mm:ss'/>
							            	</td>
							            	<td class="labelsize">${userlist.sCreatedBy}</td>
							            	<td class="labelsize">
							            		<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${userlist.dUpdatedDate}" pattern='MM-dd-yyyy HH:mm:ss'/>
							            	</td>
							            	<td class="labelsize">${userlist.sUpdatedBy}</td>
							            	<td class="labelsize">
												<a onclick="deleteuserfrm('${userlist.id}', 'soft')" href="#" title="Delete" style="color:#FF0000;margin-left: 10px;">
													<i class="fa fa-minus-square" aria-hidden="true"></i>
												</a>
												<c:choose>
													<c:when test="${fn:contains(userlist.cIsActive,'Y')}">
														<a onclick="deleteuserfrm('${userlist.id}', 'inactive')" href="#" title="Make Inactive" style="color:red;margin-left: 10px;">
															<i class="fa fa-power-off" aria-hidden="true"></i>
														</a>											
													</c:when>
													<c:otherwise>
														<a onclick="deleteuserfrm('${userlist.id}', 'active')" href="#" title="Make Active" style="color:green;margin-left: 10px;">
															<i class="fa fa-power-off" aria-hidden="true"></i>
														</a>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${fn:contains(userlist.cIsBannedSurvey,'Y')}">
														<a onclick="deleteuserfrm('${userlist.id}', 'unbanned')" href="#" title="Allow to re-take Survey" style="color:green;margin-left: 10px;">
															<i class="fa fa-address-card" aria-hidden="true"></i>
														</a>											
													</c:when>
													<c:otherwise>
														<a onclick="deleteuserfrm('${userlist.id}', 'banned')" href="#" title="Don't allow to re-take Survey" style="color:red;margin-left: 10px;">
															<i class="fa fa-address-card" aria-hidden="true"></i>
														</a>
													</c:otherwise>
												</c:choose>
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
		});

		function deleteuserfrm(id, key)
		{
			var messgae = "Are you sure you want to delete this record?";
			var title = "Delete Record";
			if(key == 'active' || key == 'inactive')
			{
				title = "Status Change";
				messgae = "Are you sure you want to change the status this record?";
			} else if(key == 'banned')
			{
				title = "Re-take Survey";
				messgae = "Are you sure you don't want to allow this user to re-take the Survey?";
			} else if(key == 'unbanned')
			{
				title = "Re-take Survey";
				messgae = "Are you sure you want to allow this user to re-take the Survey?";
			}
			
			$.confirm({
				title: title,
				content: messgae,
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
				url: "./trans_controller/deleteRecord",	
				data: {"user_id" : id, "key" : key},
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