<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*"%>
<%@ page import = "dao.*"%>
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
		<title>Category Update Form</title>
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
				padding-right:50%;
				padding-bottom: 100px;
			}
		</style>
	</head>
	<body>
	<%
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		//System.out.println(categoryId+" <-- categoryId");
		
		CategoryDao categoryDao = new CategoryDao();
		Category category = categoryDao.selectCategoryOne(categoryId);
		
	%>
		<div id="aside">
			 <jsp:include page="/inc/sidemenu.jsp"></jsp:include> 
		</div>
		<div id="section">
			<div id="main">
				<h1>카테고리 수정</h1>
				<form method="post" action="<%=request.getContextPath()%>/category/categoryUpdateAction.jsp">
					<table class="table">
						<tr>
							<th>category_id</th>
							<td>
								<input type="text" name="categoryId" value=<%=categoryId%> readonly="readonly">
							</td>
						</tr>
						<tr>
							<th>name</th>
							<td>
								<input type="text" name="name" value=<%=category.getName()%>>
							</td>
						</tr>
					</table>
					<button type="submit">저장</button>
				</form>
			</div>	
		</div>	
	</body>
</html>