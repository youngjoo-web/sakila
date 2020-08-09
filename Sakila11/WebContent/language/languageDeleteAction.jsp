<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="vo.*"%>
<%
	System.out.println("------languageDeleteAction------ ");
	int languageId = Integer.parseInt(request.getParameter("languageId"));
	System.out.println(languageId+" <-- languageId");
	
	LanguageDao languageDao = new LanguageDao();
	languageDao.deleteLanaguage(languageId);
	
	response.sendRedirect(request.getContextPath()+"/language/languageList.jsp");
	
%>