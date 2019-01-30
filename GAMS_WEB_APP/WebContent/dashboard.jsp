
<%@ page import="com.gams.models.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="icon" href="images/register.png" type="image/png" />
	<link rel="stylesheet" type="text/css" href="css/dashboard_style.css" />
	<link rel="stylesheet" type="text/css" href="css/sales_style.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Dasboard-GAMS</title>
	<script type="text/javascript" src="jScripts/jquery-3.3.1.js"></script>
	<script type="text/javascript" src="jScripts/dashboard_script.js"></script>
	<script type="text/javascript" src="jScripts/sell_now_script.js"></script>
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
					<li class="mainMenuList active" id="sales">Sales</li>
					<li class="mainMenuList" id="client">Client</li>
					<li class="mainMenuList" id="product">Product</li>
					<li class="mainMenuList" id="admin">Admin</li>
					
				</ul>
				<img alt="Logout" src="images/logout_icon.png" id="logOutIcon">
			</div>
		</div>
		
		<div id="footer">
			<div id="leftSection">
				<h2 id="salesHeadline">Sales</h2>
				<ul id="salesMenu" class="subMenu">
					<li class="subMenuList activeSideChoice" id="sellNowLeftBtn"><img src="images/sell_now_icon.png"><label>Sell Now</label></li>
					<li class="subMenuList" id="sellHistoryLeftBtn"><img src="images/sell_history_icon.png"><label>Sale History</label></li>
				</ul>
			</div>
			
			
			<!-- Operational Portion of the PAGE -->
			<div id="rightSection">
				<div class="operationField" id="sellNow">
					 <div id="sellNowSearchDiv" class="saleNowElements">
					 	<input type="text" id="searchClientBox" placeholder="Search Client By Name/Id">
					 	<input type="text" id="searchItemBox" placeholder="Search Product By Name/Id">
					 	<input type="text" id="sellAmountBox" placeholder="Amount">
					 	<input type="button" id="addSellItemBtn" value="Add">
					 	
					 </div>
					 <div id="sellSearchExpandDiv" class="saleNowElements">
					 	<ul id="sellClientSuggestUl">
					 		
					 	</ul>
					 </div>
					 <div id="sellSearchExpandDiv2" class="saleNowElements">
					 	<ul id="sellItemSuggestUl">
					 		
					 	</ul>
					 </div>
					 <div id="sellNowInvoiceDive" class="saleNowElements">
					 	<h2><u>Invoice: Sale</u></h2>
					 	<h3 id="clientH3">Client Name: </h3>
					 	<h4 id="clientIdh4">Client Id:</h4>
					 	<div id="sellInvoiceHeadDiv">
					 		<table>
					 		<tr>
					 			<td class="idInvoieTd">Id</td>
					 			<td class="nameInvoieTd">Product Name</td>
					 			<td class="amountInvoieTd">Amount</td>
					 			<td class="priceInvoieTd">Price</td>
					 		</tr>
					 		</table>
					 	</div>
					 	<div id="sellInvoiceBodyDiv">
					 		<table id="sellBodyTbl">
					 		
					 		</table>
					 	</div>
					 	<div id="sellInvoiceFootDiv">
					 		<label id="totalDueCost">Arrears: </label>
					 		<input id="sellPaymentBox" type="text" placeholder="Pay Now">
					 		<label id="totalSellCost">Total Cost:</label>
					 	</div>
					 	<input id="sellBtn" type="button" value="Sell Now">
					 </div>
					 
				</div>
				<div class="operationField" id="saleHistory">
					Sale history...
				</div>
			</div>
		</div>
		
		
	</div>
	
</body>
</html>