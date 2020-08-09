<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dao.*"%>
<%@ page import = "vo.*"%>    
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
		<meta charset="UTF-8">
		<title>Insert title here</title>
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
		System.out.println("--------languageUpdateForm.jsp--------");
		int languageId= Integer.parseInt(request.getParameter("languageId"));
		System.out.println(languageId+" <--languageId");
		
		LanguageDao languageDao = new LanguageDao();
		Language language = languageDao.selectLanguageOne(languageId);	
	%>
		<div id="aside">
			 <jsp:include page="/inc/sidemenu.jsp"></jsp:include> 
		</div>
		<div id="section">
			<h1>actor 수정 폼</h1>
			<form method="post" action="<%=request.getContextPath()%>/languageUpdateAction.jsp?languageId=<%=languageId%>">
				<div>
					language_id : 
					<input type="text" name="languageId" value="<%=language.getLanguageId()%>" readonly="readonly">
				</div>
				<div>
					name : 
					<input type="text" name="name" value="<%=language.getName()%>">
				</div>
				<button type="submit">저장</button>
			</form>
		</div>	
	</body>
</html>