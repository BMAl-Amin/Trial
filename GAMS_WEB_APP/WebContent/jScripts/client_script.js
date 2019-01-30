$(document).ready(function() {
	$("#clientSuggestDiv").hide();
	$("#viewDiv").hide();
	$("#historyDiv").hide();
	$("#clientPaymentBtn").hide(500);
	var topType = $( "#topTypeSelect option:selected" ).text();
	var topTime = $( "#topTimeSelect option:selected" ).text();
	//Initial show of top 5 clients
	$.get("TopClient",{
		topType: topType,
		topTime: topTime
	}, function(topClients) {
		var parentDiv = $("#clientInfoInnerDiv");
		if(topClients!=null){
			var i=0;
			$.each(topClients, function(key, value) {
				
				var newDiv = $("<div class=\"topClientDiv\"></div>");
				var nameH3 = $("<h3 class=\"clientNameHeader\"></h3>");
				var totalBill = $("<label class=\"clientLabel\"></label");
				var arrear = $("<label class=\"clientLabel\"></label");
				var paidRate = $("<label class=\"clientLabel\"></label");
				var position = $("<label class=\"clientLabel\"></label");
				nameH3.text("("+(i+1)+")"+value['clientName']);
				totalBill.text("Total Bill: "+value['clientTotalBill']+" tk");
				var rateString= value['clientPayRate'];
				//var rate = rateString.substring(0,rateString.indexOf("."));
				paidRate.text("Pay Rate: "+rateString+"%");
				arrear.text("Current Arrear: "+value['clientArrear']+" tk");
				position.text(" Id:"+value['clientId']);
				newDiv.append(nameH3);
				newDiv.append(paidRate);
				newDiv.append(totalBill);
				newDiv.append(arrear);
				newDiv.append(position);
				parentDiv.append(newDiv);
				i++;
			})
		}
	})//Initially Loaded top clients ends
	
	
	
	
	
	
	
	
	
	//SCRIPT FOR CLIENT SEARCH ENGINE
	$(document).on("input","#clientSearchBoxMain",function(e){
		var inputValue = $(this).val();
		var ul = $("#clientSuggestUl");
		ul.empty();
		
		$.get("ClientSearch", {srInput: inputValue}, function(suggestedClient) {
			
			
			 if(suggestedClient!=null){
				$.each(suggestedClient, function(key, value) {
					var li = $("<li class=\"clientSuggestList\"></li>");
					li.text(value['clientId']+":"+value['clientName']);
					ul.append(li);
				})
				$("#clientSuggestDiv").show(500);
			}
			
			
		})
		
	})//ends
	
	
	
	//CLIENT SEARCH ENGINE SUGGEST LIST ON CLICK 
	$(document).on("click", ".clientSuggestList", function() {
			var selectedString = $(this).text();
			$("#clientSearchBoxMain").val(selectedString);
			$("#clientSuggestDiv").hide();
			var id = selectedString.substring(0,selectedString.indexOf(":"));
			var idH3 = $("#cIdH3");
			var nameH3 = $("#cNameH3");
			var phoneH3 = $("#cPhoneH3");
			var addressH3 = $("#cAddressH3");
			var totalBillH3 = $("#cTotalBillH3");
			var payRateH3 = $("#cPayRateH3");
			var arrearH3 = $("#cArrearH3");
			var ul = $("#cHistoryUl");
			$.get("ClientOperation",
					{
						cId:id
					},
					function(clientInfos) {
						
						if(clientInfos!=null){
							ul.empty();
							$.each(clientInfos, function(key, value) {
								idH3.empty();
								idH3.text("Client Id: "+value['clientId']);
								nameH3.empty();
								nameH3.text("Client Name: "+value['clientName']);
								phoneH3.empty();
								phoneH3.text("Phone: "+value['clientPhone']);
								addressH3.empty();
								addressH3.text("Address: "+value['clientAddress']);
								totalBillH3.empty();
								totalBillH3.text("Total Bill: "+value['clientTotalBill']+"tk");
								payRateH3.empty();
								payRateH3.text("Pay Rate: "+value['clientPayRate']+"%");
								arrearH3.empty();
								arrearH3.text("Arrear: "+value['clientArrear']+"tk");
								var li=$("<li class=\"cHistoryLi\"></li>");
								li.text(value['cSaleMonth']+""+value['cSaleDate']+","+value['cSaleYear']+"--Bill: "+value['cSaleCost']+"tk-- Paid"
										+value['cSalePaid']+"tk-- Time: "+value['cSaleTime']);
								ul.append(li);
							})
							
							$("#clientPaymentBtn").show(500);
						}
					});
			
	})//ends
	
	
	 
	 
	
	
	//PAYMENT BUTTON CLICKED
	$(document).on("click", "#clientPaymentBtn", function() {
			var id = $("#cIdH3").text();
			var amount = $("#paymentAnountBox").val();
			if(amount!=null || amount!=""){
				var totalBillH3 = $("#cTotalBillH3");
				var payRateH3 = $("#cPayRateH3");
				var arrearH3 = $("#cArrearH3");
				$.post("PayBill", {
					cId : id,
					amount : amount
				},
				function(response) {
					if(response!=null){
						$.each(response, function(key, value) {
							alert("Payment Status: "+value['payNotice']);
							totalBillH3.empty();
							totalBillH3.text("Total Bill: "+value['clientTotalBill']+"tk");
							payRateH3.empty();
							payRateH3.text("Pay Rate: "+value['clientPayRate']+"%");
							arrearH3.empty();
							arrearH3.text("Arrear: "+value['clientArrear']+"tk");
						})
						$("#paymentAnountBox").val("");
						$("#clientPaymentBtn").hide(500);
					}
				});
			}
	})//ends
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//TOP Client Algorithmic Filtering on Type Select
	$(document).on("change", "#topTypeSelect", function() {
		var topType = $( "#topTypeSelect option:selected" ).text();
		var topTime = $( "#topTimeSelect option:selected" ).text();
		$.get("TopClient",{
			topType: topType,
			topTime: topTime
		}, function(topClients) {
			var parentDiv = $("#clientInfoInnerDiv");
			parentDiv.empty();
			if(topClients!=null){
				var i=0;
			$.each(topClients, function(key, value) {
				var newDiv = $("<div class=\"topClientDiv\"></div>");
				var nameH3 = $("<h3 class=\"clientNameHeader\"></h3>");
				var totalBill = $("<label class=\"clientLabel\"></label");
				var arrear = $("<label class=\"clientLabel\"></label");
				var paidRate = $("<label class=\"clientLabel\"></label");
				var position = $("<label class=\"clientLabel\"></label");
				nameH3.text("("+(i+1)+")"+value['clientName']);
				totalBill.text("Total Bill: "+value['clientTotalBill']+" tk");
				var rateString= value['clientPayRate'];
				position.text(" Id:"+value['clientId']);
				paidRate.text("Pay Rate: "+rateString+"%");
				arrear.text("Current Arrear: "+value['clientArrear']+" tk");
				newDiv.append(nameH3);
				newDiv.append(paidRate);
				newDiv.append(totalBill);
				newDiv.append(arrear);
				newDiv.append(position);
				parentDiv.append(newDiv);
				i++;
				})
			}
		})
	})//Ends
	
	//TOP Client Algorithmic Filtering on Time Select
	$(document).on("change", "#topTimeSelect", function() {
		var topType = $( "#topTypeSelect option:selected" ).text();
		var topTime = $( "#topTimeSelect option:selected" ).text();
		$.get("TopClient",{
			topType: topType,
			topTime: topTime
		}, function(topClients) {
			var parentDiv = $("#clientInfoInnerDiv");
			parentDiv.empty();
			if(topClients!=null){
				var i=0;
			$.each(topClients, function(key, value) {
				var newDiv = $("<div class=\"topClientDiv\"></div>");
				var nameH3 = $("<h3 class=\"clientNameHeader\"></h3>");
				var totalBill = $("<label class=\"clientLabel\"></label");
				var arrear = $("<label class=\"clientLabel\"></label");
				var paidRate = $("<label class=\"clientLabel\"></label");
				var position = $("<label class=\"clientLabel\"></label");
				nameH3.text("("+(i+1)+")"+value['clientName']);
				totalBill.text("Total Bill: "+value['clientTotalBill']+" tk");
				var rateString= value['clientPayRate'];
				position.text("Id:"+value['clientId']);
				paidRate.text("Pay Rate: "+rateString+"%");
				arrear.text("Current Arrear: "+value['clientArrear']+" tk");
				newDiv.append(nameH3);
				newDiv.append(paidRate);
				newDiv.append(totalBill);
				newDiv.append(arrear);
				newDiv.append(position);
				parentDiv.append(newDiv);
				i++;
				})
			}
		})
	})//Ends
	
	
});