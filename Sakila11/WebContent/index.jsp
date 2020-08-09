<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<title>Sakila index</title>
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
			background: #5a493f;
			overflow: hidden;
			float: left;
			}
			    
			#section {
			margin-left: 300px;
			background: white;
			}
			#main {
				padding-top:40px;
				padding-right:100px;
				padding-bottom: 100px;
			}
			td{
				padding : 10px 0px;
			}
</style>
	</head>
	<body>
		<div id="aside">
			 <jsp:include page="/inc/sidemenu.jsp"></jsp:include> 
		</div>
		<div id="section">
			<div id="main">
				<h2>MYSQL 오픈 데이터베이스  Sakila를 기반으로 한 홈페이지 구축</h2>
				<hr>
				<table>	
					<tr>
						<td><img src="<%=request.getContextPath()%>/imgs/Z.jpg"></td>
						<td><img src="<%=request.getContextPath()%>/imgs/M.jpg"></td>
						<td><img src="<%=request.getContextPath()%>/imgs/Y.jpg"></td>
						<td><img src="<%=request.getContextPath()%>/imgs/I.jpg"></td>
						<td><img src="<%=request.getContextPath()%>/imgs/B.jpg"></td>
						<td><img src="<%=request.getContextPath()%>/imgs/H.jpg"></td>
					</tr>
					<tr style="text-align: center;">
						<td>백영주</td>
						<td>엄미선</td>
						<td>이윤선</td>
						<td>서일태</td>
						<td>이병관</td>
						<td>황혜인</td>
					</tr>
				</table>
				<hr>
				<table style="text-align: center">
					<tr>
						<th height="50" style="font-size: 25px" colspan="3">개발 환경</th>
					</tr>
					<tr>
						<th>PROGRAM LANGUAGE</th>
						<td>JAVA</td>
						<td>1.80_241</td>
					</tr>
					<tr>
						<th>WEB LANGUAGE</th>
						<td>HTML</td>
						<td>5</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>CSS(BOOTSTRAP)</td>
						<td>W3CSCHOOL(B4)</td>
					</tr>
					<tr>
						<th>DEVELOPMENT TOOL</th>
						<td>ECLIPSE</td>
						<td>4.14.0</td>
					</tr>
					<tr>
						<th>RDBMS</th>
						<td>MARIA DB</td>
						<td>10.4</td>
					</tr>
					<tr>
						<th>WAS</th>
						<td>APACHE-TOMCAT</td>
						<td>9.0.30</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>