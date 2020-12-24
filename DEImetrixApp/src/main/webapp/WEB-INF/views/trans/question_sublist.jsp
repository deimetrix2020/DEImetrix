<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<body>
		<c:forEach items="${requestScope.survry_questions_list}" var="ques_list" varStatus="counter">
   			<c:if test="${fn:contains(ques_list.cIsActive,'Y')}">
    			<c:choose>
	            	<c:when test="${fn:contains(requestScope.survey_init.cIsLeaderShip,'Y')}">
		            	<div class="carousel-item">
					    	<div class="container">
					    		<form id="survey_form${counter.count}" name="survey_form${counter.count}">
				            		<p class="h3" align="center" style="word-break: break-all;width: 100%;font-weight: bold;">${ques_list.sQuestions}</p><br>
				            		<c:set var="rand"><%= java.lang.Math.round(java.lang.Math.random() * 2) %></c:set>
					    			<c:choose>
				            			<c:when test="${rand mod 2 == 0}">
				            				<!-- response 1 -->
		            						<div align="center">
												<div class="row"><div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<p class="h4" align="center">${ques_list.sResponse1}</p>
													<br>
													<div class="track">
														<div style="margin-top: -15px;" align= "left">
															<span class="step_first"> 
																<input
																	class="icon table_value${counter.count}"
																	type="radio" 
																	src="${ques_list.sQuestions}|res1:${ques_list.sResponse1}|${ques_list.cIsResponse1}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	id="almostalways1${counter.count}"
																	name="surveyanswer_1_${counter.count}" value="3" 
																	onclick="radio_button_func('${counter.count}', '3')"> 
															</span> <span class="text" style="width: 120% !important;">Almost Always <br> (3)</span> 
														</div>
														<div style="margin-top: -15px;width: 40%">
															<span class="step"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res1:${ques_list.sResponse1}|${ques_list.cIsResponse1}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="often1${counter.count}" name="surveyanswer_1_${counter.count}"
																	value="2" onclick="radio_button_func('${counter.count}', '2')"> 
															</span> <span class="text">Often <br> (2)</span> 
														</div>
														<div style="margin-top: -15px;width: 25%">
															<span class="step"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res1:${ques_list.sResponse1}|${ques_list.cIsResponse1}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="rarely1${counter.count}" name="surveyanswer_1_${counter.count}"
																	value="1" onclick="radio_button_func('${counter.count}', '1')"> 
															</span> <span class="text">Rarely <br> (1)</span> 
														</div>
														<div style="margin-top: -15px;width: 25%" align= "right">
															<span class="step_last"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res1:${ques_list.sResponse1}|${ques_list.cIsResponse1}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="almostnever1${counter.count}"
																	name="surveyanswer_1_${counter.count}" value="0" 
																	onclick="radio_button_func('${counter.count}', '0')"> 
															</span> <span class="text">Almost Never <br> (0)</span> 
														</div>
													</div></div>
												</div>	
											</div>
											
											<hr style="height:1px;">
											<!-- response 2 -->
											
											<div align="center">
												<div class="row"><div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<p class="h4" align="center">${ques_list.sResponse2}</p>
													<br>
													<div class="track">
														<div style="margin-top: -15px;" align= "left">
															<span class="step_first"> 
																<input
																	class="icon table_value${counter.count}"
																	type="radio" 
																	src="${ques_list.sQuestions}|res2:${ques_list.sResponse2}|${ques_list.cIsResponse2}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	id="almostalways2${counter.count}"
																	name="surveyanswer_2_${counter.count}" value="3" 
																	onclick="radio_button_func2('${counter.count}', '3')"> 
															</span> <span class="text" style="width: 120% !important;">Almost Always <br> (3)</span> 
														</div>
														<div style="margin-top: -15px;width: 40%">
															<span class="step"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res2:${ques_list.sResponse2}|${ques_list.cIsResponse2}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="often2${counter.count}" name="surveyanswer_2_${counter.count}"
																	value="2" onclick="radio_button_func2('${counter.count}', '2')"> 
															</span> <span class="text">Often <br> (2)</span> 
														</div>
														<div style="margin-top: -15px;width: 25%">
															<span class="step"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res2:${ques_list.sResponse2}|${ques_list.cIsResponse2}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="rarely2${counter.count}" name="surveyanswer_2_${counter.count}"
																	value="1" onclick="radio_button_func2('${counter.count}', '1')"> 
															</span> <span class="text">Rarely <br> (1)</span> 
														</div>
														<div style="margin-top: -15px;width: 25%" align= "right">
															<span class="step_last"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res2:${ques_list.sResponse2}|${ques_list.cIsResponse2}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="almostnever2${counter.count}"
																	name="surveyanswer_2_${counter.count}" value="0" 
																	onclick="radio_button_func2('${counter.count}', '0')"> 
															</span> <span class="text">Almost Never <br> (0)</span> 
														</div>
													</div></div>
												</div>	
											</div>
				            			</c:when>
				            			<c:otherwise>
				            				<!-- response 2 -->
										  	
											<div align="center">
												<div class="row"><div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<p class="h4" align="center">${ques_list.sResponse2}</p>
													<br>
													<div class="track">
														<div style="margin-top: -15px;" align= "left">
															<span class="step_first"> 
																<input
																	class="icon table_value${counter.count}"
																	type="radio" 
																	src="${ques_list.sQuestions}|res2:${ques_list.sResponse2}|${ques_list.cIsResponse2}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	id="almostalways2${counter.count}"
																	name="surveyanswer_2_${counter.count}" value="3" 
																	onclick="radio_button_func2('${counter.count}', '3')"> 
															</span> <span class="text" style="width: 120% !important;">Almost Always <br> (3)</span> 
														</div>
														<div style="margin-top: -15px;width: 40%">
															<span class="step"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res2:${ques_list.sResponse2}|${ques_list.cIsResponse2}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="often2${counter.count}" name="surveyanswer_2_${counter.count}"
																	value="2" onclick="radio_button_func2('${counter.count}', '2')"> 
															</span> <span class="text">Often <br> (2)</span> 
														</div>
														<div style="margin-top: -15px;width: 25%">
															<span class="step"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res2:${ques_list.sResponse2}|${ques_list.cIsResponse2}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="rarely2${counter.count}" name="surveyanswer_2_${counter.count}"
																	value="1" onclick="radio_button_func2('${counter.count}', '1')"> 
															</span> <span class="text">Rarely <br> (1)</span> 
														</div>
														<div style="margin-top: -15px;width: 25%" align= "right">
															<span class="step_last"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res2:${ques_list.sResponse2}|${ques_list.cIsResponse2}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="almostnever2${counter.count}"
																	name="surveyanswer_2_${counter.count}" value="0" 
																	onclick="radio_button_func2('${counter.count}', '0')"> 
															</span> <span class="text">Almost Never <br> (0)</span> 
														</div>
													</div></div>
												</div>			
											</div>
										  	
										  	<br>
											<hr style="height:1px;">
											<!-- response 1 -->
											<div align="center">
												<div class="row"><div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<p class="h4" align="center">${ques_list.sResponse1}</p>
													<br>
													<div class="track">
														<div style="margin-top: -15px;" align= "left">
															<span class="step_first"> 
																<input
																	class="icon table_value${counter.count}"
																	type="radio" 
																	src="${ques_list.sQuestions}|res1:${ques_list.sResponse1}|${ques_list.cIsResponse1}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	id="almostalways1${counter.count}"
																	name="surveyanswer_1_${counter.count}" value="3" 
																	onclick="radio_button_func('${counter.count}', '3')"> 
															</span> <span class="text" style="width: 120% !important;">Almost Always <br> (3)</span> 
														</div>
														<div style="margin-top: -15px;width: 40%">
															<span class="step"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res1:${ques_list.sResponse1}|${ques_list.cIsResponse1}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="often1${counter.count}" name="surveyanswer_1_${counter.count}"
																	value="2" onclick="radio_button_func('${counter.count}', '2')"> 
															</span> <span class="text">Often <br> (2)</span> 
														</div>
														<div style="margin-top: -15px;width: 25%">
															<span class="step"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res1:${ques_list.sResponse1}|${ques_list.cIsResponse1}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="rarely1${counter.count}" name="surveyanswer_1_${counter.count}"
																	value="1" onclick="radio_button_func('${counter.count}', '1')"> 
															</span> <span class="text">Rarely <br> (1)</span> 
														</div>
														<div style="margin-top: -15px;width: 25%" align= "right">
															<span class="step_last"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res1:${ques_list.sResponse1}|${ques_list.cIsResponse1}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="almostnever1${counter.count}"
																	name="surveyanswer_1_${counter.count}" value="0" 
																	onclick="radio_button_func('${counter.count}', '0')"> 
															</span> <span class="text">Almost Never <br> (0)</span> 
														</div>
													</div></div>
												</div>	
											</div>
											<br>
				            			</c:otherwise>
				            		</c:choose>
				            		<br><br>
				            		<table width="100%">
				            			<tr>
				            				<td width="10%">
				            					<button type="button" class="btn btn-secondary btn-md" style="float: left;width: 90px" onclick="back_page('${counter.count}')">
													<i class="fa fa-backward" aria-hidden="true"></i>&nbsp;&nbsp;Back
												</button>
				            				</td>
				            				<td width="80%" align="center">
				            					<div class="progress">
												  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100" style="background-color: #d59d2b;"></div>
												</div>
				            				</td>
				            				<td width="10%">
				            					<button type="button" class="btn btn-md" style="background-color: #000000;color: #d59d2b;font-weight: bold;width: 120px;margin-left: 5px;"
													onclick="tablevalues_submit('table_value${counter.count}', '${ques_list.domainsDO.sDomainName}', '${ques_list.dimensionsDO.sDimensionsName}', ${counter.count})">
														Continue&nbsp;&nbsp;<i class="fa fa-forward" aria-hidden="true"></i>
												</button>
												<button type="button" class="btn btn-md" style="background-color: #000000;color: #d59d2b;font-weight: bold;display: none;width: 90px"
													onclick="tablevalues_submit('table_value${counter.count}', '${ques_list.domainsDO.sDomainName}', '${ques_list.dimensionsDO.sDimensionsName}', ${counter.count})">
														Submit&nbsp;&nbsp;<i class="fa fa-forward" aria-hidden="true"></i>
												</button>
				            				</td>
				            			</tr>
				            		</table>
					    		</form>
					    	</div>
						  </div>
	            	</c:when>
	            	<c:otherwise>
	            		<c:if test="${fn:contains(ques_list.cIsLeaderShip,'N')}">
	            			<div class="carousel-item">
					    	<div class="container">
					    		<form id="survey_form${counter.count}" name="survey_form${counter.count}">
				            		<p class="h3" align="center" style="word-break: break-all;width: 100%;font-weight: bold;">${ques_list.sQuestions}</p><br>
				            		<c:set var="rand"><%= java.lang.Math.round(java.lang.Math.random() * 2) %></c:set>
					    			<c:choose>
				            			<c:when test="${rand mod 2 == 0}">
				            				<!-- response 1 -->
		            						<div align="center">
												<div class="row"><div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<p class="h4" align="center">${ques_list.sResponse1}</p>
													<br>
													<div class="track">
														<div style="margin-top: -15px;" align= "left">
															<span class="step_first"> 
																<input
																	class="icon table_value${counter.count}"
																	type="radio" 
																	src="${ques_list.sQuestions}|res1:${ques_list.sResponse1}|${ques_list.cIsResponse1}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	id="almostalways1${counter.count}"
																	name="surveyanswer_1_${counter.count}" value="3" 
																	onclick="radio_button_func('${counter.count}', '3')"> 
															</span> <span class="text" style="width: 120% !important;">Almost Always <br> (3)</span> 
														</div>
														<div style="margin-top: -15px;width: 40%">
															<span class="step"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res1:${ques_list.sResponse1}|${ques_list.cIsResponse1}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="often1${counter.count}" name="surveyanswer_1_${counter.count}"
																	value="2" onclick="radio_button_func('${counter.count}', '2')"> 
															</span> <span class="text">Often <br> (2)</span> 
														</div>
														<div style="margin-top: -15px;width: 25%">
															<span class="step"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res1:${ques_list.sResponse1}|${ques_list.cIsResponse1}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="rarely1${counter.count}" name="surveyanswer_1_${counter.count}"
																	value="1" onclick="radio_button_func('${counter.count}', '1')"> 
															</span> <span class="text">Rarely <br> (1)</span> 
														</div>
														<div style="margin-top: -15px;width: 25%" align= "right">
															<span class="step_last"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res1:${ques_list.sResponse1}|${ques_list.cIsResponse1}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="almostnever1${counter.count}"
																	name="surveyanswer_1_${counter.count}" value="0" 
																	onclick="radio_button_func('${counter.count}', '0')"> 
															</span> <span class="text">Almost Never <br> (0)</span> 
														</div>
													</div></div>
												</div>	
											</div>
											<hr style="height:1px;">
											<!-- response 2 -->
											<div align="center">
												<div class="row"><div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<p class="h4" align="center">${ques_list.sResponse2}</p>
													<br>
													<div class="track">
														<div style="margin-top: -15px;" align= "left">
															<span class="step_first"> 
																<input
																	class="icon table_value${counter.count}"
																	type="radio" 
																	src="${ques_list.sQuestions}|res2:${ques_list.sResponse2}|${ques_list.cIsResponse2}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	id="almostalways2${counter.count}"
																	name="surveyanswer_2_${counter.count}" value="3" 
																	onclick="radio_button_func2('${counter.count}', '3')"> 
															</span> <span class="text" style="width: 120% !important;">Almost Always <br> (3)</span> 
														</div>
														<div style="margin-top: -15px;width: 40%">
															<span class="step"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res2:${ques_list.sResponse2}|${ques_list.cIsResponse2}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="often2${counter.count}" name="surveyanswer_2_${counter.count}"
																	value="2" onclick="radio_button_func2('${counter.count}', '2')"> 
															</span> <span class="text">Often <br> (2)</span> 
														</div>
														<div style="margin-top: -15px;width: 25%">
															<span class="step"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res2:${ques_list.sResponse2}|${ques_list.cIsResponse2}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="rarely2${counter.count}" name="surveyanswer_2_${counter.count}"
																	value="1" onclick="radio_button_func2('${counter.count}', '1')"> 
															</span> <span class="text">Rarely <br> (1)</span> 
														</div>
														<div style="margin-top: -15px;width: 25%" align= "right">
															<span class="step_last"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res2:${ques_list.sResponse2}|${ques_list.cIsResponse2}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="almostnever2${counter.count}"
																	name="surveyanswer_2_${counter.count}" value="0" 
																	onclick="radio_button_func2('${counter.count}', '0')"> 
															</span> <span class="text">Almost Never <br> (0)</span> 
														</div>
													</div></div>
												</div>		
											</div>
											
				            			</c:when>
				            			<c:otherwise>
				            				<!-- response 2 -->
											<div align="center">
												<div class="row"><div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<p class="h4" align="center">${ques_list.sResponse2}</p>
													<br>
													<div class="track">
														<div style="margin-top: -15px;" align= "left">
															<span class="step_first"> 
																<input
																	class="icon table_value${counter.count}"
																	type="radio" 
																	src="${ques_list.sQuestions}|res2:${ques_list.sResponse2}|${ques_list.cIsResponse2}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	id="almostalways2${counter.count}"
																	name="surveyanswer_2_${counter.count}" value="3" 
																	onclick="radio_button_func2('${counter.count}', '3')"> 
															</span> <span class="text" style="width: 120% !important;">Almost Always <br> (3)</span> 
														</div>
														<div style="margin-top: -15px;width: 40%">
															<span class="step"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res2:${ques_list.sResponse2}|${ques_list.cIsResponse2}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="often2${counter.count}" name="surveyanswer_2_${counter.count}"
																	value="2" onclick="radio_button_func2('${counter.count}', '2')"> 
															</span> <span class="text">Often <br> (2)</span> 
														</div>
														<div style="margin-top: -15px;width: 35%">
															<span class="step"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res2:${ques_list.sResponse2}|${ques_list.cIsResponse2}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="rarely2${counter.count}" name="surveyanswer_2_${counter.count}"
																	value="1" onclick="radio_button_func2('${counter.count}', '1')"> 
															</span> <span class="text">Rarely <br> (1)</span> 
														</div>
														<div style="margin-top: -15px;width: 25%" align= "right">
															<span class="step_last"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res2:${ques_list.sResponse2}|${ques_list.cIsResponse2}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="almostnever2${counter.count}"
																	name="surveyanswer_2_${counter.count}" value="0" 
																	onclick="radio_button_func2('${counter.count}', '0')"> 
															</span> <span class="text">Almost Never <br> (0)</span> 
														</div>
													</div></div>
												</div>	
											</div>
											
											<hr style="height:1px;">
											<!-- response 1 -->
											<div align="center">
												<div class="row"><div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<p class="h4" align="center">${ques_list.sResponse1}</p>
													<br>
													<div class="track">
														<div style="margin-top: -15px;" align= "left">
															<span class="step_first"> 
																<input
																	class="icon table_value${counter.count}"
																	type="radio" 
																	src="${ques_list.sQuestions}|res1:${ques_list.sResponse1}|${ques_list.cIsResponse1}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	id="almostalways1${counter.count}"
																	name="surveyanswer_1_${counter.count}" value="3" 
																	onclick="radio_button_func('${counter.count}', '3')"> 
															</span> <span class="text" style="width: 120% !important;">Almost Always <br> (3)</span> 
														</div>
														<div style="margin-top: -15px;width: 40%">
															<span class="step"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res1:${ques_list.sResponse1}|${ques_list.cIsResponse1}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="often1${counter.count}" name="surveyanswer_1_${counter.count}"
																	value="2" onclick="radio_button_func('${counter.count}', '2')"> 
															</span> <span class="text">Often <br> (2)</span> 
														</div>
														<div style="margin-top: -15px;width: 25%">
															<span class="step"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res1:${ques_list.sResponse1}|${ques_list.cIsResponse1}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="rarely1${counter.count}" name="surveyanswer_1_${counter.count}"
																	value="1" onclick="radio_button_func('${counter.count}', '1')"> 
															</span> <span class="text">Rarely <br> (1)</span> 
														</div>
														<div style="margin-top: -15px;width: 25%" align= "right">
															<span class="step_last"> 
																<input type="radio"
																	src="${ques_list.sQuestions}|res1:${ques_list.sResponse1}|${ques_list.cIsResponse1}|${ques_list.cIsLeaderShip}|${ques_list.domainsDO.id}|${ques_list.dimensionsDO.id}|${ques_list.id}"
																	class="icon table_value${counter.count}"
																	id="almostnever1${counter.count}"
																	name="surveyanswer_1_${counter.count}" value="0" 
																	onclick="radio_button_func('${counter.count}', '0')"> 
															</span> <span class="text">Almost Never <br> (0)</span> 
														</div>
													</div></div>
												</div>	
											</div>
				            			</c:otherwise>
				            		</c:choose>
				            		<br><br>
				            		<table width="100%">
				            			<tr>
				            				<td width="10%">
				            					<button type="button" class="btn btn-secondary btn-md" style="float: left;width: 90px" onclick="back_page('${counter.count}')">
													<i class="fa fa-backward" aria-hidden="true"></i>&nbsp;&nbsp;Back
												</button>
				            				</td>
				            				<td width="80%" align="center">
				            					<div class="progress">
												  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100" style="background-color: #d59d2b;"></div>
												</div>
				            				</td>
				            				<td width="10%">
				            					<button type="button" class="btn btn-md" style="background-color: #000000;color: #d59d2b;font-weight: bold;width: 120px;margin-left: 5px;"
													onclick="tablevalues_submit('table_value${counter.count}', '${ques_list.domainsDO.sDomainName}', '${ques_list.dimensionsDO.sDimensionsName}', ${counter.count})">
														Continue&nbsp;&nbsp;<i class="fa fa-forward" aria-hidden="true"></i>
												</button>
												<button type="button" class="btn btn-md" style="background-color: #000000;color: #d59d2b;font-weight: bold;display: none;width: 90px"
													onclick="tablevalues_submit('table_value${counter.count}', '${ques_list.domainsDO.sDomainName}', '${ques_list.dimensionsDO.sDimensionsName}', ${counter.count})">
														Submit&nbsp;&nbsp;<i class="fa fa-forward" aria-hidden="true"></i>
												</button>
				            				</td>
				            			</tr>
				            		</table>
					    		</form>
					    	</div>
						  </div>
	            		</c:if>
	            	</c:otherwise>
            	</c:choose>
            </c:if>
	    </c:forEach>
	</body>
</html>