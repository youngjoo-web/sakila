<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
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
		<meta charset="UTF-8">
		<title>Language List</title>
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
		        margin-left: px;
		        background: white;
		    }
		    #main {
				padding-top:50px;
				padding-bottom:50px;
				margin-left:300px;
				margin-right:100px;
				text-align: center;
			}
		</style>
	</head>
	<body>
		<!-- 컨트롤 로직 -->
	<%
		LanguageDao languageDao = new LanguageDao();
		int currentPage=1;
		if(request.getParameter("currentPage")!=null){
			currentPage= Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage=5;
		int totalCount=0;
		int beginRow = (currentPage-1)*rowPerPage;
		ArrayList<Language> list = languageDao.selectLanguageAll(beginRow, rowPerPage);
		totalCount = languageDao.selectTotalCount();
		int lastPage = totalCount/rowPerPage;
		if(totalCount%rowPerPage!=0){
			lastPage +=1;
		}
	%>
		<div id="aside">
			 <jsp:include page="/inc/sidemenu.jsp"></jsp:include> 
		</div>
		<div id="section">
			<div id="main">
				<h2><strong>언어 목록</strong></h2>
				<a href="<%=request.getContextPath()%>/language/languageInsertForm.jsp">추가</a>
				<table class="table table-striped" style="margin-top: 20px;">
						<thead style="background-color:#5a493f; color: white;">
						<tr>
							<th>언어 번호</th>
							<th>언어</th>
							<th>Delete</th>
							<th>Update</th>
						</tr>
					</thead>
					<tbody>
					<%
						for(Language language : list){
					%>
						<tr>
							<td><%=language.getLanguageId()%></td>
							<td><%=language.getName()%></td>
							<td><a href="<%=request.getContextPath()%>/language/languageDeleteAction.jsp?languageId=<%=language.getLanguageId()%>">삭제</a></td>
							<td><a href="<%=request.getContextPath()%>/language/languageUpdateForm.jsp?languageId=<%=language.getLanguageId()%>">수정</a></td>
						</tr>
					<%
						}
					%>
					</tbody>
				</table>
				<!-- 페이징 링크 -->
				<div style="text-align: center;">
					<a class="btn btn-sm" style="background-color: #d6d6d6; color:white;" href="<%=request.getRequestURI()%>?currentPage=1"><strong><<</strong></a>
					<%
						if(currentPage>1){
					%>
							<a class="btn btn-sm" style="background-color: #d6d6d6; color:white;" href="<%=request.getRequestURI()%>?currentPage=<%=currentPage-1%>"><strong>이전</strong></a>
					<%	
						}else{
					%>
					<a class="btn btn-sm" style="background-color: #d6d6d6; color:white;" href="<%=request.getRequestURI()%>?currentPage=1"><strong>이전</strong></a>
					<%
						}
					%>
					<span>
						-<%=currentPage%>-
					</span>
					<%
						if(currentPage<lastPage){
					%>
							<a class="btn btn-sm" style="background-color: #d6d6d6; color:white;" href="<%=request.getRequestURI()%>?currentPage=<%=currentPage+1%>"><strong>다음</strong></a>
					<%
						}else{
					%>
							<a class="btn btn-sm" style="background-color: #d6d6d6; color:white;" href="<%=request.getRequestURI()%>?currentPage=<%=lastPage%>"><strong>다음</strong></a>
					<%
						}
					%>
					<a class="btn btn-sm" style="background-color: #d6d6d6; color:white;" href="<%=request.getRequestURI()%>?currentPage=<%=lastPage%>"><strong>>></strong></a>
				</div>
			</div>	
		</div>	
	</body>
</html>