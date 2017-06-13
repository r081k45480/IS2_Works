<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>Nerazduzeni clanovi <br/>
	<table border="1">
		<c:forEach var="c" items="${clanovi }">
			<tr><td>${c.ime }</td><td>${c.prezime }</td><td>${c.datumRodjenja }</td><td>${c.clanskibroj }</td></tr>
		</c:forEach>
	</table>
</body>
</html>