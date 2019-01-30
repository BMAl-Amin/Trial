package com.gams.dao.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import com.gams.models.Client;
import com.gams.models.ClientInfo;

public interface ClientDaoInterface {
	public ArrayList<Client> getAllClients();
	public void saveNewClient(Client model) throws SQLException;
	public ArrayList<Client> fetchTopFiveClient(String query);
	public ArrayList<ClientInfo> fetchClientInfos(int id);
}
