<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	response.setStatus(404);
	response.setStatus(400);
	response.setStatus(403);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <link rel="icon" type="image/png" href="resources/img/fav_icon.png" />
  <title>404</title>
  <!--- Bootstrap CSS File-------->
  <link rel="stylesheet" href="resources/web-plugins/bootstrap/css/bootstrap.min.css">
  <!------ Fonts CSS File--------->
  <link rel="stylesheet" href="resources/web-plugins/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
  <style>
	a
	{
		color: #fcb815;
		text-decoration: none;
	}
	a:hover
	{
		color: #2577ff;
		
	}
  </style>
</head>
<body>
	<div class="container" style="padding: 60px;">
		<center><img src="resources/img/404.png" width="600"></center><br/>
		<center><h2 style="letter-spacing: 2px;">PAGE NOT FOUND</h2></center><br/>
		<center><label style="font-size: 25px; color: #c3c3c7;">The page you were looking for could not be found</label></center><br/>
		<c:choose>
			<c:when test="${sessionScope.common.sLoginType eq '0'}">
				<center><label><a href="./logout"><b>Back to Home</b></a></label></center>			
			</c:when>
			<c:otherwise>
				<center><label><a href="./userlogout"><b>Back to Home</b></a></label></center>
			</c:otherwise>
		</c:choose>
	</div>
<!--- Jquery File-------->
<script type="text/javascript" src="resources/web-plugins/jquery/jquery-3.3.1.min.js"></script>
<!---- Bootstrap Js File ------->
<script type="text/javascript" src="resources/web-plugins/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>