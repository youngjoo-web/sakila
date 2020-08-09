<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%@ page import="dao.*" %>
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
		<title>actor 수정 폼</title>
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
			#main {
				padding-top: 40px;
			}
		</style>
	</head>
	<body>
	<%
		int actorId=  Integer.parseInt(request.getParameter("actorId"));
		System.out.println("updateActorForm-------------------");
		ActorDao actorDao = new ActorDao();
		Actor actor = actorDao.selectActorOne(actorId);
	%>
		<div id="aside">
			 <jsp:include page="/inc/sidemenu.jsp"></jsp:include> 
		</div>
		<div id="section">
			<div id="main">
				<h1>actor 수정 폼</h1>
				<form method="post" action="<%=request.getContextPath()%>/actor/actorUpdateAction.jsp">
					<div>
						actor_id : 
						<input type="text" name="actorId" value=<%=actorId%> readonly="readonly">
					</div>
					<div>
						first_name : 
						<input type="text" name="firstName" value=<%=actor.getFirstName()%>>
					</div>
					<div>
						last_name : 
						<input type="text" name="lastName" value=<%=actor.getLastName()%>>
					</div>
					<button type="submit">저장</button>
				</form>
			</div>
		</div>
	</body>
</html>