<%@page import="dao.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import = "vo.*" %>
<%@page import = "java.util.*" %>
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
		<title>Address List</title>
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
		
		// 페이징 관련 로직(currentPage, rowPerPage, beginRow)
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 10;
		int beginRow = (currentPage-1)*rowPerPage;
		
		// 리스트 출력 로직
		AddressDao addressDao = new AddressDao();
		ArrayList<AddressAndCityAndCountry> list = addressDao.selectAddressAndCityAndCountry(rowPerPage, beginRow);
		
		// 페이징 관련 로직 (lastPage, totalCount)
		int totalCount = 0;
		totalCount = addressDao.selectTotalCount(totalCount);
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
				<h2><strong>매장 주소 목록</strong></h2>
				<table class="table table-striped" style="text-align: center; margin-top: 20px;">
					<thead style="background-color:#5a493f; color: white;">
						<tr>
							<th width="100">주소번호</th>
							<th width="120">국가</th>
							<th width="120">도시</th>
							<th>시.구</th>
							<th>주소</th>
							<th>상세주소</th>
							<th>우편번호</th>
							<th>휴대번호</th>
						</tr>
					</thead>
					<tbody>
					<%
						for(AddressAndCityAndCountry a : list){
					%>
						<tr>
							<td><%=a.getAddress().getAddressId()%></td>
							<td><%=a.getCountry().getCountry()%></td>
							<td><%=a.getCity().getCity()%></td>
							<td><%=a.getAddress().getDistrict()%></td>
							<td><%=a.getAddress().getAddress()%></td>
							<td><%=a.getAddress().getAddress2()%></td>
							<td><%=a.getAddress().getPostalCode()%></td>
							<td><%=a.getAddress().getPhone()%></td>
						</tr>
					<%
						}
					%>
					</tbody>
				</table>
				<!-- 페이징 링크 -->
				<div>
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