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
        <title>Survey Questions</title>
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
                            <a class="nav-link" style="font-size: 16px;" href="./reg_user_list">
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
                                        Survey Questions
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
                        <form id="questionform" name="questionform">
	                        <div class="card-body">
	                        	<div class="row">
		                        	<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
										<label class="cus-label">Domain</label>
										<div class="inner-addon left-addon">
											<select class="form-control validate[required]" id="domain_val" name="domain_val" onchange="domainChange()">
												<option value="">-- Select Domain --</option>
												<c:forEach items="${requestScope.domain_list}" var="levelinfo">
													<option value="${levelinfo.id}">${levelinfo.sDomainName}</option>
												</c:forEach>
											</select>
											<input type="hidden" id="question_id" name="question_id" class="form-control">
											<input type="hidden" id="question_status" name="question_status" class="form-control">
										</div>
									</div>
									<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
										<label class="cus-label">Dimensions</label>
										<div class="inner-addon left-addon">
											<select class="form-control validate[required]" id="dimension_val" name="dimension_val" onchange="dimensionChange(this);">
												<option value="">-- Select Dimension --</option>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<label class="cus-label">Survey Question</label>
			                            <div class="input-group mb-3">
										  <div class="input-group-prepend">
										    <span class="input-group-text" id="basic-addon1"><i class="fa fa-terminal" aria-hidden="true"></i></span>
										  </div>
										  <input type="text" id="survey_ques_txt" name="survey_ques_txt" class="form-control validate[required]" autocomplete="off" placeholder="Question" aria-describedby="basic-addon1">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
										<label class="cus-label">Response 1</label>
										<div class="input-group mb-3">
										  <div class="input-group-prepend">
										    <span class="input-group-text" id="basic-addon1"><i class="fa fa-terminal" aria-hidden="true"></i></span>
										  </div>
										  <input type="text" id="response1_txt" name="response1_txt" class="form-control validate[required]" autocomplete="off" placeholder="Response 1" aria-describedby="basic-addon1">
										</div>
									</div>
									<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5" style="margin-top: 25px;">
										<div class="inner-addon left-addon">
											<label class="cus-label">DEIQ Oriented?</label>
											<input type="checkbox" class="validate[required] checkbox" id="response1_val" name="response_val_name" autocomplete="off" style="margin-left: 10px;">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
										<label class="cus-label">Response 2</label>
										<div class="input-group mb-3">
										  <div class="input-group-prepend">
										    <span class="input-group-text" id="basic-addon1"><i class="fa fa-terminal" aria-hidden="true"></i></span>
										  </div>
										  <input type="text" id="response2_txt" name="response2_txt" class="form-control validate[required]" autocomplete="off" placeholder="Response 2" aria-describedby="basic-addon1">
										</div>
									</div>
									<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5" style="margin-top: 25px;">
										<div class="inner-addon left-addon">
											<label class="cus-label">DEIQ Oriented?</label>
											<input type="checkbox" class="validate[required] checkbox" id="response2_val" name="response_val_name" style="margin-left: 10px;">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
										<div class="inner-addon left-addon">
											<label class="cus-label">Question for Leader?</label>
											<input type="checkbox" class="checkbox" id="leader_val" name="leader_val" style="margin-left: 10px;">
										</div>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
										<div class="inner-addon left-addon">
											<label class="cus-label">Active?</label>
											<input type="checkbox" class="checkbox" id="active_val" name="active_val" checked="checked" style="margin-left: 10px;">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: 20px;" align="right">
										<button id="add" class="btn btn-md" type="button" onclick="quesitionsformsubmit()" style="font-weight: bolder;background-color: #000000;color: #d59d2b;">
											<i class="fa fa-cloud" aria-hidden="true"></i>&nbsp;&nbsp;Add/Update Record
										</button>
									</div>
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
                    <div class="card mb-4">
                        <div class="card-header bg-secondary" style="color: #000000;font-weight: bolder;">Download & Upload</div>
                        <div class="row">
							<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5" style="display: flex;margin: 20px;">
								<input type="file" class="filestyle" data-size="sm" id="upload_csv_file" name="upload_csv_file" data-buttonName="btn btn-warning btn-lg">
								<button class="btn btn-success uploadfile_btn" title="Upload the file" type="button" onclick="uploadCSVFile()" style="font-weight: bolder; height: 32px; margin-top: 1px; margin-left: 10px; width: 42px;">
									<i class="fa fa-upload"></i>
								</button>
								<img id="checkloading" alt="Loading" src="resources/img/loader.gif" style="width: 30px;height: 30px;display: none;">
								<a href="./question_controller/downloadformat" target="_blank" style="font-size: 20px;margin-top: 7px;margin-left: 20px;" title="Download the template">
									<i class="fa fa-download" style="color: red;"></i>
								</a>
							</div>
							<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5" style="margin: 20px;" align="right">
								<a href="./question_controller/exportallquestions" target="_blank" style="font-size: 20px; color: #d59d2b; margin-left: 5px;font-weight: bolder;">
									<span class="control-label labelTxt" style="font-size: 15px">Download All Questions</span>&nbsp;&nbsp;<i class="fa fa-arrow-circle-down"></i>
								</a>
							</div>
                        </div>
						<div class="card-footer medium text-muted" align="center">
                        	<label class="control-label labelTxt" style="padding-left: 5px;" id="result_label"></label>
                        </div>
                    </div>
                    <div class="card mb-4" style="background-color: transparent;border: none;">
	                    <div align="right">
							
						</div>                        
                    </div>
                    <div class="row">
                        <div class="table table-responsive loaderclass col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<table id="datatable" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
								<thead style="font-size: 13px;">
									<tr class="bg-secondary" style="color: #000000;font-weight: bolder;text-align: center;">
										<th class="labelsize">S.No</th>
						            	<th class="labelsize">Domain</th>
						            	<th class="labelsize">Dimension</th>
						            	<th class="labelsize">Survey Questions</th>
						            	<th class="labelsize">Response 1</th>
						            	<th class="labelsize">DEIQ Oriented 1?</th>
						            	<th class="labelsize">Response 2</th>
						            	<th class="labelsize">DEIQ Oriented 2?</th>
						            	<th class="labelsize">Question for Leader?</th>
						            	<th class="labelsize">Active</th>
						            	<th class="labelsize">Created Date</th>
						            	<th class="labelsize">Created By</th>
										<th class="labelsize">Modified Date</th>
										<th class="labelsize">Modified By</th>
						            	<th class="labelsize">Actions</th>
									</tr>
								</thead>
								<tbody class="userdatatable">
						        	<c:forEach items="${requestScope.questions_list}" var="queslist" varStatus="counter">
							            <tr>
							            	<td class="labelsize" style="text-align: center;">${counter.count}</td>
							            	<td class="labelsize">${queslist.domainsDO.sDomainName}</td>
							            	<td class="labelsize">${queslist.dimensionsDO.sDimensionsName}</td>
							            	<td class="labelsize">${queslist.sQuestions}</td>
							            	<td class="labelsize">${queslist.sResponse1}</td>
							            	<c:choose>
								            	<c:when test="${fn:contains(queslist.cIsResponse1,'Y')}">
									            	<td class="labelsize" style="text-align: center;">Yes</td>
								            	</c:when>
								            	<c:otherwise>
								            		<td class="labelsize" style="text-align: center;">No</td>
								            	</c:otherwise>
							            	</c:choose>
							            	<td class="labelsize">${queslist.sResponse2}</td>
							            	<c:choose>
								            	<c:when test="${fn:contains(queslist.cIsResponse2,'Y')}">
									            	<td class="labelsize" style="text-align: center;">Yes</td>
								            	</c:when>
								            	<c:otherwise>
								            		<td class="labelsize" style="text-align: center;">No</td>
								            	</c:otherwise>
							            	</c:choose>
							            	<c:choose>
								            	<c:when test="${fn:contains(queslist.cIsLeaderShip,'Y')}">
									            	<td class="labelsize" style="text-align: center;">Yes</td>
								            	</c:when>
								            	<c:otherwise>
								            		<td class="labelsize" style="text-align: center;">No</td>
								            	</c:otherwise>
							            	</c:choose>
							            	<c:choose>
								            	<c:when test="${fn:contains(queslist.cIsActive,'Y')}">
									            	<td class="labelsize" style="text-align: center;">Yes</td>
								            	</c:when>
								            	<c:otherwise>
								            		<td class="labelsize" style="text-align: center;">No</td>
								            	</c:otherwise>
							            	</c:choose>
							            	<td class="labelsize">
							            		<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${queslist.dCreatedDate}" pattern='MM-dd-yyyy HH:mm:ss'/>
							            	</td>
							            	<td class="labelsize">${queslist.sCreatedBy}</td>
							            	<td class="labelsize">
							            		<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${queslist.dUpdatedDate}" pattern='MM-dd-yyyy HH:mm:ss'/>
							            	</td>
							            	<td class="labelsize">${queslist.sUpdatedBy}</td>
							            	<td class="labelsize" align="center">
												<a onclick="retrivequestionfrm('${queslist.id}')" title="Click to edit record" href="#" style="color:#ffc107;">
													<i class="fa fa-wrench" aria-hidden="true"></i>
												</a>
												<a onclick="deletequestionfrm('${queslist.id}', 'soft')" href="#" title="Delete" style="color:#FF0000;margin-left: 10px;">
													<i class="fa fa-minus-square" aria-hidden="true"></i>
												</a>
												<c:choose>
													<c:when test="${fn:contains(queslist.cIsActive,'Y')}">
														<a onclick="deletequestionfrm('${queslist.id}', 'inactive')" href="#" title="Make Inactive" style="color:red;margin-left: 10px;">
															<i class="fa fa-power-off" aria-hidden="true"></i>
														</a>											
													</c:when>
													<c:otherwise>
														<a onclick="deletequestionfrm('${queslist.id}', 'active')" href="#" title="Make Active" style="color:green;margin-left: 10px;">
															<i class="fa fa-power-off" aria-hidden="true"></i>
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

			var lastValue_ques = '';
			$("#survey_ques_txt").on('change keyup paste mouseup', function() {
			    if ($(this).val() != lastValue_ques) 
				{
			    	lastValue_ques = $(this).val();

			        var value_val = lastValue_ques.replace(/["`]/g, '');
					$('#survey_ques_txt').val(value_val);

					var domain_val = $("#domain_val").val();
					var dimension_val = $("#dimension_val").val();

					if(domain_val.trim() !== "" && dimension_val.trim() !== "")
					{
						checking_duplicate();		
					}
				}
			});

			var lastValue_res1 = '';
			$("#response1_txt").on('change keyup paste mouseup', function() {
			    if ($(this).val() != lastValue_res1) 
				{
			    	lastValue_res1 = $(this).val();

			        var value_val = lastValue_res1.replace(/["`]/g, '');
					$('#response1_txt').val(value_val);

					var domain_val = $("#domain_val").val();
					var dimension_val = $("#dimension_val").val();

					if(domain_val.trim() !== "" && dimension_val.trim() !== "")
					{
						checking_duplicate();
					}
				}
			});

			var lastValue_res2 = '';
			$("#response2_txt").on('change keyup paste mouseup', function() {
			    if ($(this).val() != lastValue_res2) 
				{
			    	lastValue_res2 = $(this).val();

			        var value_val = lastValue_res2.replace(/["`]/g, '');
					$('#response2_txt').val(value_val);

					var domain_val = $("#domain_val").val();
					var dimension_val = $("#dimension_val").val();

					if(domain_val.trim() !== "" && dimension_val.trim() !== "")
					{
						checking_duplicate();
					}
				}
			});
			
			$('#response1_val').change(function() {
		        if(this.checked) {
		        	$("#response2_val").attr("disabled", true);
		        	$("#response2_val").css('opacity', '0.4');
		        } else
			    {
		        	$("#response2_val").attr("disabled", false);
		        	$("#response2_val").css('opacity', '');
				}
		        $('#response2_val').prop('checked', false);
		    });

			$('#response2_val').change(function() {
		        if(this.checked) {
		        	$("#response1_val").attr("disabled", true);
		        	$("#response1_val").css('opacity', '0.4');
		        } else
			    {
		        	$("#response1_val").attr("disabled", false);
		        	$("#response1_val").css('opacity', '');
				}
		        $('#response1_val').prop('checked', false);
		    });
		});

		function checking_duplicate()
		{
			var domain_form = $("#questionform").serialize();
	        $.ajax({
				type:'POST',
				url: "./question_controller/retriveRecord_by_forginkey",	
				data: domain_form,
				success: function(data)
				{
					$("#questionform :input").prop("disabled", false);
					if(data)
					{
						$("#question_status").val(data);
					} else
					{
						$("#question_status").val(data);
					}
				}
			});
		}

		function dimensionChange(selected_val)
		{
			var value_txt = selected_val.value;
			var domain_val = $("#domain_val").val();

			if(domain_val.trim() !== "" && value_txt.trim() !== "")
			{
				checking_duplicate();		
			}
		}
		
		/*Add*/
		function quesitionsformsubmit()
		{
			if($("#question_id").val().trim() == "")
			{
				if($("#question_status").val() == "true")
				{
					$('#question_status').validationEngine('showPrompt', 'This Question already exits under the same dimension', 'load');
					return false;
				} else
				{
					$('#question_status').validationEngine('hide');				
				}
			}
			
			if(!$('#questionform').validationEngine('validate')) 
			{
				setTimeout(function() {
					$('#questionform').validationEngine('hide');
			      }, 5000);
				
				return false;
			}
			var check_box = 'N';
			if ($('#leader_val').is(":checked"))
			{
				check_box = 'Y';
			}
			// Response 1
			var resp1_check_box = 'N';
			if ($('#response1_val').is(":checked"))
			{
				resp1_check_box = 'Y';
			}
			// Response 2
			var resp2_check_box = 'N';
			if ($('#response2_val').is(":checked"))
			{
				resp2_check_box = 'Y';
			}
			//active
			var active_check_box = 'N';
			if ($('#active_val').is(":checked"))
			{
				active_check_box = 'Y';
			}
			var quest_form = $("#questionform").serialize()+"&check_val="+check_box+"&resp1_check_val="+resp1_check_box+"&resp2_check_val="+resp2_check_box+"&active_check_val="+active_check_box;
			$("#questionform :input").prop("disabled", true);
			$.ajax({
				type:'POST',
				url: "./question_controller/createRecord",
				data: quest_form,
				success: function(data)
				{
					$("#questionform :input").prop("disabled", false);
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
							content: 'Sorry, the Master data should be edited only during Maintenance period.',
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

		function retrivequestionfrm(id)
		{
			$.ajax({
				type:'POST',
				url: "./question_controller/retriveRecord",	
				data: {"id" : id},
				success: function(data)
				{
					$("#question_id").val(data.id);			
					$("#domain_val").val(data.domainsDO.id);
					domainChange();
					setTimeout(function() {
						$("#dimension_val").val(data.dimensionsDO.id);
				      }, 1000);
					$("#survey_ques_txt").val(data.sQuestions);
					$("#response1_txt").val(data.sResponse1);
					$("#response2_txt").val(data.sResponse2);
					if(data.cIsLeaderShip == 'Y')
					{
						$("#leader_val").prop("checked", true);				
					} else
					{
						$("#leader_val").prop("checked", false);
					}

					if(data.cIsResponse1 == 'Y')
					{
						$("#response1_val").prop("checked", true);

						$("#response2_val").attr("disabled", true);
			        	$("#response2_val").css('opacity', '0.4');		
					} else
					{
						$("#response1_val").prop("checked", false);
					}

					if(data.cIsResponse2 == 'Y')
					{
						$("#response2_val").prop("checked", true);

						$("#response1_val").attr("disabled", true);
			        	$("#response1_val").css('opacity', '0.4');				
					} else
					{
						$("#response2_val").prop("checked", false);
					}
					
					if(data.cIsActive == 'Y')
					{
						$("#active_val").prop("checked", true);				
					} else
					{
						$("#active_val").prop("checked", false);
					}					
				}
			});
		}

		function deletequestionfrm(id, key)
		{
			var messgae = "Are you sure you want to delete this record?";
			var title = "Delete Record";
			if(key !== 'delete' && key !== 'soft')
			{
				title = "Status Change";
				messgae = "Are you sure you want to change the status this record?";
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
				url: "./question_controller/deleteRecord",	
				data: {"question_id" : id, "key" : key},
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

		function domainChange()
		{
			var selected_domain = $("#domain_val").val();
			$.ajax({
				type:'POST',
				url: "./dimension_controller/fetching_all_list",	
				success: function(data)
				{
					$("#dimension_val").empty();
					var options = '<option value="">-- Select Dimension --</option>';
					$.each(data, function (index, value) 
					{
						if(selected_domain == value.domainsDO.id)
						{
							options += '<option value="'+value.id+'">'+ value.sDimensionsName+'</option>';
						}
					});
					document.getElementById('dimension_val').innerHTML = options;
				}
			});
		}

		function uploadCSVFile()
		{
			$(".uploadfile_btn").attr('disabled', 'disabled');
			var uploadpath = $('#upload_csv_file').val();
			var fileExtension = uploadpath.substring(uploadpath.lastIndexOf(".") + 1, uploadpath.length);
			if($('#upload_csv_file').val().length == 0)
			{
				$(".uploadfile_btn").attr('disabled', false);
				$('#upload_csv_file').validationEngine('showPrompt', 'Please choose *.csv file and keep it default format ', 'load');
				setTimeout(function() {
			         $("#upload_csv_file").validationEngine('hide');
			      }, 3000);
				return false;
			}
			if(fileExtension == "csv" || fileExtension == "CSV")
			{
				$("#checkloading").show();
				var xhr = new XMLHttpRequest();
				if(typeof FormData == "undefined")
				{
					var filetrans = [];
					filetrans.push("file", document.getElementById('upload_csv_file')[0].files);
					xhr.open("POST", './question_controller/uploadQuestionsCSV');
					xhr.send(filetrans);
				} else
				{
					var filetrans = new FormData();
					filetrans.append("file", document.getElementById('upload_csv_file').files[0]);
					xhr.open("POST", './question_controller/uploadQuestionsCSV');
					xhr.send(filetrans);
				}
				xhr.onloadend = function (data) {
					$("#checkloading").css("display","none");
					var sResult = xhr.responseText;
					if(sResult == "3")
					{
						$.confirm({
							title: 'Alert',
							content: 'Sorry, the Master data should be edited only during Maintenance period.',
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
						$(".uploadfile_btn").attr('disabled', false);
					} else
					{
						$("#result_label").empty();
						$("#result_label").show();
						$("#result_label").text(sResult);
						var splitresult = sResult.split(": ");
						if(splitresult[3].replace(" The File name", "").trim() != 0)
						{
							setTimeout(function() {window.open("./question_controller/faileddata");}, 700);

							$.confirm({
								title: 'Info',
								content: 'The uploaded file contains invalid entries.  Please check the downloaded file for more details.',
								type: 'red',
								typeAnimated: true,
								buttons: {
								tryAgain: {
									text: '&nbsp;&nbsp;OK&nbsp;&nbsp;',
									btnClass: 'btn-red',
									action: function()
									{}
								}}
							});
						}
						//$('#userform').validationEngine('hide');
						setTimeout(function() {
					         $("#result_label").fadeOut(10000);
					         location.reload();
					      }, 10000);
						$(".uploadfile_btn").attr('disabled', false);
					}
				};
			}
			else
			{
				$(".uploadfile_btn").attr('disabled', false);
				$('#upload_csv_file').validationEngine('showPrompt', 'Please check file format', 'load');
				return false;
			}
		}
	</script>
</html>