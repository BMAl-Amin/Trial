$(document).ready(function() {
	$("#sellSearchExpandDiv").hide();
	$("#sellSearchExpandDiv2").hide();
	var totalCost=0;
	var clientId;
	var productIds="";
	var productAmounts="";
	
	$(document).on("click", "#sellNowLeftBtn", function() {
		$("#saleHistory").hide();
		$("#sellNow").show(500);
		$("#sellHistoryLeftBtn").removeClass("activeSideChoice");
		$("#sellNowLeftBtn").addClass("activeSideChoice");
	})
	
	$(document).on("click", "#sellHistoryLeftBtn", function() {
		$("#saleHistory").show();
		$("#sellNow").hide();
		$("#sellHistoryLeftBtn").addClass("activeSideChoice");
		$("#sellNowLeftBtn").removeClass("activeSideChoice");
	})
	
	
	
	//SCRIPT FOR CLIENT SEARCH ENGINE
	$(document).on("input","#searchClientBox",function(e){
		var inputValue = $(this).val();
		var ul = $("#sellClientSuggestUl");
		ul.empty();
		
		$.get("ClientSearch", {srInput: inputValue}, function(suggestedClient) {
			
			
			 if(suggestedClient!=null){
				$.each(suggestedClient, function(key, value) {
					var li = $("<li class=\"sellClientSuggestList\"></li>");
					li.text(value['clientId']+":"+value['clientName']);
					ul.append(li);
				})
				$("#sellSearchExpandDiv").show(500);
			}
			
			
		})
		
	})//ends
	
	
	
	//CLIENT SEARCH ENGINE SUGGEST LIST ON CLICK 
	$(document).on("click", ".sellClientSuggestList", function() {
			$("#clientH3").empty();
			$("#searchClientBox").val($(this).text());
			$("#sellSearchExpandDiv").hide();
			$("#clientH3").text("Client: "+$(this).text().substring($(this).text().indexOf(":")+1));
			clientId = $(this).text().substring(0,$(this).text().indexOf(":"));
			$("#clientIdh4").text("ID: "+clientId);
	})//ends
	

	
	
	//ITEM SEARCH ENGINE
	$(document).on("input","#searchItemBox",function(e){
		var ul = $("#sellItemSuggestUl");
		ul.empty();
		var inputValue = $(this).val();
		$.get("SearchProduct",{sInput : inputValue}, function(suggestedProduct) {
			if(suggestedProduct!=null){
				$.each(suggestedProduct, function(key, value) {
					var li=$("<li class=\"sellItemSuggestList\"></li>");
					li.text(value['productId']+": "+value['productName']);
					ul.append(li);
				})//traversing over the model object is done
				$("#sellSearchExpandDiv2").show(500);
			}
		});
	})//ends
	
	
	
	
	//ITEM SEARCH SUGGEST LIST ON CLICK
	$(document).on("click", ".sellItemSuggestList", function() {
		
			$("#searchItemBox").val($(this).text());
			$("#sellSearchExpandDiv2").hide();
	})//ends
	
	
	
	
	//ADDING SELECTED ITEM TO INVOICE
	$(document).on("click", "#addSellItemBtn", function() {
		var nameString = $("#searchItemBox").val();
		var amount = $("#sellAmountBox").val();
		if(amount==null || amount==""){
			$("#sellAmountBox").focus();
		}else if(nameString==null || nameString==""){
			$("#searchItemBox").focus();
		}else{
			
			var itemId = nameString.substring(0,nameString.indexOf(":"));
			var itemName = nameString.substring(nameString.indexOf(" "));
			var table = $("#sellBodyTbl");
			var rowTd = $("<tr><td class=\"idInvoieTd\"></td><td class=\"nameInvoiceTd\"></td><td class=\"amountInvoiceTd\"></td><td class=\"priceInvoiceTd\"></td></tr>");
			var itemCost;
			
			if(productIds.length<1){
				productIds=itemId;
			}else{
				productIds = productIds+","+itemId;
			}
			
			if(productAmounts.length<1){
				productAmounts=amount;
			}else{
				productAmounts = productAmounts+","+amount;
			}
			
			
			$.get("ProductPrice",{id:itemId} , function(response) {
				itemCost = response*amount;
				rowTd.children().eq(0).text(itemId);
				rowTd.children().eq(0).css("min-width", "50px");
				rowTd.children().eq(1).text(itemName);
				rowTd.children().eq(1).css("min-width", "300px");
				rowTd.children().eq(2).text(amount);  
				rowTd.children().eq(2).css("min-width", "80px");
				rowTd.children().eq(3).text(itemCost);
				rowTd.children().eq(3).css("min-width", "100px");
				table.append(rowTd);
				totalCost = totalCost+itemCost;
				$("#totalSellCost").empty();
				$("#totalSellCost").text("Total Cost: "+totalCost);
				
			})
			
			$("#searchItemBox").val("");
			$("#sellAmountBox").val("1");
			
		}
		
	})//ends
	
	
	
	//SELL NOW BUTTON CLICKED 
	$(document).on("click", "#sellBtn", function() {
		var paidAmount = $("#sellPaymentBox").val();
		if(paidAmount==null||paidAmount==''){
			paidAmount = 0.0;
		}
		
		if(totalCost>0 && clientId!=null){
			$.post("SellNow",
					{
						clientId:clientId,
						totalCost:totalCost,
						pIds:productIds,
						pAmounts:productAmounts,
						paidAmount:paidAmount
					}, 
					function(response) {
						$("#sellBodyTbl").empty();
						$("#sellPaymentBox").val("0");
						totalCost=0;
						$("#totalSellCost").empty();
						$("#totalSellCost").text("Total Cost: "+totalCost);
						productIds = "";
						productAmounts = "";
						alert("Sale Service Status: "+response);
						
			})
		}else{
			alert("Fill Up Invoice Correctly and then press Sell Now!");
		}
		
		
		
		
		
		
	})//ENDS
	
	
})