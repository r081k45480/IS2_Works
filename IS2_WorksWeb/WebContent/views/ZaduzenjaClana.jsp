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
	<form action="/LibWeb/clanovi/zaduzenjaClana" method="get">
		Clanksi broj: <input type="text" name="clanskibroj"/> <input type="submit" value="pronadji"/>
	</form>
	<table>
		<c:forEach var="z" items="${zaduzenja}">
			<tr><td>${z.primerak.knjiga.naslov }</td>
			<td>${z.primerak.knjiga.autor }</td>
			<td>${z.datumZaduzenja}</td>
			<td>${z.datumVracanja }</td>
			<c:if test="${ empty z.datumVracanja}">
				<td><a href="/LibWeb/clanovi/razduziZaduzenje/${z.id }">Razduzi</a></td>
			</c:if>
			</tr>
		</c:forEach>
	</table>
</body>
</html>