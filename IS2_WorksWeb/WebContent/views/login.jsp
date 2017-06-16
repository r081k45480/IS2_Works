<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="false"%>
<html>
<head>
<meta charset="UTF-8">
<title>Pocetna stranica</title>
</head>
<body>
		<c:if test="${not empty error}">
			${error}
		</c:if>

		<form name='loginForm'
		    action="<c:url value='j_spring_security_check' />" method='POST'>

		    <table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value='robii'></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
			        <td colspan='2'>
                                <input name="submit" type="submit" value="submit" />
                                </td>
			</tr>
		   </table>

			<input type="hidden"
                     name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
</body>
</html>