<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%@ page import="dao.*" %>
<%
	int actorId = Integer.parseInt(request.getParameter("actorId"));
	System.out.println("deleteActorAction-------------");
	System.out.println(actorId+" <-- actorId");
	
	
	ActorDao actorDao = new ActorDao();
	actorDao.deleteActor(actorId);
	
	response.sendRedirect(request.getContextPath()+"/actor/actorList.jsp");
	
%>