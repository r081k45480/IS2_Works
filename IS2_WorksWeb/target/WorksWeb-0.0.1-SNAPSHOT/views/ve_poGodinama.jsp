<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="/LibWeb/vezbe/knjigeZaGodinu" method="get">
		<select name="godina"> 
			<c:forEach items="${godine}" var="god">
				<option value="${god }"> ${god}</option>
			</c:forEach>
		</select>
		
		<input type="submit" value="Prikazi"/>
	</form>
	
	<c:if test="${!empty brojKnjiga }">
		Odabrana je ${brojKnjiga} knjiga
	</c:if>
	<br>
	<br>
	<br>
	<c:if test="${!empty knjige}">
		<table>
			<c:forEach items="${knjige }" var="knj">
				<tr>
					<td>
						${knj.naslov}
					</td>
					<td>
						${knj.autor}
					</td>
					<td>
						${knj.godinaIzdanja}
					</td>
					<td>	
						<a href="/LibWeb/vezbe/izvestaj.pdf?id=${knj.idKnjige }">
						Prikazi
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>