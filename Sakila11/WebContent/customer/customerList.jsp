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
		<title>Custormer List</title>
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
			table {
				margin-top:20px;
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
	
		//리스트 출력 로직 
		CustomerDao customerDao = new CustomerDao();
		ArrayList<CustomerAndStoreAndAddress> list = customerDao.selectCustegoryList(rowPerPage, beginRow);
		
		// 페이징 관련 로직 (lastPage, totalCount)
		int totalCount = 0;
		totalCount = customerDao.selectTotalCount(totalCount);
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
				<h2><strong>고객 목록</strong></h2>
				<!-- payment List -->
				<a href="<%=request.getContextPath()%>/customer/insertCustomerForm.jsp">추가</a>
				<table class="table table-striped">
					<thead style="background-color:#5a493f; color: white;">
						<tr>
							<th width="100">고객<br>번호</th>
							<th>고객 이름</th>
							<th>고객 이메일</th>
							<th>대여매장</th>
							<th width="100">대여여부</th>
							<th>대여일자</th>
						</tr>
					</thead>
					<tbody>
					<%
						for(CustomerAndStoreAndAddress c : list) {
					%>
						<tr>
							<td><%=c.getCustomer().getCustomerId()%></td>
							<td><%=c.getCustomer().getFirstName()%>&nbsp;<%=c.getCustomer().getLastName()%></td>
							<td><%=c.getCustomer().getEmail()%></td>
							<td><%=c.getAddress().getAddress()%></td>
							<td><%=c.getCustomer().getActive()%></td>
							<td><%=c.getCustomer().getCreateDate()%></td>
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