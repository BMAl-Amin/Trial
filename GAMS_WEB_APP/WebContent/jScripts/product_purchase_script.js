$(document).ready(function() {
	
	//SECTION FOR NEW PRODUCT PURCHASE
	$(document).on("click", "#choicePurchaseProduct", function() {
		$("#viewProduct").hide();
		$("#entryNew").hide();
		$("#purchaseProduct").show(500)
		$("#choiceViewProduct").removeClass("activeSideChoice");
		$("#choiceNewEntry").removeClass("activeSideChoice");
		$("#choicePurchaseProduct").addClass("activeSideChoice");
		$("#toggleType").addClass("toggleActive");
		$("#toggleId").removeClass("toggleActive");
		
		$("#searchByType").show();
		$("#namLabel").addClass("toggleLabel");
		$("#searchById").hide();
		
	});
	
	
	
	
	$("#toggleId").click(function() {
		$("#namLabel").removeClass("toggleLabel");
		$("#idLabel").addClass("toggleLabel");
		$("#toggleType").removeClass("toggleActive");
		$("#toggleId").addClass("toggleActive");
		$("#searchByType").fadeOut(500);
		$("#searchById").show(500);
		$("#expandableBox").hide();
		
		
	});
	
	$("#toggleType").click(function() {
		$("#namLabel").addClass("toggleLabel");
		$("#idLabel").removeClass("toggleLabel");
		$("#toggleId").removeClass("toggleActive");
		$("#toggleType").addClass("toggleActive");
		$("#searchById").hide(500);
		$("#searchByType").show(500);
	});
	
	
	$("#prType").change(function() {
		optionSelected = $(this).find("option:selected");
		var type = optionSelected.val();
		var select = $("#prName");
		select.empty();
	    if(type!=null || type!=""){
	    	$.get('GetProductByType',{productType:type}, function(responseJson) {
				if(responseJson!=null){
					$.each(responseJson, function(key, value) {
						var option = $("<option></option>");
						option.text(value['productId']+": "+value['productName']);
						option.appendTo(select);
					})
				}
			})
	    }
	});
	
	
	
	//PRODUCT SEARCH ENGINE
	$(document).on("input","#searchId",function(e){
		var inputValue = $(this).val();
		$("#expandableBox").empty();
		//AJAX TO FETCH PRODUCTS FROM SERVER
		$.get("SearchProduct",{sInput : inputValue}, function(suggestedProduct) {
			
			var size = 0;
			if(suggestedProduct!=null){
				$.each(suggestedProduct, function(key, value) {
					size = size+1;
					var option = $("<option class=\"suggestionOption\"></option>");
					option.text(value['productId']+": "+value['productName']);
					option.appendTo($("#expandableBox"));
				})//traversing over the model object is done
				$("#expandableBox").show(1000);
				$("#expandableBox").attr("size", 10);
			}
		});
	});
	
	
	//SHOWING EXPANDABLE SUGGESTION BOX
	$(document).on("dblclick", "#expandableBox", function(e) {
		var valuIs = $(this).val();
		 $("#searchId").val(valuIs);
	     $("#expandableBox").hide();
	 });
	
	
	
	// 
	
	$("#searchTypeBtn").click(function() {
		
		$("#costLabel").empty();
		var selected = $("#prName").val();
		var invoiceId = selected.substring(0, selected.indexOf(":"));
		var prodName = selected.substring(selected.indexOf(" "));
		var amount = $("#purchaseAmountByType").val();
		var cost;
		
		$.get("ProductPrice",{id:invoiceId}, function(response) {
			if(response!=null){
				cost = response*amount; 
				var table = $("#invoiceTable");
				var newRow = $("<tr class=\"tdParentRow\"><td></td><td></td><td></td><td class=\"invoicePPrice\"></td></tr>");
				newRow.children().eq(0).text(invoiceId);
				newRow.children().eq(1).text(prodName);
				newRow.children().eq(2).text(amount);
				newRow.children().eq(3).text(cost);
				newRow.appendTo(table);
				}
		});
	});
});