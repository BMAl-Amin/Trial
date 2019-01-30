package com.gams.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gams.models.Client;
import com.gams.services.impl.ClientImpl;
import com.gams.services.interfaces.ClientInterface;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class TopClient
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/TopClient" })
public class TopClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ClientInterface serv = new ClientImpl();
		String topType = request.getParameter("topType");
		String topTime = request.getParameter("topTime");
		ArrayList<Client> topClients = serv.getTopClients(topType, topTime);
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(topClients, new TypeToken<List<Client>> () {}.getType());
		response.setContentType("application/json");
		response.getWriter().print(element.getAsJsonArray());
	}

}
