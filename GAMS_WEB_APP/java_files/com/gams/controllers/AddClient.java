package com.gams.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gams.dao.implmnt.ClientDaoImpl;
import com.gams.dao.interfaces.ClientDaoInterface;
import com.gams.models.Client;

/**
 * Servlet implementation class AddClient
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddClient" })
public class AddClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClient() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client model = new Client();
		
		String text;
		
		model.setClientName(request.getParameter("name"));
		model.setClientPhone(request.getParameter("phone"));
		model.setClientAddress(request.getParameter("address"));
		
		ClientDaoInterface dao = new ClientDaoImpl();
		try {
			dao.saveNewClient(model);
			text = "Successful !";
		} catch (SQLException e) {
			text = "Error !"+e.getMessage();
			e.printStackTrace();
		}
		
		response.setContentType("text/html");
		response.getWriter().write(text);
	}

}
