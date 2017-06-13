<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Unos clana</title>
</head>
  
<body>
	<c:set var="today" value="<%=new Date()%>" />
	<form:form action="/LibWeb/clanovi/saveClan" method="post" modelAttribute="clan">
		<table>
  		      	<tr><td>Kategorija:</td><td>
  		 <!--         	<form:select name="kategorija" path="kategorija">
  				<c:forEach var="k" items="${kategorije }">
  					<form:option value="${k.idkategorije}">${k.nazivkategorije}</form:option>
  				</c:forEach>
  				</form:select>
  		-->
  		<!--  		<form:select path="kategorija">
            		<form:options items="${kategorije}" itemValue="idkategorije" itemLabel="nazivkategorije"/>
        		</form:select>
        -->		
        	<form:select path="kategorija" items="${kategorije}" itemValue="idkategorije" itemLabel="nazivkategorije"/>
  			</td></tr> 
  			<tr><td>Ime:</td><td><form:input type="text" name="ime" path="ime"/></td></tr>
  			<tr><td>Prezime:</td><td><form:input type="text" name="prezime" path="prezime"/></td></tr>
  			<tr><td>Adresa:</td><td><form:input type="text" name="adresa" path="adresa"/></td></tr>
  			<tr><td>Datum rodjenja:</td><td><input type="text" name="datumRodjenja" path="datumRodjenja"/></td></tr>
  	   		<tr><td>Datum upisa:</td><td><input type="date" name="datumUpisa" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${today }"/>'/></td></tr>
  			<tr><td><input type="submit" value="Sacuvaj"/></td></tr>
  		</table>
  	</form:form>
 ${poruka } ${clan.clanskibroj} 	
  	
</body>
</html>