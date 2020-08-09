<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import ="vo.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	System.out.println("categoryUpdateAction----------");
	int categoryId = Integer.parseInt(request.getParameter("categoryId"));
	System.out.println(categoryId+" <-- categoryId");
	String name = request.getParameter("name");
	System.out.println(name+" <-- name");
	
	Category category = new Category();
	category.setCategoryId(categoryId);
	category.setName(name);
	CategoryDao categoryDao = new CategoryDao();
	categoryDao.updateCategory(category);
	
	response.sendRedirect(request.getContextPath()+"/category/categoryList.jsp");
%>