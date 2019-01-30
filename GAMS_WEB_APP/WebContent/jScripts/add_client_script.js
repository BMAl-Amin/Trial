$(document).ready(function() {
	
	$(document).on("click", "#addClientBtn", function() {
		var name = $("#clientNameBox").val();
		var phone = $("#clientPhoneBox").val();
		var address = $("#clientAddressBox").val();
		
		if(name=="" || name==null){
			$("#clientNameBox").focus();
			alert("Fill each and every field, please !");
		}else if(phone=="" || phone==null){
			$("#clientPhoneBox").focus();
			alert("Fill each and every field, please !");
		}else if(address=="" || address==null){
			$("#clientAddressBox").focus();
			alert("Fill each and every field, please !");
		}else{
			$.post("AddClient", {
				name: name,
				phone: phone,
				address: address
			}, function(response) {
				alert("Entry of "+name+" status: "+response);
				$("#clientPhoneBox").val("");
				$("#clientNameBox").val("");
				$("#clientAddressBox").val("");
			})
		}
	}); //ends
	
	
	
	
	
});