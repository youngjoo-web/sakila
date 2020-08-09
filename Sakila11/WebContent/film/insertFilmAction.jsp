<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="vo.*"%>
<%
	Film f = new Film();
	f.setTitle(request.getParameter("title"));
	f.setDescription(request.getParameter("description"));
	f.setReleaseYear(request.getParameter("releaseYear"));
	f.setLanguageId(Integer.parseInt(request.getParameter("languageId")));
	f.setOriginalLanguageId(Integer.parseInt(request.getParameter("originalLanguageId")));
	f.setRentalDuration(Integer.parseInt(request.getParameter("rentalDuration")));
	f.setRentalRate(Double.valueOf(request.getParameter("rentalRate")));
	f.setReplacementCost(Double.valueOf(request.getParameter("replacementCost")));
	f.setLength(Integer.parseInt(request.getParameter("length")));
	f.setReplacementCost(Double.valueOf(request.getParameter("rentalRate")));
	f.setRating(request.getParameter("rating"));
	f.setSpecialFeatures(request.getParameter("specialFeatures"));
	
	FilmDao filmdao = new FilmDao();
	filmdao.insertFilm(f);
	response.sendRedirect(request.getContextPath() + "/film/filmList.jsp");
%>