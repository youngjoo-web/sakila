<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "vo.*"%>
<%@ page import= "dao.*"%>
<%
	request.setCharacterEncoding("UTF-8");

	int actorId=  Integer.parseInt(request.getParameter("actorId"));
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	System.out.println(actorId+" "+firstName+" "+lastName);
	
	Actor actor = new Actor();
	actor.setActorId(actorId);
	actor.setFirstName(firstName);
	actor.setLastName(lastName);
	ActorDao actorDao = new ActorDao();
	actorDao.updateActor(actor);
	
	response.sendRedirect(request.getContextPath()+"/actor/actorList.jsp");
%>