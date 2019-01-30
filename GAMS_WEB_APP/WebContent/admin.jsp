<%@ page import="com.gams.models.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="icon" href="images/register.png" type="image/png" />
	<link rel="stylesheet" type="text/css" href="css/dashboard_style.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Admin-GAMS</title>
	<script type="text/javascript" src="jScripts/jquery-3.3.1.js"></script>
	<script type="text/javascript" src="jScripts/dashboard_script.js"></script>
</head>

<body background="images/bcg.jpg">
	<%
	Object admin = request.getSession(false).getAttribute("adminInfo") ;
	Admin adminModel = (Admin)admin;
	%>
	<div id="mainContainer">
		<div id="header">
			<div id="leftHeader">
				<img id="homeIcon" alt="" src="images/dash_home_icon.png">
				<h1 id="name">GAMS-DASHBOARD</h1>
			</div>
			<div id="middleHeader">
				<img alt="" src="images/user_icon.png" id="userIcon">
				<label><%out.print(adminModel.getUserName()); %></label>
			</div>
			<div id="rightHeader">
				<ul id="mainMenu">
					<!-- <li class="mainMenuList" id="uName">User: </li> -->
					<li class="mainMenuList" id="sales">Sales</li>
					<li class="mainMenuList" id="client">Client</li>
					<li class="mainMenuList" id="product">Product</li>
					<li class="mainMenuList active" id="admin">Admin</li>
					
				</ul>
				<img alt="Logout" src="images/logout_icon.png" id="logOutIcon">
			</div>
		</div>
		
		<div id="footer">
			<div id="leftSection">
				<h2 id="salesHeadline">Admin</h2>
				<ul id="salesMenu" class="subMenu">
					<li class="subMenuList"><img src="images/sell_now_icon.png"><label>Current Admin</label></li>
					<li class="subMenuList"><img src="images/sell_history_icon.png"><label>Admin Request</label></li>
					<li class="subMenuList"><img src="images/sell_history_icon.png"><label>Edit existing admin</label></li>
				</ul>
			</div>
			<div id="rightSection">
				Jquery works...Admin SEction
			</div>
		</div>
		
		
	</div>
	
</body>
</html>