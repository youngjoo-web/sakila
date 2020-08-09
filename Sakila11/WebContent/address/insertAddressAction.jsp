<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="util.*" %>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<%
	System.out.println("------insertAddressAction.jsp------");
	request.setCharacterEncoding("UTF-8");
	/* String countryName = request.getParameter("countryName");
	System.out.println(countryName+" <-- countryName"); */
	/* String cityName = request.getParameter("cityName");
	System.out.println(cityName+" <-- cityName"); */
	int cityId = Integer.parseInt(request.getParameter("cityId"));
	System.out.println(cityId+" <-- cityId");
	String district = request.getParameter("district");
	System.out.println(district+" <-- district");
	String addressName = request.getParameter("address");
	System.out.println(addressName+" <-- address");
	String address2 = request.getParameter("address2");
	System.out.println(address2+" <-- address2");
	String postalCode = request.getParameter("postalCode");
	System.out.println(postalCode+" <-- postalCode");
	String phone = request.getParameter("phone");
	System.out.println(phone+" <-- phone");
	
	Address address = new Address();
	address.setCityId(cityId);
	address.setDistrict(district);
	address.setAddress(addressName);
	address.setAddress2(address2);
	address.setPostalCode(postalCode);
	address.setPhone(phone);
	
	AddressDao addressDao = new AddressDao();
	addressDao.insertAddress(address);
	
	response.sendRedirect(request.getContextPath()+"/city/cityList.jsp");
%>