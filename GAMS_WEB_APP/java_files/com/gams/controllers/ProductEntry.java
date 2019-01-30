package com.gams.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gams.dao.implmnt.ProductDaoImpl;
import com.gams.dao.interfaces.ProductDaoInt;
import com.gams.models.Product;


@WebServlet(asyncSupported = true, urlPatterns = { "/ProductEntry" })
public class ProductEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ProductEntry() {
        super();
    }

	
    //DO POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product model = new Product();
		String saveNotice;
		model.setProductName(request.getParameter("productName"));
		model.setProductBrand(request.getParameter("brandName"));
		model.setProductType(request.getParameter("productType"));
		model.setProductPurchasePrice(Double.parseDouble(request.getParameter("productPurchasePrice")));
		model.setProductUnitType(request.getParameter("productUnitType"));
		model.setProductUnit(request.getParameter("productUnit"));
		model.setProductSalePrice(Double.parseDouble(request.getParameter("productSalePrice")));
		
		ProductDaoInt dao = new ProductDaoImpl();
		try {
			dao.saveProduct(model);
			saveNotice = "ok";
		} catch (SQLException e) {
			saveNotice = e.getMessage();
			e.printStackTrace();
		}
		
		response.getWriter().write(saveNotice);
	}

}
