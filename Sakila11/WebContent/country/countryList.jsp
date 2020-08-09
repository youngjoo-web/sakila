<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*"%>
<%@ page import = "dao.*"%>
<%@ page import = "java.util.*"%>
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
		<title>Country List</title>
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
		ArrayList<Country> list = new ArrayList<>();
		String searchWord="";
        if(request.getParameter("searchWord")!=null){
           searchWord=request.getParameter("searchWord");
        }
        
    	 // 페이징 관련 로직(currentPage, rowPerPage, beginRow)
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 10;
		int beginRow = (currentPage-1)*rowPerPage;	
		
		//리스트 출력 로직
		CountryDao countryDao = new CountryDao();
		if(searchWord == ""){
			list = countryDao.selectCountryListAll(rowPerPage, beginRow);
		}else{
			list = countryDao.selectSearchCountry(beginRow, rowPerPage, searchWord);
		}
		
		// 페이징 관련 로직 (lastPage, totalCount)
		int totalCount = 0;
		if(searchWord == ""){
			totalCount = countryDao.selectTotalCount(totalCount);
		}else{
			totalCount = countryDao.selectSearchTotalCount(searchWord, totalCount);
		}
		int lastPage = totalCount/rowPerPage;
		if(totalCount%rowPerPage!=0){
			lastPage+=1;
		}
	%>
		<div id="aside">
			 <jsp:include page="/inc/sidemenu.jsp"></jsp:include> 
		</div>
		<div id="section">
			<div id="main">
				<h2><strong>국가 목록</strong></h2>
				<div>
					<form method="post" action="<%=request.getContextPath()%>/country/countryList.jsp">
						<input type = "text" name = "searchWord">
						<button type="submit">검색</button>
					</form>
				</div>
				<table class="table table-striped" style="text-align: center; margin-top:10px;">
					<thead style="background-color:#5a493f; color: white;">
						<tr>
							<th>국가 번호</th>
							<th>국가</th>
							<th>도시 입력</th>
						</tr>
					</thead>
					<tbody>
					<%
						for(Country c : list){
					%>
							<tr>
								<td><%=c.getCountryId()%></td>
								<td><%=c.getCountry() %></td>
								<td><a href="<%=request.getContextPath()%>/city/insertCityForm.jsp?countryId=<%=c.getCountryId()%>&country=<%=c.getCountry()%>">입력</a></td>
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