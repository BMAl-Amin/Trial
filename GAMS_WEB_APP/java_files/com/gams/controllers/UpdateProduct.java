package com.gams.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gams.dao.implmnt.ProductDaoImpl;
import com.gams.models.Product;

/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateProduct" })
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product model = new Product();
		int id = Integer.parseInt(request.getParameter("pId"));
		model.setProductId(id);
		model.setProductSalePrice(Double.parseDouble(request.getParameter("sPrice")));
		model.setProductPurchasePrice(Double.parseDouble(request.getParameter("pPrice")));
		model.setProductUnit(request.getParameter("unit"));
		model.setProductName(request.getParameter("pName"));
		model.setProductBrand(request.getParameter("bName"));
		
		String responseText;
		if(new ProductDaoImpl().productUpdated(model)) {
			responseText = model.getProductNotice();
		}else {
			responseText = model.getProductNotice();
		}
		
		response.getWriter().write(responseText);
		
	}

}
