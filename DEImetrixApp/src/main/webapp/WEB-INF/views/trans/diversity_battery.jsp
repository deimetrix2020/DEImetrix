<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<c:choose>
		<c:when test="${requestScope.diversity_img eq '0'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_00.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '5'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_5.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '10'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_10.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '15'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_15.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '20'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_20.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '25'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_25.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '30'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_30.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '35'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_35.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '40'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_40.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '45'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_45.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '50'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_50.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '55'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_55.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '60'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_60.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '65'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_67.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '70'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_70.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '75'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_75.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '80'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_80.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '85'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_85.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '90'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_90.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:when test="${requestScope.diversity_img eq '95'}">
			<img alt="" src="resources/img/report_imgs/diversity/d_95.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:when>
		<c:otherwise>
			<img alt="" src="resources/img/report_imgs/diversity/d_100.png"
				style="object-fit: contain; height: 270px; width: 200px;">
		</c:otherwise>
	</c:choose>
</body>
</html>