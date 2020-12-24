<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Profile Report</title>
  	<!--- Bootstrap CSS File-------->
  	<link rel="stylesheet" type="text/css" href="resources/css/pdf_css/sheets-of-paper-usletter.css">
  	<link rel="stylesheet" href="resources/web-plugins/bootstrap/css/bootstrap.min.css">
  	<!------ Fonts CSS File--------->
  	<link rel="stylesheet" href="resources/web-plugins/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
  	<style type="text/css">
  		p{
  			text-align: justify;
  		}
  		td, th {
		  border: 1px solid #dddddd;
		  padding-right: 10px;
		  padding-left: 10px;
		}
		.td_class_border {
	   		border: 1px solid #dddddd;
		  padding-right: 10px;
		  padding-left: 10px;
		}
  	</style>
</head>
<body class="document" style="background-color: #eee;pointer-events: none;">
		
		<div class="page" contenteditable="true">
			<div align="center">
				<img alt="DEI report" src="resources/img/report_logo_new1.png" width="400"> 
				<br><br>
				<p class="h5" style="text-align: center;">This ${leader_val} report has been prepared for <b style="font-weight: bolder;">${sessionScope.common.sName}</b></p>
				<hr> 
			</div>
			<br><br>
			<p class="h4" style="font-weight: bolder;text-align: left;">
				Understanding the DEImetrix&trade; Assessment Model
			</p>
			<p>
				The purpose of DEImetrix&trade; assessment is to increase behavioral self-awareness and measure commitment to Diversity, Equity and Inclusion in daily interactions, processes, and decisions.
			<p>
			<p>
				The DEImetrix&trade; Assessment Model measures your attitudes, preferences, and beliefs about Diversity, Equity, and Inclusion.  Each of these domains (Diversity, Equity, and Inclusion) is further comprised of three dimensions, which are illustrated below:
			</p>
			<br><br>
			<div class="row" class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
				<img alt="DEI" src="resources/img/DEI_static.png" style="object-fit: contain;width: 100%"> 
			</div>
			<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			<div style="bottom: 0;" align="right">1</div>
		</div>
		<div class="page" contenteditable="true">
			<p></p>
			<p class="h5" style="font-weight: bolder;">Your DEI Assessment Summary</p>
			<p style="font-size: 15px;">Your DEImetrix&trade; assessment is the average of your domain scores (Diversity, Equity, Inclusion and Overall).  The three main domains (Diversity, Equity, and Inclusion) are comprised of the averages of their dimensions.  Every domain and dimension have their own corresponding percentile.</p>
			<br><br>
			<div align="center">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<!-- total image -->
						<div class="row" align="center">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="position: relative;text-align: center;">
								<jsp:include page="total_battery.jsp"></jsp:include>
							</div>
						</div>
						<div class="row col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
							<img alt="" src="resources/img/report_imgs/arrow_img_new.png" style="object-fit: contain;width: 100%">
						</div>
						<br>
						<!-- DEI images -->
						<div class="row col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center"> 
							<!-- <diversity -->
							<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
								<jsp:include page="diversity_battery.jsp"></jsp:include>
								<p></p>
								<div>
									<p style="font-size: 13px;text-align: center;">Are you open to difference?</p>
								</div>
							</div>
							<!-- equity -->
							<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
								<jsp:include page="equity_battery.jsp"></jsp:include>
								<p></p>
								<div>
									<p style="font-size: 13px;text-align: center;">Are you fair irrespective of difference?</p>
								</div>
							</div>
							<!-- inclusion -->
							<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
								<jsp:include page="inclusion_battery.jsp"></jsp:include>
								<p></p>
								<div>
									<p style="font-size: 13px;text-align: center;">Are you engaging those that are different?</p>
								</div>
							</div>
						</div>
						<div class="row" class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
							<img alt="DEI" src="resources/img/per_img.png"  style="object-fit: contain;width: 100%">
						</div>
					</div>
				</div>
			</div>
			<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			<div style="bottom: 0;" align="right">2</div>
		</div>
		
		<div class="page" contenteditable="true">
			<div style="margin: 20px;">
				<br><br>
				<p class="h4" style="font-weight: bolder;text-align: left;">
					Domains and Dimensions Summary
				</p>
				<br><br>
				<table width="100%">
					<tr>
						<td width="60%">
							<label style="font-weight: bolder;">DIVERSITY</label>
						</td>
						<td width="40%" align="center">
							<label style="font-weight: bolder;">Diversity Domain Score</label>
						</td>
					</tr>
					<tr>
						<td width="60%">
							<label><span style="font-weight: bolder;">Comfortable with Differences</span><br>
							<span style="font-size: 11px;word-wrap: break-word;text-align: justify;">
								Embraces opportunities to interact with people who exhibit different values, behaviors, and attitudes from what one considers "normal." 
								Characteristics include an ability to look past superficial appearances, sexual orientation, education, income, age, disability, 
								and other such factors.</span></label>
						</td>
						<td width="40%" align="center">
							<p style="font-weight: bold;text-align: center;">${diver1}</p>
						</td>
					</tr>
					<tr>
						<td width="60%">
							<label><span style="font-weight: bolder;">Appreciates Diverse Thought</span><br>
							<span style="font-size: 11px;word-wrap: break-word;text-align: justify;">
							Exemplifies the ability and willingness to listen to different opinions. Characteristics include patience, listening without judgment,
							 as well as the need to know and understand the thoughts and feelings of others.</span></label>
						</td>
						<td width="40%" align="center">
							<p style="font-weight: bold;text-align: center;">${diver2}</p>
						</td>
					</tr>
					<tr>
						<td width="60%">
							<label><span style="font-weight: bolder;">Develops Cultural Intelligence</span><br>
							<span style="font-size: 11px;word-wrap: break-word;text-align: justify;">Accepts, relates to, and easily works with people who are from different countries and cultures. Characteristics include broad mindedness, curiosity, and an ability to empathize and accept people from diverse backgrounds and countries.</span></label>
						</td>
						<td width="40%" align="center">
							<p style="font-weight: bold;text-align: center;">${diver3}</p>
						</td>
					</tr>
				</table>
				<br>
				<table width="100%">
					<tr>
						<td width="60%">
							<label style="font-weight: bolder;">EQUITY</label>
						</td>
						<td width="40%" align="center">
							<label style="font-weight: bolder;">Equity Domain Score</label>
						</td>
					</tr>
					<tr>
						<td width="60%">
							<label><span style="font-weight: bolder;">Acts with Fairness</span><br>
							<span style="font-size: 11px;word-wrap: break-word;text-align: justify;">Seeks and provides equal opportunities to colleagues, subordinates, and superiors, regardless of job title or position. Characteristics include sharing ideas and opportunities throughout the organization and helping level the playing field for all employees in the organization.</span></label>
						</td>
						<td width="40%" align="center">
							<p style="font-weight: bold;text-align: center;">${equity1}</p>
						</td>
					</tr>
					<tr>
						<td width="60%">
							<label><span style="font-weight: bolder;">Engages Respectfully</span><br>
							<span style="font-size: 11px;word-wrap: break-word;text-align: justify;">Treats everyone as an equal, with kindness and respect. Characteristics include acknowledging people throughout the organization, regardless of job title, team, department, or position.</span></label>
						</td>
						<td width="40%" align="center">
							<p style="font-weight: bold;text-align: center;">${equity2}</p>
						</td>
					</tr>
					<tr>
						<td width="60%">
							<label><span style="font-weight: bolder;">Fosters Stewardship</span><br>
							<span style="font-size: 11px;word-wrap: break-word;text-align: justify;">Takes ownership of their job functions and responsibilities, including mistakes, while helping others. Characteristics include accepting responsibility for personal performance-including owning and learning from errors-while recognizing the needs of others beyond the person's assigned responsibilities.</span></label>
						</td>
						<td width="40%" align="center">
							<p style="font-weight: bold;text-align: center;">${equity3}</p>
						</td>
					</tr>
				</table>
				<br>
				<table width="100%">
					<tr>
						<td width="60%">
							<label style="font-weight: bolder;">INCLUSION</label>
						</td>
						<td width="40%" align="center">
							<label style="font-weight: bolder;">Inclusion Domain Score</label>
						</td>
					</tr>
					<tr>
						<td width="60%">
							<label><span style="font-weight: bolder;">Creates a Feeling of Belonging</span><br>
							<span style="font-size: 11px;word-wrap: break-word;text-align: justify;">Welcomes new team members and helps them acclimate. Characteristics include friendliness, humor, and sincerity.</span></label>
						</td>
						<td width="40%" align="center">
							<p style="font-weight: bold;text-align: center;">${inclu1}</p>
						</td>
					</tr>
					<tr>
						<td width="60%">
							<label><span style="font-weight: bolder;">Interacts with Humility</span><br>
							<span style="font-size: 11px;word-wrap: break-word;text-align: justify;">Recognizes accomplishments as a shared effort and offers credit to other team members. Characteristics include selfless behaviors, generosity, and an ability to recognize and appreciate others' achievements.</span></label>
						</td>
						<td width="40%" align="center">
							<p style="font-weight: bold;text-align: center;">${inclu2}</p>
						</td>
					</tr>
					<tr>
						<td width="60%">
							<label><span style="font-weight: bolder;">Promotes Participation</span><br>
							<span style="font-size: 11px;word-wrap: break-word;text-align: justify;">Welcomes and encourages others to join in and share opinions during activities, meetings, and events, including introducing new people to team members. Characteristics include friendliness, compassion, humor, and sensitivity.</span></label>
						</td>
						<td width="40%" align="center">
							<p style="font-weight: bold;text-align: center;">${inclu3}</p>
						</td>
					</tr>
				</table>
			</div>
			<br><br><br><br><br><br><br><br><br><br>
			<div style="bottom: 0;" align="right">3</div>
		</div>
		<div class="page" contenteditable="true">
			<div style="margin: 20px;">
				<br><br>
				<p class="h4" style="font-weight: bolder;text-align: left;">
					The DEImetrix&trade; Takeaway
				</p>
				<p>
					It is important to recognize that these scores only represent where you are today and can be improved.
				</p>
				<p>
					When looking at your results, please take note of domains and dimensions that are relatively lower or out of balance with your strengths.  It is not uncommon for one domain to be more developed than the others.  For example, improvements in Diversity often occur before those in Equity or Inclusion.
				</p>
				<p>
					By completing the DEImetrix&trade; assessment, you took the first step toward creating awareness. Reading your entire DEImetrix&trade; assessment report, you showed you are open minded and care. With additional understanding, education, and practice, you can improve your awareness and help make you work environment more enjoyable-even profitable-for you, your associates, and your organization.
				</p>
				<p>
					Your next step is to focus on one specific dimension from one specific DEI domain and make a conscious decision to work on improving one specific behavior.
				</p>
				<p>
					Complete the table below:
				</p>
				<table width="100%" style="border: 1px solid #dddddd;">
					<tr>
						<td width="50%" style="border: 1px solid #dddddd;">
							Highest Dimensions
						</td>
						<td width="50%" style="border: 1px solid #dddddd;">
							Lowest Dimensions
						</td>		
					</tr>
					<tr>
						<td width="50%" class="td_class_border">
							<p></p>
							1.
							<p></p>
						</td>
						<td width="50%" class="td_class_border">
							<p></p>
							1.
							<p></p>
						</td>
					</tr>
					<tr>
						<td width="50%" class="td_class_border">
							<p></p>
							2.
							<p></p>
						</td>
						<td width="50%" class="td_class_border">
							<p></p>
							2.
							<p></p>
						</td>		
					</tr>
					<tr>
						<td width="50%" class="td_class_border">
							<p></p>
							3.
							<p></p>
						</td>
						<td width="50%" class="td_class_border">
							<p></p>
							3.
							<p></p>
						</td>		
					</tr>
				</table>
				<br>
				<p>Develop a Personal Action Plan</p>
				<p>
					<textarea style="width: 100%;resize: none;min-height: 150px"></textarea>
				</p>
			</div>
			<br><br><br><br><br><br><br><br><br><br><br><br><br>
			<div style="bottom: 0;" align="right">4</div>
		</div>
	<!--- Jquery File-------->
	<script type="text/javascript" src="resources/web-plugins/jquery/jquery-3.3.1.min.js"></script>
	<!---- Bootstrap Js File ------->
	<script type="text/javascript" src="resources/web-plugins/bootstrap/js/bootstrap.min.js"></script>
</body>
<script type="text/javascript">
//window.print();
</script>
</html>