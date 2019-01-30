$(document).ready(function(){
	var val;
	$(document).on("dblclick","#productTable tr",function() {
		$("#editDiv").fadeOut(500);
		var nameCell = $(this).children().eq(1);
		var brandCell = $(this).children().eq(2);
		var sPriceCell = $(this).children().eq(3);
		var pPriceCell = $(this).children().eq(4);
		var unitCell = $(this).children().eq(5);
		var id = $(this).children().eq(0).text();
		var name = nameCell.text();
		var brand = brandCell.text();
		var sPrice = sPriceCell.text();
		var pPrice = pPriceCell.text();
		var unit = unitCell.text();
		
		nameCell.html("<input type='text' value='"+name+"' class='editCell'>");
		nameCell.children().first().focus();
		brandCell.html("<input type='text' value='"+brand+"' class='editCell'>");
		brandCell.children().first().focus();
		sPriceCell.html("<input type='text' value='"+sPrice+"' class='editCell'>");
		sPriceCell.children().first().focus();
		pPriceCell.html("<input type='text' value='"+pPrice+"' class='editCell'>");
		pPriceCell.children().first().focus();
		unitCell.html("<select class='editCell'>" +
				"<option>"+unit+"</option>" +
				"<option>gram</option>" +
				"<option>kg</option>" +
				"<option>ml</option>" +
				"<option>liter</option>" +
				"<option>pcs</option>" +
				"</select>");
		
		$(this).children().last().children().first().click(function() {
			
				name = nameCell.children().first().val();
				brand = brandCell.children().first().val();
				sPrice = sPriceCell.children().first().val();
				pPrice = pPriceCell.children().first().val();
				unit = unitCell.children().first().val();
				
				//CALL AJAX HERE// TO DO
				$.post(
					"UpdateProduct",
					{
						pId : id,
						pName : name,
						bName : brand,
						sPrice: sPrice,
						pPrice: pPrice,
						unit: unit
					},
					
					function(response) {
						$("#noticeDiv").empty();
						$("#noticeDiv").text(response);
					}
				);
				
				nameCell.text(name);
				brandCell.text(brand);
				sPriceCell.text(sPrice);
				pPriceCell.text(pPrice);
				unitCell.text(unit);
				
				
				$("#editDiv").fadeIn(1000);
			
		})
		
		
	});
	
	 
	
	
	
});//SCRIPT ENDS