
<%@ page import="com.gams.models.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="icon" href="images/register.png" type="image/png" />
	<link rel="stylesheet" type="text/css" href="css/dashboard_style.css" />
	<link rel="stylesheet" type="text/css" href="css/client_style.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Client Management-GAMS</title>
	<script type="text/javascript" src="jScripts/jquery-3.3.1.js"></script>
	<script type="text/javascript" src="jScripts/dashboard_script.js"></script>
	<script type="text/javascript" src="jScripts/client_script.js"></script>
	<script type="text/javascript" src="jScripts/add_client_script.js"></script>
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
					<li class="mainMenuList active" id="client">Client</li>
					<li class="mainMenuList" id="product">Product</li>
					<li class="mainMenuList" id="admin">Admin</li>
					
				</ul>
				<img alt="Logout" src="images/logout_icon.png" id="logOutIcon">
			</div>
		</div>
		
		<div id="footer">
		
			<!-- LEFT SECTION -->
			<div id="addClientDiv">
				<h2 id="addClientH2">Add New Client</h2>
				<form id="addClientForm" method="post">
					<label> Client Name:</label><br>
					<input type="text" id="clientNameBox"><br>
					<label>Client Phone: </label><br>
					<input type="text" id="clientPhoneBox" value="+880"><br>
					<label>Client Address: </label><br>
					<textarea rows="4" cols="25" id="clientAddressBox"></textarea><br>
					<input type="button" value="Add Now" id="addClientBtn">
				</form>
			</div>
			
			
			
			<!-- MID SECTION -->
			<div id="clientOperationDiv">
				<fieldset id="clientOperationField">
					<legend id="clientOperationLegend">Client Operation</legend>
					<input type="text" id="clientSearchBoxMain" placeholder="Search Client using ID or Name">
					<input type="text" id="paymentAnountBox" placeholder="Amount">
					<input type="button" value="PaY NoW" id="clientPaymentBtn">
				</fieldset>
				<div class="clientOperationInnerDiv" id="paymentDiv">
					<h3 id="cIdH3" class="paymentH3">Client Id: </h3>
					<h3 id="cNameH3" class="paymentH3">Client name: </h3>
					<h3 id="cAddressH3" class="paymentH3">Client Address: </h3>
					<h3 id="cPhoneH3" class="paymentH3">Client phone: </h3>
					<h3 id="cTotalBillH3" class="paymentH3">Total Bill: </h3>
					<h3 id="cPayRateH3" class="paymentH3">Payment Rate: </h3>
					<h3 id="cArrearH3" class="paymentH3">Current Arrear: </h3>
					<h3 id="cHistoryH3" class="paymentH3">Purchase History</h3>
					<div id="cHistoryDiv">
						<ul id="cHistoryUl">
						
						</ul>
					</div>
				</div>
			</div>
			
			
			
			<!-- RIGHT SECTION -->
			<div id="clientInfoDiv">
				<h2 id="topClientH2">Top 5 Clients</h2>
				<select id="topTypeSelect">
					<option>By Highest Pay Rate</option>
					<option>By Lowest Pay Rate</option>
					<option>By Highest Bill</option>
				</select>From
				<select id="topTimeSelect">
					<option>Current Month</option>
					<option>Today</option>
					<option>Current Year</option>
					<option>2018</option>
				</select>
				<div id="clientInfoInnerDiv">
				
				</div>
			</div>
			
			<div id="clientSuggestDiv">
				<ul id="clientSuggestUl"></ul>
			</div>
			
		</div>
		
		
	</div>
	
</body>
</html>