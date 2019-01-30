package com.gams.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gams.dao.implmnt.ProductDaoImpl;

/**
 * Servlet implementation class ProductPrice
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ProductPrice" })
public class ProductPrice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductPrice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String resp =Double.toString(new ProductDaoImpl().getPriceOf(id));
			response.setContentType("text/html");
			response.getWriter().write(resp);
		} catch (SQLException e) {
			System.out.println("In product price fetching servlet: "+e.getMessage());
		}
	}

}
