<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <meta name="description" content="" />
  <meta name="author" content="" />
  <link rel="icon" type="image/png" href="resources/img/fav_icon.png" />
  <title>User Survey</title>
  <style type="text/css">
  
  	.carousel-indicators-numbers {
	    li {
	      text-indent: 0;
	      margin: 0 2px;
	      width: 30px;
	      height: 30px;
	      border: none;
	      border-radius: 100%;
	      line-height: 30px;
	      color: #fff;
	      background-color: #999;
	      transition: all 0.25s ease;
	      &.active, &:hover {
	        margin: 0 2px;
	        width: 30px;
	        height: 30px;
	        background-color: #337ab7;        
	      }
	    }
	}
	
	input[type='radio'] {
	    -webkit-appearance:none;
	    width:20px;
	    height:20px;
	    border:1px solid darkgray;
	    border-radius:50%;
	    outline:none;
	    box-shadow:0 0 5px 0px gray inset;
	}
	input[type='radio']:hover {
	    box-shadow:0 0 5px 0px #000000 inset;
	}
	input[type='radio']:before {
	    content:'';
	    display:block;
	    width:60%;
	    height:60%;
	    margin: 20% auto;    
	    border-radius:50%;    
	}
	input[type='radio']:checked:before {
	    background: #000000;
	}
	
	.track {
	     position: relative;
	     background-color: #ddd;
	     height: 7px;
	     display: -webkit-box;
	     display: -ms-flexbox;
	     display: flex;
	     margin-bottom: 90px;
    	 margin-top: 20px;
	 }
	
	 .track .step_first {
	     -webkit-box-flex: 1;
	     -ms-flex-positive: 1;
	     flex-grow: 1;
	     width: 25%;
	     margin-top: -18px;
	     text-align: center;
	     position: relative;
	 }
	 
	 .track .step {
	     -webkit-box-flex: 1;
	     -ms-flex-positive: 1;
	     flex-grow: 1;
	     width: 25%;
	     margin-top: -18px;
	     text-align: center;
	     position: relative;
	 }
	 
	 .track .step_last {
	     -webkit-box-flex: 1;
	     -ms-flex-positive: 1;
	     flex-grow: 1;
	     width: 25%;
	     margin-top: -18px;
	     text-align: center;
	     position: relative;
	 }
	
	 .track .text {
	     display: block;
	     margin-top: 7px;
	     width: 100% !important;
	 }
	
	  .track .icon {
	     display: inline-block;
	     width: 40px;
	     height: 40px;
	     line-height: 40px;
	     position: relative;
	     border-radius: 100%;
	     background: #ddd;
	 }
	 
  </style>
</head>
<body style="overflow-x:hidden;background-color: #d59d2b;">
<jsp:include page="../common/header.jsp"></jsp:include>
<!--- Strat main content----->
	<br>
	<div class="container">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
			<img src="resources/img/logo.png" alt="DEImetrix" width="200" align="middle">
			<div align="right" style="color: background-color: black;position: absolute;top: 0px;right: 0px;">
				<ul class="navbar-nav ml-auto ml-md-0">
	                <li class="nav-item dropdown">
	                    <a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color: black;">
	                    	<i class="fas fa-user fa-fw"></i></a>
	                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
	                        <a class="dropdown-item" href="#" onclick="demo_resume_page()">${sessionScope.common.sName}</a>
	                        <c:if test="${fn:contains(sessionScope.user.cIsBannedSurvey,'Y')}">
		                        <a class="dropdown-item" href="./profile_report" target="_blank">Profile Report</a>
		                    </c:if>
		                    <a class="dropdown-item" href="./reports/ProfileReport" target="_blank">${sessionScope.common.sName} PDF Report</a>
	                        <div class="dropdown-divider"></div>
	                        <a class="dropdown-item" href="./userlogout">Logout</a>
	                    </div>
	                </li>
	            </ul>
			</div>
		</div><br>
		<div class="main-card">
			<div class="card">
				<c:if test="${fn:contains(sessionScope.user.cIsBannedSurvey,'N')}">
					<div class="card-header" style="background-color: #fff;">
						<div align="right">
							<span id="time" style="font-size: 25px;margin: 10px;font-weight: bolder;color: #d59d2b"></span>
							<span id="timer_text" style="font-size: 15px;margin: 10px;font-weight: bolder;color: #d59d2b;display: none;">The survey will be active for ${sessionScope.common.iDisplayTimer} minutes.</span>
						</div>				
					</div>
				</c:if>
				<c:if test="${fn:contains(sessionScope.user.cIsBannedSurvey,'Y')}">
					<div class="card-header" style="background-color: #fff;">
						<div class="row" align="center">
						  	<div class="alert alert-danger col-xs-12 col-sm-12 col-md-12 col-lg-12">
							   <label style="text-align: center;">
							   You have already taken this assessment. Please contact <strong>info@deimetrix.com</strong></label>
							</div>
						</div>				
					</div>
				</c:if>
				<div class="card-body loaderclass">
					<c:if test="${requestScope.info_val eq 0}">
						<div class="row" align="center">
						  	<div class="alert alert-warning col-xs-12 col-sm-12 col-md-12 col-lg-12">
							   <label style="text-align: center;">Application maintenance period starts at <strong class="h3">${requestScope.info_date}</strong class="h3"> and ends at <strong>${requestScope.info_end_date}</strong>.</label>
							</div>
						</div>						
					</c:if>
					<div class="row" style="display: none;" id="prealert_alert" align="center">
					  	<div class="alert alert-warning col-xs-12 col-sm-12 col-md-12 col-lg-12">
						   <label style="text-align: center;">The survey will close in <strong class="h3">5 minutes</strong>, please save your responses to prevent lose of data.</label>
						</div>
					</div>
					<div class="row" style="display: none;" id="closealert_alert" align="center">
					  	<div class="alert alert-warning col-xs-12 col-sm-12 col-md-12 col-lg-12">
						   <label style="text-align: center;">The survey will close in <strong class="h3">1 minute</strong>, please save your responses to prevent lose of data.</label>
						</div>
					</div>
					<div class="row" style="display: none;" id="required_alert" align="center">
					  	<div class="alert alert-danger col-xs-12 col-sm-12 col-md-12 col-lg-12">
						   <label style="text-align: center;">Please select a response before moving further.</label>
						</div>
					</div>
					<div class="row" style="display: none;" id="success_alert" align="center">
						<div class="alert alert-success col-xs-12 col-sm-12 col-md-12 col-lg-12">
							  Saved successfully.
						</div>
					</div>
					<div class="row" style="display: none;" id="failed_alert" align="center">
						<div class="alert alert-danger col-xs-12 col-sm-12 col-md-12 col-lg-12">
						  Please select a response.
						</div>
					</div>
					<div class="row" style="display: none;" id="failed_alert_server" align="center">
						<div class="alert alert-danger col-xs-12 col-sm-12 col-md-12 col-lg-12">
						  Please check your network connection and try again.
						</div>
					</div>
					<div id="carouselExampleIndicators" class="carousel slide" data-keyboard="false" data-interval="false">
					  <div class="carousel-inner" role="listbox">
					    <!-- Slide 1 -->
					    <div class="carousel-item" id="zoom_level">
				    		<img alt="intro" src="resources/img/banner.jpg" style="width: 100%;object-fit: cover;">
							<p class="h4" align="center">DEImetrix&trade; is an exploration of your beliefs, attitudes and preferences toward Diversity, Equity & Inclusion in your daily interactions in the workplace.</p>
					    </div>
					    <div class="carousel-item">
					    	<div class="container">
							  <h2 style="font-weight: bold;" align="center">Demographics</h2><br>
							  <form id="demographics_form" name="demographics_form">
							  	<input type="hidden" id="trans_user_id" name="trans_user_id" class="form-control">
							  	<input type="hidden" id="trans_survey_init_id" name="trans_survey_init_id" class="form-control">
							  	<div>	
									<label class="cus-label" for="reportval_label" id="reportval_label">Do you have direct reports?</label>
								      <label class="form-check-label" for="radio1" style="margin-left: 30px;">
								        <input type="radio" class="validate[required]" id="reportval" name="optradio" value="Y">Yes
								      </label>
								      <label class="form-check-label" for="radio2" style="margin-left: 30px;">
								        <input type="radio" class="validate[required]" id="reportval1" name="optradio" value="N">No
								      </label>
								</div>
							    <c:forEach items="${requestScope.demo_list}" var="demolist" varStatus="counter">
							    	<div class="form-group">
								      <label for="chooseans${counter.count}" id="chooseans${counter.count}">${demolist.sQuestions}</label>
								      <c:choose>
										    <c:when test="${empty demolist.sPrefredOptionsAns}">
										       <input type="text" src="${demolist.sQuestions}|${demolist.id}|${counter.count}" autocomplete="off" id="chooseans${counter.count}" name="chooseans${counter.count}" class="form-control validate[required] answer_val special_char">
										    </c:when>
										    <c:otherwise>
									    		<select class="form-control validate[required] answer_val" src="${demolist.sQuestions}|${demolist.id}|${counter.count}" id="chooseans${counter.count}" name="chooseans${counter.count}" onchange="other_selection(this);">
										      		<option value=""></option>
											        <c:forTokens items="${demolist.sPrefredOptionsAns}" delims="," var="mutltians">  
												      	<option value="${mutltians}|${counter.count}">${mutltians}</option>
													</c:forTokens>	
													<c:if test="${demolist.sQuestions ne 'Job level' && demolist.sQuestions ne 'Tenure'}">
														<option value="Others|${counter.count}">Other</option>												      	
											      	</c:if>
										      	</select>
												  <input type="text" src="${demolist.sQuestions}|${demolist.id}|${counter.count}" autocomplete="off" id="other_field${counter.count}" name="other_field${counter.count}" placeholder="Other" class="form-control validate[required] other_answer_val" style="display: none;margin-top: 5px;">
										    </c:otherwise>
										</c:choose>
								    </div>
						         </c:forEach>
						         <br>
							  </form>
							</div>						    
					    </div>
					    <div class="carousel-item">
					    	<div class="container">
					    		<p class="h2" align="center" style="font-weight: bolder;">Assessment Instructions</p><br>
					    		<p class="h5">DEImetrix&trade; explores your beliefs, attitude and preferences toward Diversity, Equity & Inclusion. For the most valid and valuable results, it is important that you respond honestly, not politically correct or socially aware.</p><br>
					    		<p class="h5">The survey consists of ${question_count} statements, each with two (2) paired answers. Read each statement carefully, then choose an answer based on your initial reaction. There is no right or wrong answer.</p><br>
					    		<p class="h5" style="font-weight: bold;">Each pair of answers cannot total more than three (3) points. The score assigned to one answer affects the score of the companion answer.  For example, answering Almost Always (3 points) to one answer automatically assigns Almost Never (0 points) to its companion.</p>
					    	</div>
					    </div>
			    		<jsp:include page="question_sublist.jsp"></jsp:include>
			        	<div class="carousel-item" id="remove_item">
					    	<div class="" align="center">
					    		<svg class="checkmark" viewBox="0 0 52 52">
								  <circle class="checkmark__circle" cx="26" cy="26" r="25" fill="none"/>
								  <path class="checkmark__check" fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8"/>
								</svg><br>
					    		<p align="center" class="h1" style="font-weight: bold;font-size: 55px;">Thank you!</p><br>
					    		<p align="center" class="h4" style="font-weight: bold;">You're done.</p><br>
					    		<p align="center" class="h5">Please click the Generate Report button to access your customized DEImetrix&trade; assessment.</p><br>
					    		<a class="btn btn-lg" href="./profile_report" target="_blank" type="button" style="font-weight: bolder !important;background-color: #000000 !important;color: #d59d2b !important;">
									<i class="fa fa-flag" aria-hidden="true"></i>&nbsp;&nbsp;Generate Report
								</a>
					    	</div>
					    </div>
					  </div>
					</div>
					<c:if test="${fn:contains(sessionScope.user.cIsBannedSurvey,'N')}">
						<div id="intro_ass__btn" align="center">
							<a href="#carouselExampleIndicators" class="btn btn-lg next_btn_val" role="button" aria-pressed="true" data-slide="next" style="margin-top: 10px;background-color: #000000;color: #d59d2b;font-weight: bold;">Take the DEImetrix Assessment</a>
						</div>
						<div id="intro_contine_btn" align="center">
							<!-- <a href="#carouselExampleIndicators" class="btn btn-lg next_btn_val" role="button" aria-pressed="true" data-slide="next" style="margin-top: 10px;background-color: #000000;color: #d59d2b;font-weight: bold;">Continue&nbsp;&nbsp;<i class="fa fa-forward" aria-hidden="true"></i></a> -->
							<button type="button" class="btn btn-lg" style="margin-top: 10px;background-color: #000000;color: #d59d2b;font-weight: bold;" onclick="demographics_details()">
						    	Continue&nbsp;&nbsp;<i class="fa fa-forward" aria-hidden="true"></i>
						    </button>
						</div>
						<div id="intro_start_btn" align="center">
							<a href="#carouselExampleIndicators" class="btn btn-lg next_btn_val" role="button" aria-pressed="true" data-slide="next" style="margin-top: 10px;background-color: #000000;color: #d59d2b;font-weight: bold;">Start</a>
						</div>
						<!-- <div id="question_button" style="margin-top: 30px;" align="center">
							<a href="#carouselExampleIndicators" class="btn btn-secondary btn-lg previous_btn_val" role="button" aria-pressed="true" data-slide="prev" style="float: left;width: 120px"><i class="fa fa-backward" aria-hidden="true"></i>&nbsp;&nbsp;Back</a>
							<a href="#carouselExampleIndicators" class="btn btn-lg next_btn_val" role="button" aria-pressed="true" data-slide="next" style="float: right;background-color: #000000;color: #d59d2b;font-weight: bold;">Continue&nbsp;&nbsp;<i class="fa fa-forward" aria-hidden="true"></i></a>
							<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
								<div class="progress">
								  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 75%;background-color: #d59d2b;"></div>
								</div>
							</div>
						</div> -->
						<div id="question_button_txt" align="center">
							<label style="font-weight: bolder;" id="slidetext"></label>
						</div>
						<input type="hidden" id="radio_button_select_val">
					</c:if>
			  	</div>
			</div>
		</div><br/>
	</div>
	<footer class="footer mt-auto py-1">
	  <div class="container">
	  	<div class="row col-xs-12 col-sm-12 col-md-12 col-lg-12">
	  		<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" align="left">
				<span class="text-muted" style="float: left;">Powered By: <a href="https://www.integrosoftwares.com/" target="_blank">Integro Softwares LLC.</a></span>
			</div>
			<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" align="right">
				<span class="text-muted" style="float: right;">&copy; 2020 <a href="https://ingenium.global/" target="_blank">Ingenium Global.</a> ALL RIGHTS RESERVED.</span>
	  		</div>	  	
	  	</div>
	  </div>
	</footer>
	
	<script type="text/javascript">

		$( document ).ready(function() {

			//zoom(1);
			
			$("#carouselExampleIndicators").off('keydown.bs.carousel');

			<c:choose>
				<c:when test="${fn:contains(sessionScope.user.cIsBannedSurvey,'N')}">
					if("${requestScope.survey_init.iDraftPageNo}" !== null && "${requestScope.survey_init.iDraftPageNo}" !== "")
					{
						$('.carousel').each(function(){
						    $(this).find('.carousel-item').eq("${requestScope.survey_init.iDraftPageNo}").addClass('active');
						  });				
					} else
					{
						$('.carousel').each(function(){
						    $(this).find('.carousel-item').eq(0).addClass('active');
						  });
					}
				</c:when>
				<c:otherwise>
					$('.carousel').each(function(){
					    $(this).find('.carousel-item').eq(0).addClass('active');
					  });
				</c:otherwise>
			</c:choose>

			var total_val = $('.carousel-item').length;
			var currentIndex_val = $('div.active').index() + 1;
			
			  if(currentIndex_val == 0 || currentIndex_val == 1)
			  {
				  $("#timer_text").show();
				  $("#time").hide();
				  $("#intro_ass__btn").show();
				  $("#intro_contine_btn").css("display", "none");
				  $("#intro_start_btn").css("display", "none");

				  $("#question_button_txt").css("display", "none");
			  } else if(currentIndex_val == 2)
			  {
				  $("#timer_text").show();
				  $("#time").hide();
				  $("#intro_ass__btn").css("display", "none");
				  $("#intro_contine_btn").show();
				  $("#intro_start_btn").css("display", "none");
				  
				  $("#question_button_txt").css("display", "none");
			  } else if(currentIndex_val == 3)
			  {
				  $("#timer_text").show();
				  $("#time").hide();
				  $("#intro_ass__btn").css("display", "none");
				  $("#intro_contine_btn").css("display", "none");
				  $("#intro_start_btn").show();
				  
				  $("#question_button_txt").css("display", "none");
			  } else
			  {
				  $("#timer_text").hide();
				  $("#time").show();
				  $("#intro_ass__btn").css("display", "none");
				  $("#intro_contine_btn").css("display", "none");
				  $("#intro_start_btn").css("display", "none");
				  $("#question_button_txt").show();
				  var cal_val_txt = ((currentIndex_val - 3) / (total_val-4)) * 100;
				  $('.progress-bar').css('width', cal_val_txt+'%').attr('aria-valuenow', cal_val_txt);
				  $('.progress-bar').attr('aria-valuemax', (total_val - 4));
				  
				  var minutes_val = "${sessionScope.common.iDisplayTimer}";
		    		var fiveMinutes = 60 * minutes_val,
	        		display = document.querySelector('#time');
			    	startTimer(fiveMinutes, display);
			  }

			  var text = (currentIndex_val - 3) + ' / ' + (total_val - 4);
			  $('#slidetext').html(text);

			var specl_last_val = '';
			$(".special_char").on('change keyup paste mouseup', function() {
			    if ($(this).val() != specl_last_val) 
				{
			    	specl_last_val = $(this).val();
			        var ques = $(this).attr("id");

			        var value_val = specl_last_val.replace(/["`]/g, '');
					$('#'+ques).val(value_val);
				}
			});

			var other_last_val = '';
			$(".other_answer_val").on('change keyup paste mouseup', function() {
			    if ($(this).val() != other_last_val) 
				{
			    	other_last_val = $(this).val();
			        var ques = $(this).attr("id");

			        var value_val = other_last_val.replace(/["`]/g, '');
					$('#'+ques).val(value_val);
				}
			});

			setTimeout(function() {

				/**************************** Append Demographic Questions start *******************************/
				
				$(".answer_val").each(function() {

			        var ques = $(this).attr("src").split(/\|/);
			        var demo_master_id = ques[1];
			        var demo_counter_id = ques[2];
			        <c:forEach items="${requestScope.trans_uesr_demo_obj}" var="trans_demolist" varStatus="counter_val">
	        			if("${trans_demolist.iDemomasterId}" == demo_master_id)
				        {
				        	if("${counter_val.count}" == 1)
					        {
				        		$("input[name=optradio][value='${trans_demolist.cIsLeaderShip}']").prop('checked', true);
						    }
		        			$('#chooseans'+demo_counter_id+' option[value="${trans_demolist.sAnswer}|'+demo_counter_id+'"]').prop('selected', true);
				        	if("${trans_demolist.sAnswer}" == 'Others')
						    {
				        		$("#other_field"+demo_counter_id).show();
					        	$("#other_field"+demo_counter_id).val("${trans_demolist.sOtherSpecify}");
							}
					        $("input[name=optradio]").attr('disabled',true);
				        }
			        </c:forEach>
			    });

				/**************************** Append Demographic Questions end *******************************/
				
				/**************************** Append Survey Questions start *******************************/
				<c:forEach items="${requestScope.trans_survey_ques_list}" var="trans_survey_list" varStatus="counter_val">

					$(".${trans_survey_list.sClassName}").each(function() {
	
				        var ques = $(this).attr("src").split(/\|/);
				        var radio_name = $(this).attr("name");
				        var answer = $("input[name='"+radio_name+"']:checked").val();
						
				        var ques_master_id = ques[6];

				        if("${trans_survey_list.iQuestionMasterId}" == ques_master_id)
				        {
					        var split_radio_name = radio_name.split("_");
					        if(split_radio_name[1] == 1)
						    {
				        		$("input[name="+radio_name+"][value='${trans_survey_list.sAsnwerMark1}']").prop('checked', true);		
				        		$("#radio_button_select_val").val(1);				    
							} else
							{
								$("input[name="+radio_name+"][value='${trans_survey_list.sAsnwerMark2}']").prop('checked', true);
								$("#radio_button_select_val").val(1);
							}
				        }
					});

		        </c:forEach>

		        /**************************** Append Survey Questions End *******************************/
				
		      }, 2000);

			var timerId = setInterval(function() {
				$.ajax({
					type:'POST',
					url: "./serverconfig/fetchingServerConfig",
					success: function(data)
					{
						var currentDate = new Date();
					    var startDate = data.dMaintainStartDate;
					    var endDate =  data.dMaintainEndDate;

					    if (currentDate > startDate && currentDate < endDate)
						{
				            clearInterval(timerId);
							
					    	$.confirm({
								title: 'Info',
								content: 'Sorry, the application is down for maintenance. Please try again later.',
								type: 'red',
								typeAnimated: true,
								buttons: 
								{
									tryAgain: 
									{
										text: '&nbsp;&nbsp;OK&nbsp;&nbsp;',
										btnClass: 'btn-red',
										action: function()
										{
											window.location.href = "./userlogout";
										}
									}
								}
							});
					    }
					}
				});
			}, 20000);
			
		});

		/* slider starts */
		
		$('.carousel').carousel({ 
			interval: false,
		});

		var total = $('.carousel-item').length;
		var currentIndex = $('div.active').index() + 1;
		
		// This triggers after each slide change
		$('.carousel').on('slid.bs.carousel', function () {
		  currentIndex = $('div.active').index() + 1;

		  if(currentIndex == 0 || currentIndex == 1)
		  {
			  $("#timer_text").show();
			  $("#time").hide();
			  $("#intro_ass__btn").show();
			  $("#intro_contine_btn").css("display", "none");
			  $("#intro_start_btn").css("display", "none");

			  $("#question_button_txt").css("display", "none");
		  } else if(currentIndex == 2)
		  {
			  $("#timer_text").show();
			  $("#time").hide();
			  $("#intro_ass__btn").css("display", "none");
			  $("#intro_contine_btn").show();
			  $("#intro_start_btn").css("display", "none");

			  $("#question_button_txt").css("display", "none");
		  } else if(currentIndex == 3)
		  {
			  $("#timer_text").show();
			  $("#time").hide();
			  $("#intro_ass__btn").css("display", "none");
			  $("#intro_contine_btn").css("display", "none");
			  $("#intro_start_btn").show();

			  $("#question_button_txt").css("display", "none");
		  } else
		  {
			  $("#timer_text").hide();
			  $("#time").show();
			  $("#intro_ass__btn").css("display", "none");
			  $("#intro_contine_btn").css("display", "none");
			  $("#intro_start_btn").css("display", "none");
			  $("#question_button_txt").show();
			  var cal_val = ((currentIndex - 3) / (total-4)) * 100;
			  $('.progress-bar').css('width', cal_val+'%').attr('aria-valuenow', cal_val);
		  }

		  if(currentIndex == 4)
		  {
			  var minutes_val = "${sessionScope.common.iDisplayTimer}";
    			var fiveMinutes = 60 * minutes_val,
       	 		display = document.querySelector('#time');
       	 		var span_val = $("#time").text().trim();
       	 		if(span_val == '' || span_val == null)
           	 	{
			    	startTimer(fiveMinutes, display);      	 	
               	}
		  }
		  
		// Now display this wherever you want
		  var display_current = currentIndex - 3;
		  var display_total = total-4;
		  var text = display_current + ' / ' + display_total;
		  $('#slidetext').html(text);
		  if(currentIndex > total)
		  {
			  $("#question_button_txt").css("display", "none");
			  $(".next_btn_val").hide();
		  } else
		  {
			  $(".next_btn_val").show();
		  }
		});

		/* slider end */
		
		function other_selection(option_val)
		{
			var selected_val = option_val.value;
			var split_val = selected_val.split(/\|/);
		    if(split_val[0] == 'Others')
			{
		    	$("#other_field"+split_val[1]).val("");
				$("#other_field"+split_val[1]).show();
			} else
			{
				$("#other_field"+split_val[1]).val("");
				$("#other_field"+split_val[1]).hide();
			}				
		}
		
		function demographics_details()
		{
			if(!$('#demographics_form').validationEngine('validate')) 
			{
				return false;
			}

			$('.loaderclass').preloader({
			  	 // loading text
			  	  text: '', 
			  	  // from 0 to 100 
			  	  percent: '', 
			  	  // duration in ms
			  	  duration: '', 
			  	  // z-index property
			  	  zIndex: '100', 
			  	  // sets relative position to preloader's parent
			  	  setRelative: false 
	  		});
			
			var jsonObj = [];
		    $(".answer_val").each(function() {

		        var ques = $(this).attr("src").split(/\|/);
		        var answer = $(this).val().split(/\|/);

		        item = {}
		        item ["question"] = ques[0];
		        item ["answer"] = answer[0];
		        item ["demo_master_id"] = ques[1];

		        if(answer[0] == 'Others')
			    {
		        	item ["other_specify"] = $("#other_field"+answer[1]).val();
				} else
				{
					item ["other_specify"] = '';
				}
				
		        jsonObj.push(item);
		    });

		    var total_page = $('.carousel-item').length - 1;
			var currentIndex_page = $('div.active').index() + 1;
		    var radioValue = $("input[name='optradio']:checked").val();
		    $.ajax({
				type:'POST',
				url: "./trans_controller/createDemographRecord",	
				data: {"report_val" : radioValue, "dynamic_ans" : JSON.stringify(jsonObj), "total_page" : total_page, "currentIndex_page" : currentIndex_page},
				success: function(data)
				{
					if(data == 1)
					{
						//$("#success_alert").show();
						setTimeout(function() {
							//$("#success_alert").css("display","none");
							location.reload();
					      }, 100);
						var currentIndex_val = $('div.active').index() + 1;
						var before_remove = currentIndex_val-1;
						$('.carousel').each(function(){
							$(this).find('.carousel-item').eq(before_remove).removeClass('active');
						    $(this).find('.carousel-item').eq(1).addClass('active');
					  	})	
					} else if(data == 2)
					{
						$("#failed_alert").show();
						setTimeout(function() {
							$("#failed_alert").css("display","none");
					      }, 5000);
					} else if(data == 3)
					{
						$.confirm({
							title: 'Info',
							content: 'Sorry, the application is down for maintenance. Please try again later.',
							type: 'red',
							typeAnimated: true,
							buttons: {
							tryAgain: {
								text: '&nbsp;&nbsp;OK&nbsp;&nbsp;',
								btnClass: 'btn-red',
								action: function()
								{}
							},
							close: function () { } }
						});
					}
					setTimeout(function() {
						$('.loaderclass').preloader('remove');
				      }, 100);
				}
			});
		}

		function tablevalues_submit(class_name, domain_name, dimension_name, counter_val)
		{
			/* if(!$('#survey_form'+counter_val).validationEngine('validate')) 
			{
				return false;
			} */
			$('.loaderclass').preloader({
			  	 // loading text
			  	  text: '', 
			  	  // from 0 to 100 
			  	  percent: '', 
			  	  // duration in ms
			  	  duration: '', 
			  	  // z-index property
			  	  zIndex: '100', 
			  	  // sets relative position to preloader's parent
			  	  setRelative: false 
	  		});
			var jsonObj = [];
			var jsonObj_dupl = [];
			var json_val = [];
			$("."+class_name).each(function() {

		        var ques = $(this).attr("src");
		        var radio_name = $(this).attr("name");
		        var answer = $("input[name='"+radio_name+"']:checked").val();

		        if(answer != 0)
			    {
		        	$("#radio_button_select_val").val(1);
				}
		        
		        if (typeof answer === 'undefined')
			    {
				    
				} else
				{
					$("#required_alert").hide();
			        if(jsonObj_dupl.indexOf(ques)=="-1")
				    {
			        	item = {};
				        item ["survey_question"] = ques;
				        item ["selected_section"] = answer;
					    if(answer == 3)
					    {
				        	item ["selected_name"] = "Almost Always";
					    } else if(answer == 2)
					    {
				        	item ["selected_name"] = "Often";
					    } else if(answer == 1)
					    {
				        	item ["selected_name"] = "Rarely";
					    } else if(answer == 0)
					    {
				        	item ["selected_name"] = "Almost Never";
					    }
				        item ["selected_domain_name"] = domain_name;
				        item ["selected_dimension_name"] = dimension_name;
				        item ["selected_class_name"] = class_name;
				        
				        jsonObj_dupl.push(ques);
				        jsonObj.push(item);
					}
				}
		    });

			if($("#radio_button_select_val").val() == 0)
			{
				$("#failed_alert").show();
				setTimeout(function() {
					$("#failed_alert").css("display","none");
			      }, 5000);
			      return false;
			}
			
			var total_page = $('.carousel-item').length - 1;
			var currentIndex_page = $('div.active').index() + 1;

			var currentIndex_page_dis = $('div.active').index() -1;
			var total_page_dis = $('.carousel-item').length - 4;
			
		     $.ajax({
				type:'POST',
				url: "./trans_controller/createUserSurveyRecord",	
				data: {"servey_prim_id" : "${sessionScope.common.iSurveyInitPrimId}", "survey_ans" : JSON.stringify(jsonObj), "total_page" : total_page, "currentIndex_page" : currentIndex_page},
				success: function(data)
				{
					if(data == 1)
					{
						//$("#success_alert").show();
						setTimeout(function() {
							//$("#success_alert").css("display","none");
							$("#radio_button_select_val").val(0);
							var before_remove = currentIndex_page-1;
							$('.carousel').each(function(){
								$(this).find('.carousel-item').eq(before_remove).removeClass('active');
							    $(this).find('.carousel-item').eq(currentIndex_page).addClass('active');
							  });
							  var cal_val = ((currentIndex_page_dis) / (total_page_dis)) * 100;
							  $('.progress-bar').css('width', cal_val+'%').attr('aria-valuenow', cal_val);
							  var text = (currentIndex_page_dis) + ' / ' + (total_page_dis);
							  $('#slidetext').html(text);

							  $("#timer_text").hide();
							  $("#time").show();
							  $("#intro_ass__btn").css("display", "none");
							  $("#intro_contine_btn").css("display", "none");
							  $("#intro_start_btn").css("display", "none");
							  $("#question_button_txt").show();
							  
							  var total_1 = $('.carousel-item').length;
							  var total_2 = $('.carousel-item').length - 1;
							  var currentIndex_1 = $('div.active').index() + 1;

							  if(currentIndex_1 == total_1)
							  {
							  	  $("#question_button_txt").css("display", "none");
								  clearInterval(myVar);
							  }
					      }, 100);
					} else if(data == 2)
					{
						$("#failed_alert").show();
						setTimeout(function() {
							$("#failed_alert").css("display","none");
					      }, 5000);
					} else if(data == 3)
					{
						$.confirm({
							title: 'Info',
							content: 'Sorry, the application is down for maintenance. Please try again later.',
							type: 'red',
							typeAnimated: true,
							buttons: {
							tryAgain: {
								text: '&nbsp;&nbsp;OK&nbsp;&nbsp;',
								btnClass: 'btn-red',
								action: function()
								{}
							},
							close: function () { } }
						});
					}
					setTimeout(function() {
						$('.loaderclass').preloader('remove');
				      }, 100);
				}
			});
		}

		function other_choosen()
		{
			var other_choose = document.getElementById("chooseans1").value;
			if(other_choose == 'Others')
			{
				$("#other_field"+counter+"").show();
			} else
			{
				$("#other_field"+counter+"").hide();
			}
		}

		function radio_button_func(count_id, checked_val)
		{
			/* almostalways2 often2 rarely2 almostnever2 */
			if(checked_val == 3)
			{
				$("#almostnever2"+count_id).prop("checked", true);
			} else if(checked_val == 2)
			{
		    	$("#rarely2"+count_id).prop("checked", true);
			} else if(checked_val == 1)
			{
		    	$("#often2"+count_id).prop("checked", true);
			} else if(checked_val == 0)
			{
		    	$("#almostalways2"+count_id).prop("checked", true);
			}
			$("#radio_button_select_val").val(1);
		}

		function radio_button_func2(count_id, checked_val)
		{
			if(checked_val == 3)
			{
				$("#almostnever1"+count_id).prop("checked", true);
			} else if(checked_val == 2)
			{
		    	$("#rarely1"+count_id).prop("checked", true);
			} else if(checked_val == 1)
			{
		    	$("#often1"+count_id).prop("checked", true);
			} else if(checked_val == 0)
			{
		    	$("#almostalways1"+count_id).prop("checked", true);
			}
			$("#radio_button_select_val").val(1);
		}

		// Timer
		var myVar;
		function startTimer(duration, display) 
		{
		    var timer = duration, minutes, seconds;
		    myVar = setInterval(function () 
			{
		        minutes = parseInt(timer / 60, 10);
		        seconds = parseInt(timer % 60, 10);
		
		        minutes = minutes < 10 ? "0" + minutes : minutes;
		        seconds = seconds < 10 ? "0" + seconds : seconds;

				var timer_val = minutes + ":" + seconds;
				if(timer_val == "05:00")
				{
					$("#prealert_alert").show();
				}
				if(timer_val == "01:00")
				{
					$("#prealert_alert").hide();
					$("#closealert_alert").show();
				}
				if(timer_val == "00:00")
				{
					window.location.href = "./userlogout";
				}
		        display.textContent = minutes + ":" + seconds;
		
		        if (--timer < 0) {
		            timer = duration;
		        }
		    }, 1000);
		}

		function demo_resume_page()
		{
			$("input[name=optradio]").attr('disabled',true);
			
			$("#intro_ass__btn").css("display", "none");
		  	$("#intro_contine_btn").show();
		  	$("#intro_start_btn").css("display", "none");
			$("#question_button_txt").css("display", "none");
			var total_val = $('.carousel-item').length;
			var currentIndex_val = $('div.active').index() + 1;
			var before_remove = currentIndex_val-1;

			if(total_val == currentIndex_val)
			{
				$("#remove_item").removeClass('active');
				$('.carousel').each(function(){
				    $(this).find('.carousel-item').eq(1).addClass('active');
			  	});
			} else
			{
				$('.carousel').each(function(){
					$(this).find('.carousel-item').eq(before_remove).removeClass('active');
				    $(this).find('.carousel-item').eq(1).addClass('active');
			  	});
			}
		}

		function back_page(counter)
		{
			var currentIndex_page = $('div.active').index() - 1;
			var backIndex_page = $('div.active').index();
			$('.carousel').each(function(){
				$(this).find('.carousel-item').eq((backIndex_page)).removeClass('active');
			    $(this).find('.carousel-item').eq(currentIndex_page).addClass('active');
		  	});

			var currentIndex_page_dis = $('div.active').index() - 2;
			var total_page_dis = $('.carousel-item').length - 4;
			
			var cal_val = ((currentIndex_page_dis) / (total_page_dis)) * 100;
		  	$('.progress-bar').css('width', cal_val+'%').attr('aria-valuenow', cal_val);
		  	var text = (currentIndex_page_dis) + ' / ' + (total_page_dis);
		  	$('#slidetext').html(text);	

		  	var currentIndex = $('div.active').index() + 1;
		  	if(currentIndex == 3)
		  	{
			  	$("#timer_text").show();
			  	$("#time").hide();
			  	$("#intro_ass__btn").css("display", "none");
			  	$("#intro_contine_btn").css("display", "none");
			  	$("#intro_start_btn").show();
			  	$("#question_button_txt").css("display", "none");
		  	}		  
		}


		function zoom(num) {

			var minZoom = 1;
			  var maxZoom = 5;
			
		    var zoomVal = parseInt($("#zoom_level").css('zoom'), 10);
		    zoomVal = zoomVal + num;
		    if (zoomVal < minZoom || zoomVal > maxZoom) {
		    	return;
		    }
		    $("#zoom_level").css('zoom', zoomVal);
		  }

	</script>
	
</body>
</html>