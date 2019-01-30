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

/**
 * Servlet implementation class GetProductByType
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/GetProductByType" })
public class GetProductByType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProductByType() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pType = request.getParameter("productType");
		System.out.println("Servlet is called for: "+pType);
		Gson gson = new Gson();
		JsonElement element;
		JsonArray jsnArr=null;
		try {
			ArrayList<Product> allProducts = new ProductDaoImpl().getProduct(pType);
			
			element = gson.toJsonTree(
					allProducts,
					new TypeToken<List<Product>>() {}.getType()
				);
			
			jsnArr = element.getAsJsonArray();
			
			
		} catch (SQLException e) {
			System.out.println("Error in servlet: "+e.getMessage());
		}
		
		response.setContentType("application/json");
		response.getWriter().print(jsnArr);
	}

}
