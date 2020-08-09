<%@page import="vo.*"%>
<%@page import="dao.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//컨트롤 로직 1) request 담당 로직
	int categoryId = Integer.parseInt(request.getParameter("categoryId"));
	System.out.println(categoryId+" <-- categoryId");
	//컨트롤 로직 2) model 호출 담당 로직
	CategoryDao categoryDao = new CategoryDao();
	categoryDao.deleteCategory(categoryId);
	
	response.sendRedirect(request.getContextPath()+"/category/categoryList.jsp");
%>