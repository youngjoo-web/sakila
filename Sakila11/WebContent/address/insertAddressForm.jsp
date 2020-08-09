<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
	<head>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<!-- Popper JS -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
		<!-- icon -->
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
		<title>insertAddressForm.jsp</title>
		<meta charset="UTF-8">
		<style>
			body{
			padding: 0;
			margin: 0;
			width: 100%;
			height: 100%;
			overflow: hidden;
			background-position: 0 0;
			background-repeat: no-repeat;
			background-attachment: fixed;
			background-size: cover;
			position: relative;
			overflow-y: auto;
			}
			
			#aside {
			width: 200px;
			height: 3000px;
			position: fixed;
			background: orange;
			overflow: hidden;
			float: left;
			}
			    
			#section {
			margin-left: 300px;
			background: white;
			}
		</style>
	</head>
	<body>
	<%
		System.out.println("-------insertAddressForm.jsp-------");
		int cityId = Integer.parseInt(request.getParameter("cityId"));
		System.out.println(cityId+" <-- cityId");
		String country = request.getParameter("country");
		System.out.println(country+" <-- countryId");
		String cityName = request.getParameter("cityName");
		System.out.println(cityName+" <-- cityName");
	%>
		<div id="aside">
			 <jsp:include page="/inc/sidemenu.jsp"></jsp:include> 
		</div>
		<div id="section">
			<h1>주소 추가</h1>
			<form method="post" action="<%=request.getContextPath()%>/address/insertAddressAction.jsp">
				<input type="hidden" name="cityId" value="<%=cityId%>">
				<table>
					<tr>
						<th>country</th>
						<td>
							<input type="text" name="countryName" value="<%=country%>" readonly="readonly">
						</td>	
					<tr>
					<tr>
						<th>city</th>
						<td>
							<input type="text" name="cityName" value="<%=cityName%>" readonly="readonly">
						</td>	
					<tr>
					<tr>
						<th>district</th>
						<td>
							<input type="text" name="district">
						</td>	
					<tr>
					<tr>
						<th>address</th>
						<td>
							<input type="text" name="address">
						</td>	
					<tr>
					<tr>
						<th>address2</th>
						<td>
							<input type="text" name="address2">
						</td>	
					<tr>
					<tr>
						<th>postal code</th>
						<td>
							<input type="text" name="postalCode">
						</td>	
					<tr>
					<tr>
						<th>phone</th>
						<td>
							<input type="text" name="phone">
						</td>	
					<tr>
				</table>
				<button type="submit">저장</button>	
			</form>
		</div>
	</body>
</html>