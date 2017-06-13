<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<form action="/LibWeb/clanovi/prikaziClana" method="get">
		Clanski broj: <input type="text" name="broj"/>
		<input type="submit" value="Izvrsi"/>
	</form>
	Podaci clana: <br/><br/>
	Clanski broj: ${clan.clanskibroj }<br/>
	Ime: ${clan.ime } <br/>
	Prezime: ${clan.prezime }<br/>
	Adresa: ${clan.adresa } <br/>
	Datum rodjenja: ${clan.datumRodjenja} <br/>
	Datum upisa: ${clan.datumUpisa }
</body>
</html>