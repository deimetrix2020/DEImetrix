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
  	<link rel="stylesheet" href="resources/web-plugins/bootstrap/css/bootstrap.css">
  	<!------ Fonts CSS File--------->
  	<link rel="stylesheet" href="resources/web-plugins/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
  	<style type="text/css">
  		p{
  			text-align: justify;
  		}
  	</style>
</head>
<body class="document" style="background-color: #eee;pointer-events: none;">
		<div class="page" contenteditable="true">
			<div align="center">
				<img alt="DEI report" src="resources/img/report_imgs/report_logo_new1.png" width="400">
				<p></p>
				<p class="h5" style="text-align: center;">This ${leader_val} report has been prepared for <b style="font-weight: bolder;">${sessionScope.common.sName}</b></p>
				<hr> 
			</div>
			<p class="h5" style="font-weight: bolder;">Assessment Summary</p>
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
							<img alt="" src="resources/img/report_imgs/arrow_img_new.png" style="object-fit: contain;width: 100%;">
						</div>
						<!-- DEI images -->
						<div class="row" style="width: 100%" align="center"> 
							<!-- <diversity -->
							<div style="width: 33.33%">
								<jsp:include page="diversity_battery.jsp"></jsp:include>
								<div>
									<label style="font-size: 13px;text-align: center;">Are you open to difference?</label>
								</div>
							</div>
							<!-- equity -->
							<div style="width: 33.33%">
								<jsp:include page="equity_battery.jsp"></jsp:include>
								<div>
									<label style="font-size: 13px;text-align: center;">Are you fair irrespective of difference?</label>
								</div>
							</div>
							<!-- inclusion -->
							<div style="width: 33.33%">
								<jsp:include page="inclusion_battery.jsp"></jsp:include>
								<div>
									<label style="font-size: 13px;text-align: center;">Are you engaging those that are different?</label>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
							<img alt="DEI" src="resources/img/report_imgs/per_img.png"  style="object-fit: contain;width: 100%">
						</div>
					</div>
				</div>
			</div>
			<div style="font-family: Corbel !important;font-size: 14px !important;">
				<p></p>
				<div style="font-weight: bolder;">Highest Domain: ${max_domain}</div>
				<div>
					Highest Dimension: ${high_first_dimen}
				</div>
				<div>
					Second Highest Dimension: ${high_sec_dimen}
				</div>
				<div>
					Third Highest Dimension: ${high_three_dimen}
				</div>
				<div style="height: 10px;"></div>
				<div style="font-weight: bolder;">
					Lowest Domain: ${min_domain}			
				</div>
				<div>
					Lowest Dimension: ${low_first_dimen}	
				</div>
				<div>
					Second Lowest Dimension: ${low_sec_dimen}			
				</div>
				<div>
					Third Lowest Dimension: ${low_three_dimen}			
				</div>
			</div>
			<div style="bottom: 0;" align="right">1</div>
		</div>
		<div class="page" contenteditable="true">
			<br>
			<p class="h4" style="font-weight: bolder;text-align: left;">
				Understanding the DEImetrix&trade; Assessment Model
			</p>
			<br>
			<p>
				The purpose of DEImetrix&trade; assessment is to increase behavioral self-awareness and measure commitment to Diversity, Equity and Inclusion in daily interactions, processes, and decisions.
			<p>
			<p>
				The DEImetrix&trade; Assessment Model measures your attitudes, preferences, and beliefs about Diversity, Equity, and Inclusion. Each of these domains (Diversity, Equity, and Inclusion) is further comprised of three dimensions, which are illustrated below:
			<p>
			<br>
			<div class="row" class="col-xs-12 col-sm-12 col-md-12 col-lg-12" align="center">
				<img alt="DEI" src="resources/img/report_imgs/DEI_static.png" style="object-fit: contain;width: 100%"> 
			</div>
			<br><br>
			<p class="h4" style="font-weight: bolder;text-align: left;">
				DEImetrix&trade; Domains and Dimensions
			</p>
			<p>
				Your DEImetrix&trade; assessment is the average of your domain scores (Diversity, Equity, And Inclusion). The three domains (Diversity, Equity, and Inclusion) are comprised of the averages of their respective dimensions. Every domain and dimension have their own corresponding index
			</p>
			<br>
			<div style="bottom: 0;" align="right">2</div>
		</div>
		<div class="page" contenteditable="true">
			<div>
				<br>
				<p>
					Diversity's dimensions are:
				</p>
				<ul style="list-style-type:circle;">
				    <li style="text-align: justify;"><em>Comfortable with Differences: </em>Embraces opportunities to interact with people who have different values, behaviors, and attitudes. Characteristics include an ability to look past superficial appearances, sexual orientation, education, income, age, and (dis)ability.</li>
				    <br>
				    <li style="text-align: justify;"><em>Appreciates Diverse Thought: </em>Exemplifies the ability and willingness to listen to different opinions. Characteristics include patience, listening without judgment, as well as the need to know and understand the thoughts and feelings of others.</li>
				    <br>
				    <li style="text-align: justify;"><em>Develops Cultural Intelligence: </em> Accepts, relates to, and easily works with people who are from different countries and cultures. Characteristics include broad mindedness, curiosity, and an ability to empathize and accept people from diverse backgrounds.</li>
				  </ul>
				<p>
					Equity's dimensions are:
				</p>
				<ul style="list-style-type:circle;">
				    <li style="text-align: justify;"><em>Acts with Fairness: </em>Seeks and provides equal opportunities to colleagues, subordinates, and superiors, regardless of job title or position. Characteristics include being impartial and providing additional opportunities to those who need them.</li>
				    <br>
				    <li style="text-align: justify;"><em>Engages Respectfully: </em>Shows regard for others by displaying kindness and respect. Characteristics include thoughtfully acknowledging people and being considerate regardless of job title, team, department, or position.</li>
				    <br>
				    <li style="text-align: justify;"><em>Fosters Stewardship: </em>Understands how their role contributes to the big picture. Characteristics include accepting responsibility for personal performance while recognizing the needs of others beyond the person's assigned responsibilities.</li>
				  </ul>
			  	<p>
					Inclusion's dimensions are:
				</p>
				<ul style="list-style-type:circle;">
				    <li style="text-align: justify;"><em>Creates a Feeling of Belonging: </em>Welcomes and accepts new members to the team and helps them bring their full selves to work. Characteristics include friendliness, humor, sincerity, warmth and making people feel valued.</li>
				    <br>
				    <li style="text-align: justify;"><em>Interacts with Humility: </em>Accepts constructive feedback and is willing to acknowledge mistakes. Characteristics include selfless behaviors, generosity, and to learn and grow through the input of others.</li>
				    <br>
				    <li style="text-align: justify;"><em>Promotes Participation: </em> Contributes to the work of others and draws perspectives from outside the inner circle. Expresses point of view constructively. Characteristics include listening, asking questions, and sensitivity.</li>
				  </ul>
			</div>
			<br><br>
			<div style="bottom: 0;" align="right">3</div>
		</div>
		<div class="page" contenteditable="true">
			<div>
				<br>
				<br>
				<p class="h4" style="font-weight: bolder;text-align: left;">
					The DEImetrix&trade; Takeaway
				</p>
				<p>
					It is important to recognize that these scores only represent where you are today and can be improved. When looking at your results, please note the domains and dimensions that are relatively lower or out of balance with your strengths. It is not uncommon for one domain to be more developed than the others.
				</p>
				<p>
					By completing the DEImetrix&trade; assessment, you took the first step toward creating awareness. With additional understanding, education, and practice, you can improve your awareness and help make you work environment more enjoyable - even profitable - for you, your team, and your organization. Your next step is to focus on one specific dimension from one DEI domain and make the conscious decision to work on improving one specific behavior.
				</p>
				<br>
				<p class="h4" style="font-weight: bolder;text-align: left;">
					Your DEI Action Plan
				</p>
				<br> 
				<p>
					1. What new insight(s) did you gain about yourself by completing this assessment?
					<br><br><br><br><br>
				</p>
				<p>
					2. Name one DEI related behavioral change that you will commit to.
					<br><br><br><br><br>
				</p>
				<p>
					3. How will you do that? By when?
					<br><br><br><br><br>
				</p>
				<p>
					4. What needs to happen in the next 2 weeks?
					<br><br><br><br>
				</p>
			</div>
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