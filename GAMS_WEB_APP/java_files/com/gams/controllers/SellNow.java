package com.gams.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gams.models.SaleModel;
import com.gams.services.impl.SaleServiceImpl;
import com.gams.services.interfaces.SaleService;

/**
 * Servlet implementation class SellNow
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/SellNow" })
public class SellNow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellNow() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		float paidAmount = Float.parseFloat(request.getParameter("paidAmount"));
		float totalCost = Float.parseFloat(request.getParameter("totalCost"));
		float arrear = totalCost-paidAmount;
		String pIds = request.getParameter("pIds");
		String pAmounts = request.getParameter("pAmounts");
		
		
		SaleModel model = new SaleModel();
		model.setClientId(Integer.parseInt(request.getParameter("clientId")));
		model.setSaleCost(totalCost);
		model.setSalePaidCost(paidAmount);
		model.setSaleArrear(arrear);
		model.setpIds(getIntList(pIds));
		model.setpAmounts(getIntList(pAmounts));
		
		SaleService serv = new SaleServiceImpl();
		serv.processProductSale(model);
		
		response.setContentType("text/html");
		response.getWriter().write(model.getSaleNotice());
		
	}
	
	
	
	
	
	
	//Converting string to list
	
	private ArrayList<Integer> getIntList(String s){
		
		ArrayList<Integer> list = new ArrayList<>();
		for(String part: s.split(",")) {
			list.add(Integer.parseInt(part));
		}
		
		return list;
	}
	
	
	
	
	
}
