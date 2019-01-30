package com.gams.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.gams.models.ClientInfo;
import com.gams.services.impl.ClientImpl;
import com.gams.services.interfaces.ClientInterface;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class ClientOperation
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ClientOperation" })
public class ClientOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientOperation() {
        super();
        
    }

   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int clientId =Integer.parseInt(request.getParameter("cId"));
		ClientInterface serv = new ClientImpl();
		ArrayList<ClientInfo> clientInfos=serv.getClients(clientId);
		
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(clientInfos, new TypeToken<List<ClientInfo>> () {}.getType());
		response.setContentType("application/json");
		response.getWriter().print(element.getAsJsonArray());
	}

}
