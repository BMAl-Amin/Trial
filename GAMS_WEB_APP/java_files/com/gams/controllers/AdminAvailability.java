package com.gams.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.gams.models.Admin;
import com.gams.services.impl.AdminImplementation;
import com.gams.services.interfaces.AdminInterfaces;

/**
 * Servlet implementation class AdminAvailability
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AdminAvailability" })
public class AdminAvailability extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Admin model; 
    AdminInterfaces service;
    //CONSTRUCTOR
    public AdminAvailability() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("userName");
		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    
		
		model = new Admin();
		model.setUserName(username);
		service = new AdminImplementation();
		service.checkAdminAvailability(model);
		
		if(model.isAdminAvailable()) {
			System.out.println("Username is Available !");
			response.getWriter().write("Username is available !");
		}else {
			
		}
	}

}
