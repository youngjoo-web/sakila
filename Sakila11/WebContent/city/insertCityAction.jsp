<%@page import="dao.CityDao"%>
<%@ page import ="vo.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	System.out.println("-------inserCityAction.jsp-------");
	
	String cityName = request.getParameter("cityName");
	System.out.println(cityName+" <- insertCityAction.jsp cityName");
	int countryId = Integer.parseInt(request.getParameter("countryId"));
	System.out.println(countryId+" <- insertCityAction.jsp countryId");
	
	City city = new City(); // City클래스의 정보를 담을 city변수 생성
	 // form에서 받아온 cityId값을 City클래스의 cityId값에 대입  
	city.setCity(cityName);
	city.setCountryId(countryId);
	CityDao cityDao = new CityDao(); 
	cityDao.insertCity(city); // 받은 정보를 담은  city변수를 insertCity메서드의 매개변수로 사용하고 메서드 호출
	
	response.sendRedirect(request.getContextPath()+"/country/countryList.jsp");
%>