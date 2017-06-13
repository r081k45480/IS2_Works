<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Novi projekat</title>
</head>
<body>

	<c:set var="today" value="<%=new Date()%>" />
	<form:form action="/WorksWeb/proj/save" method="post" modelAttribute="proj">
		<table>
 		    <tr><td>Naziv</td><td><form:input type="text" name="naziv" path="naziv"/></td></tr>
  			<tr><td>Opis:</td><td><form:input type="text" name="opis" path="opis"/></td></tr>
 			<tr><td>Pocetak projekta:</td><td><input type="date" name="pocetak" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${today }"/>'/></td></tr>
  	   		<tr><td>Plan zavrsetka:</td><td><input type="date" name="planZavrsetka" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${today }"/>'/></td></tr>
  		</table>
  		<input type="submit" value="Sacuvaj"/>
  	</form:form>
  	
  	<h3><i>${poruka }</i></h3>
</body>
</html>