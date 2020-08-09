<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dao.*"%>
<%@ page import = "vo.*"%>  
<%
	request.setCharacterEncoding("UTF-8");

	System.out.println("-------languageUpdateAction-------");
	int languageId= Integer.parseInt(request.getParameter("languageId"));
	System.out.println(languageId+" <-- languageId");
	String name = request.getParameter("name");
	System.out.println(name+" <-- name");
	Language language = new Language();
	language.setLanguageId(languageId);
	language.setName(name);
	LanguageDao languageDao = new LanguageDao();
	languageDao.updateLanguage(language);
	
	response.sendRedirect(request.getContextPath()+"/language/languageList.jsp");
	
%>