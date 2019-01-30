package com.gams.services.interfaces;

import java.util.ArrayList;

import com.gams.models.Client;
import com.gams.models.ClientInfo;
import com.gams.models.ClientPayment;

public interface ClientInterface {
	public ArrayList<Client> getSuggestedClient(String input);
	public ArrayList<Client> getTopClients(String topType, String topTime);
	public ArrayList<ClientInfo> getClients(int id);
	public ClientPayment saveClientPayment(int id, double payAmount);
}
