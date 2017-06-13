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
${poruka}
<br><br>
Naslov: ${knjiga.naslov}<br>
Autor: ${knjiga.autor}
<br><br>
<form action="/LibWeb/knjige/savePrimerci" method="post">
Broj primeraka: <input type="text" name="brojPrimeraka">
<input type="submit" value="Dodaj primerke">
</form>
<c:if test="${empty invBrojevi && snimanjePrimeraka}">
	Doslo je do greske, inventarni brojevi nisu generisani!
</c:if>
<c:if test="${!empty invBrojevi}">
	Generisani su sledeci inventarni brojevi: <br>
	<c:forEach var="invBroj" items="${invBrojevi}">
		${invBroj} <br>
	</c:forEach>
</c:if>
</body>
</html>