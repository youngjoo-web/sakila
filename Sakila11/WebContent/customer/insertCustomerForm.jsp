<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.*"%>
<%@ page import="vo.*"%>
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
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
		integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
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
			   margin-left: 300px;
			   background: white;
			}
			#main {
				padding-top:40px;
				padding-right:50%;
				padding-bottom: 100px;
			}
			th{
				text-align: center;
				background-color:#E6CFA6;
				width: 20%;
			}
		</style>
	</head>
	<body>
	<%
		CityDao cityDao = new CityDao();
		ArrayList<City> cityList = cityDao.selectCityList();
		StoreDao storeDao = new StoreDao();
		ArrayList<Integer> storeIdList = storeDao.selectStoreIdList();
	%>
		<div id="aside">
			<jsp:include page="/inc/sidemenu.jsp"></jsp:include>
		</div>
		<div id="section">
			<div id="main">
				<h1>Customer Insert</h1>
				<div>
					<form method="post" action="<%=request.getContextPath()%>/customer/insertCustomerAction.jsp">
						<table class="table">
							<tr>
								<th>first name</th>
								<td>
									<input type="text" name="firstName">
								</td>
							</tr>
							<tr>
								<th>last name</th>
								<td>
									<input type = "text" name="lastName">
								</td>
							</tr>
							<tr>
								<th>address</th>
								<td>
									<input type = "text" name="address">
								</td>
							</tr>
							<tr>
								<th>address2</th>
								<td>
									<input type = "text" name="address2">
								</td>
							</tr>
							<tr>
								<th>district</th>
								<td>
									<input type = "text" name="district">
								</td>
							</tr>
							<tr>
								<th>City_id</th>
								<td>
									<select name="cityId">
										<option disabled>==선택해주세요==</option>
										<%
											for (City c : cityList) {
										%>
												<option value="<%=c.getCityId()%>"><%=c.getCity()%></option>
										<%
											}
										%>
									</select>
								</td>
							</tr>
							<tr>
								<th>POSTAL CODE</th>
								<td>
									<input type = "text" name="postalCode">
								</td>
							</tr>
							<tr>
								<th>phone</th>
								<td>
									<input type = "text" name="phone">
								</td>
							</tr>
							<tr>
								<th>email</th>
								<td>
									<input type = "text" name="email">
								</td>
							</tr>
							<tr>
								<th>Store_id</th>
								<td>
									<select name = "storeId">
										<option disabled>==선택해주세요==</option>
										<%
											for (Integer storeId : storeIdList){
										%>
												<option value = "<%=storeId%>"><%=storeId %></option>
										<%
											}
										%>
									</select>
								</td>
							</tr>
						</table>
						<div>
							<button type = "submit">회원등록</button>
						</div>
					</form>
				</div>
			</div>	
		</div>
	</body>
</html>