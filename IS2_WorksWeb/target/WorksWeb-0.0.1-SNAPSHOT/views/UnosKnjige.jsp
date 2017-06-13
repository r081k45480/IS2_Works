<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="knjigaI" action="/LibWeb/knjige/saveKnjiga"
		method="post"  enctype="multipart/form-data">
		<table>
			<tr>
				<td><s:message code="author"/></td>
				<td><form:input type="text" name="autor" path="autor" /></td>
			</tr>
			<tr>
				<td><s:message code="title"/></td>
				<td><form:input type="text" name="naslov" path="naslov" /></td>
			</tr>
			<tr>
				<td><s:message code="publisher"/></td>
				<td><form:input type="text" name="izdavac" path="izdavac" /></td>
			</tr>
			<tr>
				<td><s:message code="publishingyear"/></td>
				<td><form:input type="text" name="godinaIzdanja"
						path="godinaIzdanja" /></td>
			</tr>
			<tr>
				<td><s:message code="image"/></td>
				<td><form:input type="file" path="slika" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="<s:message code="saveBtn"/>" />
			</tr>
		</table>
	</form:form>
</body>
</html>