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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

@WebServlet(asyncSupported = true, urlPatterns = { "/ShowProduct" })
public class ShowProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ShowProduct() {
        super();
    }

	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product model = new Product();
		try {
			model.setProduct(new ProductDaoImpl().getAllProducts());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Product> allProducts = model.getProduct();
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(
								allProducts,
								new TypeToken<List<Product>>() {}.getType()
							);
		JsonArray jsnArr = element.getAsJsonArray();
		response.setContentType("application/json");
		response.getWriter().print(jsnArr);
	}

}
