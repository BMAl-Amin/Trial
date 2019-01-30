$(document).ready(function(){
	$("body").fadeIn(2000);
   
   //SETTING UP A DIV FRAME
   $("#leftSection").css("background-image", "url('images/sideBg.jpg')");
   
   //Calling product page
   $("#product").click(function(){
	   window.location="product.jsp";
   });
   
   
   //Calling Sales page
   $("#sales").click(function(){
	   window.location="dashboard.jsp";
   });
   
 //Calling Sales page
   $("#admin").click(function(){
	   window.location="admin.jsp";
   });
   
 //Calling Client page
   $("#client").click(function(){
	   window.location="client.jsp";
   });
   
  
   //Request
   $("#request").click(function() {
	   $("#footer").hide(2000);
   });
   
   
   //Logout
   $("#logOutIcon").click(function() {
	   $("#logOutIcon").css("height", "35px");
	   $("#logOutIcon").fadeOut(1000);
	   location.replace("Logout");
   });
   
});