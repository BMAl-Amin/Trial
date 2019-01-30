<%@ page import="com.gams.models.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="icon" href="images/register.png" type="image/png" />
	<link rel="stylesheet" type="text/css" href="css/dashboard_style.css" />
	<link rel="stylesheet" type="text/css" href="css/product_style.css" />
	<title>GAMS-Product Management</title>
	<script type="text/javascript" src="jScripts/jquery-3.3.1.js"></script>
	<script type="text/javascript" src="jScripts/ScrollableTablePlugin_1.0_min.js"></script>
	<script type="text/javascript" src="jScripts/dashboard_script.js"></script>
	<script type="text/javascript" src="jScripts/product_script.js"></script>
	<script type="text/javascript" src="jScripts/product_entry_script.js"></script>
	<script type="text/javascript" src="jScripts/product_table_edit_script.js"></script>
	<script type="text/javascript" src="jScripts/product_purchase_script.js"></script>
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
					<li class="mainMenuList">Client</li>
					<li class="mainMenuList active" id="product">Product</li>
					<li class="mainMenuList">Admin</li>
					<%
					if(!adminModel.getAdminType().equals("Admin")){
						%>
						<li class="mainMenuList" id="request">Requests(<%out.print(adminModel.getTempAdmin().size());%>)</li>
						<%
					}
					%>
				</ul>
				<img alt="Logout" src="images/logout_icon.png" id="logOutIcon">
			</div>
		</div>
		
		<div id="footer">
			<div id="leftSection">
				<h2 id="salesHeadline">Products</h2>
				<ul id="salesMenu" class="subMenu">
					<li class="subMenuList activeSideChoice" id="choiceNewEntry"><img src="images/entry_new.png"><label>Entry New</label></li>
					<li class="subMenuList" id="choicePurchaseProduct"><img src="images/sell_history_icon.png"><label>Purchase</label></li>
					
					<li class="subMenuList" id="choiceViewProduct"><img src="images/view_item.png"><label>View All</label></li>
					
				</ul>
			</div>
			
			
			<div id="rightSection">
				<!-- SECTION FOR NEW PRODUCT ENTRY -->
				<div class="operationField" id="entryNew">
					 <form id="entryNewForm" class="entryNewP">
					 	<fieldset class="field">
					 		<legend>New Product Entry</legend>
					 		
					 				<div class="inputLabel">Product Name</div><input type="text" class="entryInput" name="productName" id="pName"><br>
					 				<div class="inputLabel">Brand Name:</div><input type="text" class="entryInput" name="brandName" id="bName"><br>
					 				<div class="inputLabel">Purchase Price:</div><input type="text" class="entryInput" name="productPurchasePrice" id="pPurchasePrice"><br>
					 				<div class="inputLabel">Sale Price:</div><input type="text" class="entryInput" name="productSalePrice" id="pUnitPrice"><br>
					 				<div class="inputLabel">Product Type:</div>
					 				<select class="entryInput" name="productType" id="pType">
					 								<option>Food</option>
					 								<option>Beverage</option>
					 								<option>Oil</option>
					 								<option>Vegetables</option>
					 								<option>Grains</option>
					 								<option>Cosmetics</option>
					 								<option>Household Utensils</option>
					 							</select><br>
					 				<div class="inputLabel">Unit Type:</div>
					 				<select class="entryInput" name="productUnitType" id="puType">
					 								<option>Weight</option>
					 								<option>Volume</option>
					 								<option>Pieces</option>
					 							</select><br>
					 				<div class="inputLabel">Unit:</div>
					 				<select class="entryInput" name="productUnit" id="pUnit">
					 								<option>kg</option>
					 								<option>gram</option>
					 								<option>litter</option>
					 								<option>ml</option>
					 								<option>pcs</option>
					 							</select><br>
					 	</fieldset>
					 	<div id="entryNewBtn">Add Product</div>
					 </form>
					 <div id="entrySuccessful" class="entryNewP">
					 	<h3 id="sucH3">Product Entry Successful !</h3>
					 	<span class="sucLabel" id="productN"></span>
					 	<span class="sucLabel" id="brand"></span>
					 	<span class="sucLabel" id="type"></span>
					 	<span class="sucLabel" id="pPrice"></span>
					 	<span class="sucLabel" id="sPrice"></span>
					 	<span class="sucLabel" id="unit"></span>
					 </div>
					 <span id="entryMsg"></span>
				</div>
			
				<!-- SECTION FOR NEW PRODUCT PURCHASE -->
				<div class="operationField" id="purchaseProduct">
					<h2 id="purchaseH2">Purchase Products</h2>
					
					<div id="purchasePanel">
						<div id="searchChoice">
							Search Product By: <br>
							<label id="namLabel">Name</label>
							<div id="toggleDiv">
								<div id="toggleType"></div>
								<div id="toggleId"></div>
							</div>
							<label id="idLabel">Id</label>
						</div>
						
						<div id="searchByType">
							<label>Type: <select id="prType">
								<option></option>
								<option>Food</option>
								<option>Beverage</option>
								<option>Oil</option>
								<option>Vegetables</option>
					 			<option>Grains</option>
					 			<option>Cosmetics</option>
					 			<option>Household Utensils</option>
							</select></label>
							<label>Name: <select id="prName"><option></option></select></label>
							<label>Amount: <input type="number" id="purchaseAmountByType" value="1"></label>
							<label><input type="button" id="searchTypeBtn" value="Add"></label>
						</div>
						
						<div id="searchById">
							<div id="searchGroup">
								<input type="text" id="searchId" placeholder="Search: id/name">
								<img src="images/search_icon.png" id="srchLblImg" >
								<select id="expandableBox"></select>
							</div>
							<input type="number" id="purchaseAmountById" placeholder="Amount">
							<input type="button" id="searchIdBtn" value="Add">
						</div>
					</div>
					
					
					<div id="invoicePanel">
						<h2 id="invoiceHeader">Purchase Invoice</h2>
						<table id="invoiceTable">
							<tr class="thParentRow">
								<th>Id</th>
								<th id="nameHeaderP">Product Name</th>
								<th>Amount</th>
								<th>Product Price</th>
							</tr>
							
							<tr class="tdParentRow">
									
							</tr>
							
						</table>
						<div id="totalPCostDiv">Total Purchase Cost: <label id="costLabel"></label></div>
					</div>
				</div>
			
			
				
				
				<!-- SECTION FOR VIEW PRODUCT -->
				<div class="operationField" id="viewProduct">
					<h2>All Products</h2>
					<table id="productTable">
						<thead id="tHead">
							<tr>
								<th class="smallTd">Id</th>
								<th class="bigTd">Name</th>
								<th class="bigTd">Brand</th>
								<th class="smallTd">Sale Price</th>
								<th class="smallTd">Purchase Price</th>
								<th class="smallTd">Unit</th>
								<th class="smallTd"></th>
							</tr>
						</thead>
						<tbody id="tBody">
						
						</tbody>
					</table>
					<div id="editDiv">
						<img src="images/setting_icon.png">
						<div id="noticeDiv"><u>Notice:</u> To edit product values, double click on the desired row and press "Ok" after edit.</div>
					</div>
				</div>
				
				
				
			</div> <!-- RIGHT SECTION ENDS -->
		</div> <!-- FOOTER ENDS -->
	</div>
	
</body>
</html>