<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="vo.*"%>
<%
	request.setCharacterEncoding("UTF-8");

	String name = request.getParameter("name");
	System.out.println(name+" <-- name");
	
	Language language = new Language();
	language.setName(name);
	
	LanguageDao languageDao = new LanguageDao();
	languageDao.insertlanguage(language);
	
	response.sendRedirect(request.getContextPath()+"/language/languageList.jsp");
	
%>