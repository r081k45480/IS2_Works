<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="/LibWeb/vezbe2/knjigePoImenu" method="get">
	<select name="ime">
		<c:forEach items="${imena }" var="i">
			<option value="${i }">${i }</option>
		</c:forEach>
	</select>
	<input type="submit" value="prikazi"/>
</form>

<c:if test="${!empty knjiga}">
	<table border="1">
		<tr>
			<td>
				Naslov:
			</td>
			<td>
				${knjiga.naslov}
			</td>
		</tr>
		<tr>
			<td>
				Autor:
			</td>
			<td>
				${knjiga.autor}
			</td>
		</tr>
		<tr>
			<td>
				Godina izadnja:
			</td>
			<td>
				${knjiga.godinaIzdanja}
			</td>
		</tr>
		<c:forEach items="${primerci}" var="pri">
		<tr>
			<td>
				${pri.invBroj }
			</td>
			<td>
				<a href="/LibWeb/vezbe2/izvestaj.pdf?invBroj=${pri.invBroj }">Prikazi</a>
			</td>
		</tr>
		</c:forEach>
	</table>
</c:if>

</body>
</html>