<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<%
	request.setCharacterEncoding("UTF-8");

	String firstName = request.getParameter("firstName");
	System.out.println(firstName+" <-- firstName");
	String lastName = request.getParameter("lastName");	
	System.out.println(lastName+" <-- lastName");
	
	Actor actor = new Actor();
	actor.setFirstName(firstName);
	actor.setLastName(lastName);
	ActorDao actorDao = new ActorDao();
	actorDao.insertActor(actor);
	
	response.sendRedirect(request.getContextPath()+"/actor/actorList.jsp");
%>