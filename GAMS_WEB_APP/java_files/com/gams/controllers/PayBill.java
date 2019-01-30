package com.gams.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gams.models.ClientPayment;
import com.gams.services.impl.ClientImpl;
import com.gams.services.interfaces.ClientInterface;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;


@WebServlet(asyncSupported = true, urlPatterns = { "/PayBill" })
public class PayBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PayBill() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idText = request.getParameter("cId");
		idText = idText.substring(idText.lastIndexOf(" ")+1);
		int id = Integer.parseInt(idText);
		double payAmount = Double.parseDouble(request.getParameter("amount"));
		ClientInterface serv = new ClientImpl();
		ClientPayment pModel = serv.saveClientPayment(id, payAmount);
		ArrayList<ClientPayment> model = new ArrayList<>();
		model.add(pModel);
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(model, new TypeToken<List<ClientPayment>> () {}.getType());
		response.setContentType("application/json");
		response.getWriter().print(element.getAsJsonArray());
	}

}
