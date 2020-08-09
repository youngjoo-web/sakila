<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "java.util.*" %>
<%
	request.setCharacterEncoding("UTF-8");

	System.out.println("-------insertStaffAction.jsp-------");
	int cityId = Integer.parseInt(request.getParameter("cityId"));
	System.out.println(cityId+" <-- cityId");
	String addressName = request.getParameter("address");
	System.out.println(addressName+" <-- address");
	String address2 = request.getParameter("address2");
	System.out.println(address2+" <-- address2");
	String district = request.getParameter("district");
	System.out.println(district+" <-- district");
	String postalCode = request.getParameter("postalCode");
	System.out.println(postalCode+" <-- postalCode");
	String phone = request.getParameter("phone");
	System.out.println(phone+" <-- phone");
	
	Address address = new Address();
	address.setAddress(addressName);
	address.setAddress2(address2);
	address.setCityId(cityId);
	address.setDistrict(district);
	address.setPostalCode(postalCode);
	address.setPhone(phone);
	
	AddressDao addressDao = new AddressDao();
	
	int addressId = addressDao.insertAddressAndSelectKey(address);
	String firstName = request.getParameter("firstName");
	System.out.println(firstName+" <-- firstName");
	String lastName = request.getParameter("lastName");
	System.out.println(lastName+" <-- lastName");
	String picture = request.getParameter("picture");
	System.out.println(picture+" <-- picture");
	String email = request.getParameter("email");
	System.out.println(email+" <-- email");
	int storeId = Integer.parseInt(request.getParameter("storeId"));
	System.out.println(storeId+" <-- storeId");
	String userName = request.getParameter("userName");
	System.out.println(userName+" <-- userName");
	String password = request.getParameter("password");
	System.out.println(password+" <-- password");

	Staff staff = new Staff();
	staff.setFirstName(firstName);
	staff.setLastName(lastName);
	staff.setAddressId(addressId);
	staff.setEmail(email);
	staff.setStoreId(storeId);
	staff.setActive(1);
	staff.setUserName(userName);
	staff.setPassword(password);
	
	StaffDao staffDao = new StaffDao();
	staffDao.insertStaff(staff);
	
	response.sendRedirect(request.getContextPath()+"/staff/staffList.jsp");
%>