<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div style="text-align: center;">
	<div style="margin-top: 20px; color:white;">
		<h3>MENU</h3>
		<hr>
	</div>
		<button type="button" class="btn" style="color:white !important; width:100%; height:60px; font-size: 18px" onclick="location.href='<%=request.getContextPath()%>/index.jsp'">
			<strong>HOME</strong>
		</button>
	</div>
	<div class="dropdown">
		<button type="button" class="btn dropdown-toggle" data-toggle="dropdown" style="color:white !important; width:100%; height:60px; font-size: 18px;">
			<i class='far fa-building'><strong>필름 관련</strong></i>
		</button>
		<div class="dropdown-menu" style="color: !important; background-color: !important;">
			<a class="nav-link" href="<%=request.getContextPath()%>/film/filmList.jsp"><i class="far fa-address-card">FILM</i></a>
			<a class="nav-link" href="<%=request.getContextPath()%>/filmText/filmTextList.jsp"><i class="far fa-address-card">FILM TEXT</i></a>
			<a class="nav-link" href="<%=request.getContextPath()%>/filmCategory/filmCategoryList.jsp"><i class="far fa-address-card">FILM CATEGORY</i></a>
			<a class="nav-link" href="<%=request.getContextPath()%>/filmActor/filmActorList.jsp"><i class="far fa-address-card">FILM ACTOR</i></a>
			<a class="nav-link" href="<%=request.getContextPath()%>/actor/actorList.jsp"><i class="far fa-address-card">ACTOR</i></a>
			<a class="nav-link" href="<%=request.getContextPath()%>/category/categoryList.jsp"><i class="far fa-address-card">CATEGORY</i></a>
			<a class="nav-link" href="<%=request.getContextPath()%>/language/languageList.jsp"><i class="far fa-address-card">LANGUAGE</i></a>
		</div>
	</div>
	<div class="dropdown" style="margin-bottom: 15x;">
		<button type="button" class="btn dropdown-toggle" data-toggle="dropdown" style="color:white !important; width:100%; height:60px; font-size: 18px;">
			<i class='far fa-building'><strong>주소관련</strong></i>
		</button>
		<div class="dropdown-menu" style="color:#6c757d !important; background-color: !important;">
			<a class="nav-link" href="<%=request.getContextPath()%>/country/countryList.jsp"><i class="far fa-address-card">COUNTRY</i></a>
			<a class="nav-link" href="<%=request.getContextPath()%>/city/cityList.jsp"><i class="far fa-address-card">CITY</i></a>
			<a class="nav-link" href="<%=request.getContextPath()%>/address/addressList.jsp"><i class="far fa-address-card">ADDRESS</i></a>
		</div>
	</div>
	<div class="dropdown" style="margin-bottom: 15x;">
		<button type="button" class="btn dropdown-toggle" data-toggle="dropdown" style="color:white !important; width:100%; height:60px; font-size: 18px;">
			<i class='far fa-building'><strong>매장관련</strong></i>
		</button>
		<div class="dropdown-menu" style="color:#6c757d !important; background-color: !important;">
			<a class="nav-link" href="<%=request.getContextPath()%>/store/storeList.jsp"><i class="far fa-address-card">STORE</i></a>
			<a class="nav-link" href="<%=request.getContextPath()%>/staff/staffList.jsp"><i class="far fa-address-card">STAFF</i></a>
			<a class="nav-link" href="<%=request.getContextPath()%>/payment/paymentList.jsp"><i class="far fa-address-card">PAYMENT</i></a>
			<a class="nav-link" href="<%=request.getContextPath()%>/inventory/inventoryList.jsp"><i class="far fa-address-card">INVENTORY</i></a>
			<a class="nav-link" href="<%=request.getContextPath()%>/rental/rentalList.jsp"><i class="far fa-address-card">RENTAL</i></a>
			<a class="nav-link" href="<%=request.getContextPath()%>/customer/customerList.jsp"><i class="far fa-address-card">CUSTOMER</i></a>
		</div>
	</div>
</div>

	