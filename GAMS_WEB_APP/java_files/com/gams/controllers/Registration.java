package com.gams.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gams.dao.implmnt.AdminDaoImplementation;
import com.gams.dao.interfaces.AdminDaoInterface;
import com.gams.models.Admin;

/**
 * Servlet implementation class Registration
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Registration" })
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Registration servlet called!");
		
		Admin model = new Admin();
		model.setUserName(request.getParameter("userName"));
		model.setAdminFullName(request.getParameter("fullName"));
		model.setAdminType(request.getParameter("adminType"));
		model.setAdminEmail(request.getParameter("email"));
		model.setAdminPassword(request.getParameter("password"));
		AdminDaoInterface dao = new AdminDaoImplementation();
		dao.saveNewAdmin(model);
		
		String messege ="Yeour Request is pending for approval !";
		request.setAttribute("msg1", messege);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
