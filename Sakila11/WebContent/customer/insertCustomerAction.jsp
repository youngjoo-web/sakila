<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.*"%>
<%@ page import="vo.*"%>
<%
	request.setCharacterEncoding("UTF-8");

	int cityId = Integer.parseInt(request.getParameter("cityId"));
	System.out.println(cityId + " <-- cityId");
	String addressName = request.getParameter("address");
	System.out.println(addressName + " <-- address");
	String address2 = request.getParameter("address2");
	System.out.println(address2 + " <-- address2");
	String district = request.getParameter("district");
	System.out.println(district + " <-- district");
	String postalCode = request.getParameter("postalCode");
	System.out.println(postalCode + " <-- postalCode");
	String phone = request.getParameter("phone");
	System.out.println(phone + " <-- phone");

	Address address = new Address();
	address.setAddress(addressName);
	address.setAddress2(address2);
	address.setCityId(cityId);
	address.setDistrict(district);
	address.setPostalCode(postalCode);
	address.setPhone(phone);

	AddressDao addressDao = new AddressDao();

	int addressId = addressDao.insertAddressAndSelectKey(address);
	System.out.println(addressId);
	String firstName = request.getParameter("firstName");
	System.out.println(firstName+" <-- firstName");
	String lastName = request.getParameter("lastName");
	System.out.println(lastName+" <-- lastName");
	String email = request.getParameter("email");
	System.out.println(email+" <-- email");
	int storeId = Integer.parseInt(request.getParameter("storeId"));
	int active = 1;
	Customer customer = new Customer();
	customer.setFirstName(firstName);
	customer.setLastName(lastName);
	customer.setEmail(email);
	customer.setActive(active);
	customer.setAddressId(addressId);
	customer.setStoreId(storeId);
	
	
	CustomerDao cusDao = new CustomerDao();
	cusDao.insertCustomer(customer);
	/*
	
	Customer customer = new Customer();
	customer.setFirstName(request.getParameter("firstName"));
	customer.setLastName(request.getParameter("lastName"));
	customer.setEmail(request.getParameter("email"));
	customer.setAddressId(addressId);
	customer.setStoreId(Integer.parseInt(request.getParameter("storeId")));
	customer.setActive(1);
	CustomerDao cusDao = new CustomerDao();
	cusDao.insertCustomer(customer);*/
	response.sendRedirect(request.getContextPath()+"/customer/customerList.jsp");
%>