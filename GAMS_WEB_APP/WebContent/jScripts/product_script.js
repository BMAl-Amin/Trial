$(document).ready(function(){
	$("#entryMsg").hide();
	$("#entrySuccessful").hide();
	
	var beep = new Audio('audio/beep.mp3');
	var optionSelected;
	
	
	
	//LEFT SIDED VIEW PRODUCT BUTTON CLICKED 
	$(document).on("click", "#choiceViewProduct", function() {
		
		$("#entryNew").hide(500);
		$("#purchaseProduct").hide(500);
		$("#viewProduct").show(500);
		$("#choiceViewProduct").addClass("activeSideChoice");
		$("#choiceNewEntry").removeClass("activeSideChoice");
		$("#choicePurchaseProduct").removeClass("activeSideChoice");
		
		//AJAX CALL TO SERVLET FOR GETTING product DATA FROM DATABASE TABLE
		$.get('ShowProduct', function(responseJson) {
			if(responseJson!=null){
				$("#productTable").find("tr:gt(0)").remove();
				var table=$("#productTable");
				var tHead=$("#tHead");
				var tBody=$("tBody");
				var i=0;
				$.each(responseJson, function(key, value) {
					var row = $("<tr><td class=\"smallTd\"></td><td class=\"bigTd\"></td><td class=\"bigTd\"></td><td class=\"smallTd\"></td><td class=\"smallTd\"></td><td class=\"smallTd\"></td><td class=\"smallTd\"></td></tr>");
					row.children().eq(0).text(value['productId']);
					row.children().eq(1).text(value['productName']);
					row.children().eq(2).text(value['productBrand']);
					row.children().eq(3).text(value['productSalePrice']);
					row.children().eq(4).text(value['productPurchasePrice']);
					row.children().eq(5).text(value['productUnit']);
					row.children().eq(6).html("<input type='button' value='ok' class='editBt'>");
					row.appendTo(tBody);
					
				});//Loop ends
			}
		});
	});
	
	
	
	
	
	
	
	
	//Left side menu option button clicked
	$("#choiceNewEntry").click(function() {
		$("#entrySuccessful").hide();
		$("#purchaseProduct").hide(500);
		$("#viewProduct").hide(500);
		$("#entrySuccessful").hide(500);
		$("#entryNewForm").show(500);
		$("#entryNew").show(500);
		
		$("#choiceViewProduct").removeClass("activeSideChoice");
		$("#choicePurchaseProduct").removeClass("activeSideChoice");
		$("#choiceNewEntry").addClass("activeSideChoice");
		
	});
	
	
	
	
	
	
	
	
	
	
	
});