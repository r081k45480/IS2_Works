<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Works evidence</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" />
</head>
<body>
<h1>${user.imePrezime}</h1>
<p><a href="<c:url value="/logout" />" >logout</a></p>

<c:if test="${empty user.manager}">
	<h3><i>-manager-</i></h3>


	<p><a href="/WorksWeb/proj/admin/init">Novi projekat</a>
	<a href="/WorksWeb/users/admin/init">Novi radnik</a></p>
	
	
	<div class="text-container">
	<h2>Radnici:</h2>
	<table>
	<c:forEach items="${radnici}" var="p">
		<tr><td>${p.imePrezime}</td></tr>
	</c:forEach>
	</table>
	</div>
	
	
</c:if>

<div class="text-container">
<h2>Projekti:</h2>
<table>
	<c:forEach items="${projs}" var="p">
		<tr><td><a href="/WorksWeb/proj/showproj?id=${p.id}">${p.naziv }</a></td></tr>
	</c:forEach>
</table>
</div>


<c:if test="${empty user.manager}">
<h3>
	<i>
		<a href="/WorksWeb/reports/SviProjekti.pdf">Izvestaj svih projekata</a>
	</i>
</h3>
</c:if>
</body>
</html>