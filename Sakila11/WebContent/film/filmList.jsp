<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.*"%>
<%@ page import="vo.*"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet"
			href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<!-- jQuery library -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<!-- Popper JS -->
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<!-- Latest compiled JavaScript -->
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
		<!-- icon -->
		<link rel="stylesheet"
			href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
			integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
			crossorigin="anonymous">
		<title>Film List</title>
		<meta charset="UTF-8">
		<style>
			body {
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
		<%
			request.setCharacterEncoding("UTF-8");
	
			// 페이징 관련 로직(currentPage, rowPerPage, beginRow)
			int currentPage = 1;
			if (request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			int rowPerPage = 10;
			int beginRow = (currentPage - 1) * rowPerPage;
	
			//리스트 출력 로직
			FilmDao filmDao = new FilmDao();
	
			// 검색 관련 로직(searchWord)검색연관 ---> 페이징 관련 로직 (lastPage, totalCount)
			String searchWord = "";
			ArrayList<FilmAndLanguage> list = new ArrayList<>();
			if (request.getParameter("searchWord") != null) {
				searchWord = request.getParameter("searchWord");
				list = filmDao.selectSearchFilmAndLanguageListAll(beginRow, rowPerPage, searchWord);
			} else {
				list = filmDao.selectFilmAndLanguageListAll(rowPerPage, beginRow);
			}
			int totalCount = 0;
			totalCount = filmDao.selectTotalCount(totalCount);
			int lastPage = totalCount / rowPerPage;
			if (totalCount % rowPerPage != 0) {
				lastPage += 1;
			}
		%>
		<div id="aside">
			<jsp:include page="/inc/sidemenu.jsp"></jsp:include>
		</div>
		<div id="section">
			<div id="main">
				<h2><strong>필름 목록</strong></h2>
				<form method="post" action="<%=request.getRequestURI()%>">
					<div>
						검색 : <input type="text" name="searchWord"><button type="submit">검색</button>
					</div>			
				</form>
				<a href = "<%=request.getContextPath()%>/film/insertFilmForm.jsp">필름추가</a>
				<table class="table table-striped" style="margin-top: 10px;">
					<thead style="background-color:#5a493f; color: white;">
						<tr>
							<th width="100">필름<br>번호</th>
							<th width="100">제목</th>
							<th width="350">줄거리</th>
							<th width="150">개봉일</th>
							<th width="70">재생<br>시간</th>
							<th width="70">등급</th>
							<th width="70">언어</th>
							<th width="70">특별편</th>
							<th width="100">대여<br>기간</th>
							<th width="100">대여료</th>
							<th width="100">연체료</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (FilmAndLanguage f : list) {
						%>
						<tr>
							<td><%=f.getFilm().getFilmId()%></td>
							<td><%=f.getFilm().getTitle()%></td>
							<td><%=f.getFilm().getDescription()%></td>
							<td><%=f.getFilm().getReleaseYear()%></td>
							<td><%=f.getFilm().getLength()%></td>
							<td><%=f.getFilm().getRating()%></td>
							<td><%=f.getLanguage().getName()%></td>
							<td><%=f.getFilm().getSpecialFeatures()%></td>
							<td><%=f.getFilm().getRentalDuration()%></td>
							<td><%=f.getFilm().getRentalRate()%>$</td>
							<td><%=f.getFilm().getReplacementCost()%>$</td>
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