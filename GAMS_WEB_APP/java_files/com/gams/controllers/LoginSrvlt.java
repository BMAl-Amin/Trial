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
import com.gams.services.impl.AdminImplementation;
import com.gams.services.interfaces.AdminInterfaces;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginSrvlt")
public class LoginSrvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginSrvlt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin model = new Admin();
		AdminInterfaces service = new AdminImplementation();
		
		
		if(request.getParameter("userName")!=null || request.getParameter("password")!=null) {
			model.setUserName(request.getParameter("userName"));
			model.setAdminPassword(request.getParameter("password"));
			AdminDaoInterface dao = new AdminDaoImplementation();
			model.setAdmin(dao.getAdmin());
			if(service.canLog(model)){
				model.setTempAdmin(dao.getAdmin("temp_admin"));
				request.getSession(true).setAttribute("adminInfo", model);
			    request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
			}else {
				model.setNotification("Error: Wrong username or password !");
				request.setAttribute("msg1", model.getNotification());
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			
		}else {
			model.setNotification("Error: Username and/or password is null !");
		}
	}

}
