<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Novi radnik</title>
</head>
<body>
<h2><a href="WorksWeb/users/initIndex" >M A I N</a></h2>
	<form:form action="/WorksWeb/users/save" method="post" modelAttribute="user">
		<table>
 		    <tr><td>Uloga:</td><td>
 		    <form:select path="uloga" items="${uloge}"/>
  			</td></tr> 
  			<tr><td>Ime i prezime:</td><td><form:input type="text" name="imePrezime" path="imePrezime"/></td></tr>
  			<tr><td>Username:</td><td><form:input type="text" name="username" path="username"/></td></tr>
  			<tr><td><input type="submit" value="Sacuvaj"/></td></tr>
  		</table>
  	</form:form>
  	
  	<h3><i>${poruka }</i></h3>
</body>
</html>