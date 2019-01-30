
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="icon" href="images/register.png" type="image/png" />
	<link rel="stylesheet" type="text/css" href="css/index_style.css">
	<title>GRMS-Login</title>
	<script type="text/javascript" src="jScripts/jquery-3.3.1.js"></script>
	<script type="text/javascript" src="jScripts/index_script.js"></script>
</head>

<body background="images/bcg.jpg">
	<h1 id="systemName">GROCERY MANAGEMENT SYSTEM<img class="loginRegIcon" src="images/register.png"></h1>
	<div id="formContainer">
		<%
			if(null != request.getAttribute("msg1")){
				%>
				<div id="msg1" class="notice"><%out.print(request.getAttribute("msg1")); %></div>
				<%
			}
		%>
		<!-- LOGIN FORM SECTION -->
		<div id="loginSection" class="formArea">
			<form action="LoginSrvlt" method="get">
				<fieldset>
					<legend class="formLegend">Admin Login</legend>
					<input name="userName" type="text" placeholder="Username" class="indexInput">
					<input name="password" type="password" placeholder="Password" class="indexInput">
					
					<input type="submit" class="submitButton" value="Login">
					<label class="bottomLabel">New admin ? <a id="toggleRegister">Click here to Register</a></label>
				</fieldset>
			</form>
				
		</div>
		
		<!-- REGISTER FORM SECTION -->
		<div id="registerSection" class="formArea">
			<form id="signUp" action="Registration" method="get">
				<fieldset>
					<legend class="formLegend">Admin Regisration</legend>
					<input id="userName" name="userName" type="text" placeholder="Username" class="indexInput" required="required">
					<div id="msg" class="indexInput notice"></div>
					<input name="fullName" type="text" placeholder="Full Name" class="indexInput" required="required">
					<input id="email" name="email" type="email" placeholder="email" class="indexInput" required="required">
					<div id="msg2" class="indexInput notice"></div>
					<input id="pass" name="password" type="password" placeholder="Password" class="indexInput" required="required">
					<input id="conPass" type="password" placeholder="Confirm Password" class="indexInput" required="required">
					<div id="msg3" class="indexInput notice"></div>
					<label class="indexInput"><input type="radio" name="adminType" value="Admin">Admin <input type="radio" name="adminType" value="Super_admin">Super Admin</label>
					<input type="submit" id="registerButton" class="submitButton" value="Register">
					<label class="bottomLabel">Already registered ? <a id="toggleLogin">Click here to Login</a></label>
				</fieldset>
				
			</form>	
			
		</div>
	</div>
	
</body>


</html>