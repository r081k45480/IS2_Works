<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/LibWeb/knjige/prikaziKnjige" method="get">
		<input type="text" name="autor"/>
		<input type="submit" value="Prikazi"/>
	</form>
<c:if test="${empty knjige && imaUpita}">
	Ne postoje knjige u bazi za datog autora.
</c:if>
<c:if test="${!empty knjige}">
	<table border="1">
	<tr><th>Naslov</th><th>Autor</th><th>Izdavac</th><th>Godina izdanja</th><th>Slika</th></tr>
	<c:forEach var="k" items="${knjige}">
		<tr>
			<td>${k.naslov }</td>
			<td>${k.autor }</td>
			<td>${k.izdavac }</td>
			<td>${k.godinaIzdanja }</td>
			<td rowspan="4"><img height="100" width="100" src="get-image/${k.idKnjige}"/></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>