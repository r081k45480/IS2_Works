<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Works evidence</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" />
</head>
<body>

<h2><a href="/WorksWeb/users/initIndex" >M A I N</a></h2>

<h1>${proj.naziv}</h1>
<h3>
	<c:if test="${!empty manager && empty user.manager}">
		<i>
			Upozorenje! Ovo je projekat drugog managera: ${manager.imePrezime } (<b>${manager.username}</b>)
		</i>
	</c:if>
</h3>
<div class="text-container">
<h3>${proj.opis }</h3>
<h5> Pocetak projekta: <fmt:formatDate pattern="yyyy-MM-dd" value="${proj.pocetak }"/></h5>
<h5> Planiran zavrsetak: <fmt:formatDate pattern="yyyy-MM-dd" value="${proj.planZavrsetka }"/></h5>

<c:if test="${!empty proj.zavrseno }">
	<h5> Zavrsen: <fmt:formatDate pattern="yyyy-MM-dd" value="${proj.zavrseno }"/></h5>
</c:if>
	</div>
<c:if test="${empty user.manager}">
<div class="text-container">
<h3> Dodaj radnika na projkat</h3>
	<form action="/WorksWeb/proj/admin/dodajradnika" method="post">
		<input type="hidden" name="p"  value="${proj.id }"/>
		<select name="r">
			<c:forEach items="${ostaliradnici}" var="r">
				<option value="${r.username }">${r.imePrezime }</option>
			</c:forEach>
		</select>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="submit" value="Dodaj na projekat"/>
	</form>
	<hr>
	<form action="/WorksWeb/proj/admin/zavrsiproj" method="post">
		<input type="hidden" name="p"  value="${proj.id }"/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="submit" value="Zavrsi projekat"/>
	</form>
	</div>
	<div class="text-container">
	<h2>Radnici projekta:</h2>
	</div>
	<c:forEach items="${radnici}" var="r">
		<div class="text-container">
		
			<h3><b>${r.imePrezime}</b></h3>
				<hr>
				<table>
					<tr><th> Taskovi </th><th>Utroseno sati</th></tr>
					
					<c:forEach items="${map[r.username]}" var="t">
						<tr>
							<td> ${t.opis}</td>
							<td> ${t.utroseno}</td>
						</tr>
					</c:forEach>
					
					<tr>
						<form action="/WorksWeb/proj/admin/addtask" method="post">
							<td>
								<input type="text" name="opis" value="novi task..."/>
								<input type="hidden" name="p" value="${proj.id }"/>
								<input type="hidden" name="u" value="${r.username }"/>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							</td>
							<td><input type="submit" value="Dodaj"/></td>
						</form>
					</tr>
				</table>
			
		</div>
	</c:forEach>
	<br/>
	
	
	
</c:if>

<c:if test="${!empty user.manager}">

<div class="text-container">
	<table>
		<tr><th> Task</th><th>Utroseno sati</th></tr>
		<c:forEach var="t" items="${mojitaskovi}">
			<tr>
				<td> ${t.opis }</td>
				<td>
					<form action="/WorksWeb/proj/utrosio" method="post">
						Utrosio: <input type="text" name="utrosio" value="${t.utroseno}"/>
								<input type="hidden" name="tid" value="${t.id }"/>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<input type="submit" value="+"/>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>
</c:if>

<c:if test="${empty user.manager}">
<h3>
	<i>
		<a href="/WorksWeb/reports/Projekat.pdf?pid=${proj.id }">Izvestaj projekta</a>
	</i>
</h3>
</c:if>
</body>
</html>