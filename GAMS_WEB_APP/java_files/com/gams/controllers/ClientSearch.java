package com.gams.controllers;

import java.io.IOException;
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
 * Servlet implementation class ClientSearch
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ClientSearch" })
public class ClientSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String input = request.getParameter("srInput");
		ClientInterface serv = new ClientImpl();
		Gson gson = new Gson();
		JsonElement element = null;
		//ArrayList<Client> suggestedProduct = 
		element = gson.toJsonTree(serv.getSuggestedClient(input), new TypeToken<List<Client>>() {}.getType());
		response.setContentType("application/json");
		response.getWriter().print(element.getAsJsonArray());
	}

}
