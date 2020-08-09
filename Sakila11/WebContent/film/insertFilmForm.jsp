<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
		integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
		<title>Insert Film Form</title>
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
		LanguageDao langDao = new LanguageDao();
		ArrayList<Language> langIdList = langDao.selectLanguageIdList();
	%>
		<div id="aside">
			<jsp:include page="/inc/sidemenu.jsp"></jsp:include>
		</div>
		<div id="section">
			<div id="main">
			<h1>필름 등록</h1>
				<form method = "post" action = "<%=request.getContextPath()%>/film/insertFilmAction.jsp">
					<table class="table">
						<tr>
							<th>제목</th>
							<td>
								<input type="text" name="title">
							</td>
						</tr>
						<tr>
							<th>줄거리</th>
							<td>
								<input type="text" name="description">
							</td>
						</tr>
						<tr>
							<th>개봉일</th>
							<td>
								<select name="releaseYear">
								<%
									for (int i = 1950; i < 2050; i += 1) {
								%>
										<option value=<%=i%>><%=i%></option>
								<%
									}
								%>
								</select>
							</td>
						</tr>
						<tr>
							<th>언어</th>
							<td>
								<select name="languageId">
								<%
									for (Language lang : langIdList) {
								%>
										<option value=<%=lang.getLanguageId()%>><%=lang.getName()%></option>
								<%
									}
								%>
								</select>
							</td>
						</tr>
						<tr>
							<th>원본언어</th>
							<td>
								<select name="originalLanguageId">
									<option disabled>==선택해주세요==</option>
									<%
										for (Language lang : langIdList) {
									%>
										<option value=<%=lang.getLanguageId()%>><%=lang.getName()%></option>
									<%
										}
									%>
								</select>
							</td>
						</tr>
						<tr>
							<th>대여기간</th>
							<td>
								<select name="rentalDuration">
									<option disabled>==선택해주세요==</option>
									<%
										for (int i = 1; i < 1000; i += 1) {
									%>
										<option value="<%=i%>"><%=i%></option>
									<%
										}
									%>
								</select>
							</td>
						</tr>
						<tr>
							<th>대여료</th>
							<td>
								<select name="rentalRate">
									<option disabled>==선택해주세요==</option>
								<%
									for (int i = 0; i < 5; i += 1) {
								%>
									<option value="<%=i + 0.99%>"><%=i + 0.99%></option>
								<%
									}
								%>
								</select>
							</td>
						</tr>
						<tr>
							<th>재생시간</th>
							<td>
								<input type="text" name="length">
							</td>
						</tr>
						<tr>
							<th>연체료</th>
							<td>
								<select name="replacementCost">
									<option disabled>==선택해주세요==</option>
								<%
									for (int i = 0; i < 31; i += 1) {
								%>
										<option value="<%=i + 0.99%>"><%=i + 0.99%></option>
								<%
									}
								%>
								</select>
							</td>
						</tr>
						<tr>
							<th>영화등급</th>
							<td>
								<select name="rating">
									<option disabled>==선택해주세요==</option>
									<option value="G">G</option>
									<option value="PG">PG</option>
									<option value="PG-13">PG-13</option>
									<option value="R">R</option>
									<option value="NC-17">NC-17</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>특별편</th>
							<td>
								<input type="checkbox" name="specialFeatures" value="Trailers">Trailers<br>
								<input type="checkbox" name="specialFeatures" value="Commentaries">Commentaries<br>
								<input type="checkbox" name="specialFeatures" value="Deleted Scenes">Deleted Scenes<br>
								<input type="checkbox" name="specialFeatures" value="Behind the Scenes">Behind the Scenes
							</td>
						</tr>
					</table>
					<div>
						<button type = "submit">등록</button>
					</div>
				</form>
			</div>	
		</div>
	</body>
</html>