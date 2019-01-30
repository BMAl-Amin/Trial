$(document).ready(function(){
	
	//ENTRY NEW PRODUCT:: FORM VALIDATION AND MAKE AJAX REQUEST
	$("#entryNewBtn").click(function() {
		var pName = $("#pName").val();
		var bName = $("#bName").val();
		var pUnitPrice = $("#pUnitPrice").val();
		var pPurchasePrice = $("#pPurchasePrice").val();
		var isEmpty = false;
		
		if(pName==null||pName==""){
			$("#pName").addClass("inputError");
			isEmpty=true;
			
		}
		
		
		if(bName==null||bName==""){
			$("#bName").addClass("inputError");
			isEmpty=true;
			
		}
		
		if(pUnitPrice==null||pUnitPrice==""){
			$("#pUnitPrice").addClass("inputError");
			isEmpty=true;
			
		}
		
		if(pPurchasePrice==null||pPurchasePrice==""){
			$("#pPurchasePrice").addClass("inputError");
			isEmpty=true;
			
		}
		
		
		if(isEmpty){
			$("#entryMsg").html("Error ! Field/s empty !");
			beep.play();
			$("#entryMsg").show();
		}else{
			$.ajax({
				type: "POST",
				url: "ProductEntry",
				data: $("#entryNewForm").serialize(),
				dataType: "html",
				success: function(response) {
					$("#entryMsg").html(response);
					$("#entryMsg").css('color', 'green');
					$("#entryMsg").show();
					
					if(response!='ok'){
						$("#entryMsg").html("Sorry ! New Product Entry Error !");
						beep.play();
						$("#entryMsg").show();
					}else{
						$("#entryNewForm").hide();
						$("#productN").text('Product Name: '+pName);
						$("#brand").text('Brand Name: '+bName);
						var type = $("#pType").val();
						$("#type").text('Product Type: '+type);
						$("#pPrice").text('Purchase Price: '+pPurchasePrice+'BDT');
						$("#sPrice").text('Sale Price: '+pUnitPrice+'BDT');
						var unit = $("#pUnit").val();
						$("#unit").text('Unit: '+unit);
						$("#entrySuccessful").show();
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					$("#entryMsg").show();
					$("#entryMsg").html(textStatus + " " + errorThrown);
				}
			});
		}
	});  // ENTRY PRODUCT ENDS
	
	
	
});