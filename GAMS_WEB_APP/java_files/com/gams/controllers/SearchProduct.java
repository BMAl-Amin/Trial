package com.gams.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gams.dao.implmnt.ProductDaoImpl;
import com.gams.models.Product;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class SearchProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/SearchProduct" })
public class SearchProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String input = request.getParameter("sInput");
		ArrayList<Product> allProduct = null;
		ArrayList<Product> suggestedProduct = new ArrayList<Product>();
		String id, name;
		Gson gson = new Gson();
		JsonElement element = null;
		
		if(input!="") {
			try {
				allProduct = new ProductDaoImpl().getAllProducts();
				
				for (Product product : allProduct) {
					name = product.getProductName().toLowerCase();
					id = Integer.toString(product.getProductId());
					if(name.contains(input.toLowerCase())||id.equals(input)) {
						suggestedProduct.add(product);
					}
				}
				
				
				
				element = gson.toJsonTree(suggestedProduct, new TypeToken<List<Product>>() {}.getType());
				response.setContentType("application/json");
				response.getWriter().print(element.getAsJsonArray());
				
			} catch (SQLException e) {
				System.out.println(" Error In SearchProduct Servlet: "+e.getMessage());
			}
		}
		
		
		
	}

}
