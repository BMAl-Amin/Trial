$(document).ready(function(){
	//var audio = new Audio('audio/welcome_tone.mp3');
	var beep = new Audio('audio/beep.mp3');
	beep.play();
	
	
	
	//Toggling between Login and Register
	$("#toggleRegister").click(function(){
		$("#loginSection").hide();
		$("#registerSection").show();
		$("#msg").hide();
		$("#msg2").hide();
		$("#msg3").hide();
		
	});
	
	$("#toggleLogin").click(function(){
		$("#registerSection").hide();
		$("#loginSection").show();
	});
	
	
	
	//Checking Username availability ...
	$("#userName").blur(function(e) {
		
		if ($("#userName").val() == null || $("#userName").val() == "") {
			beep.play();
			$("#msg").show();
			$("#msg").html("Username is required !");
		} else {
			$.ajax({
				type: "GET",
				url: "AdminAvailability",
				data: $("#signUp").serialize(),
				dataType: "html",
				success: function(response) {
					
					
					if (!$.trim(response)){
						$("#msg").hide();
					}else{
						beep.play();
						$("#msg").text(response);
						$("#msg").show();
					}
					
				},
				error: function(jqXHR, textStatus, errorThrown) {
					$("#msg").show();
					$("#msg").html(textStatus + " " + errorThrown);
				}
			});
		}
	});
	
	
	
	
	
	
	//Checking Email format 
	$("#email").blur(function() {
		var mail = $("#email").val();
		if(mail.indexOf("@") != -1){
			$("#msg2").hide();
		}else{
			beep.play();
			$("#msg2").text("Email format is incorrect!");
			$("#msg2").show();
		}
	});
	
	
	
	//Checking password and confirm password match
	$("#conPass").blur(function() {
		var a = $("#pass").val();
		var b = $("#conPass").val();
		if(a!=b){
			beep.play();
			$("#msg3").text("Password Not Matched !");
			$("#msg3").show();
			$("#pass").focus();
		}else{
			$("#pass").css('border', 'none');
			$("#msg3").hide();
		}
	});

	
	
	
	
});