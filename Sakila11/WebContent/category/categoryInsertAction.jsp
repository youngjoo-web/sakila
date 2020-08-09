<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import = "vo.*"%>
<%
	request.setCharacterEncoding("UTF-8");

	String name =  request.getParameter("name");
	System.out.println(name+" <-- name");
	
	/*
		 입력 : int, String --> Category, HashMap
		 리턴값 : void
	*/
	Category category = new Category();
	category.setName(name);
	
	CategoryDao categoryDao = new CategoryDao();
	categoryDao.insertCategory(category);
	
	response.sendRedirect(request.getContextPath()+"/category/categoryList.jsp");
%>