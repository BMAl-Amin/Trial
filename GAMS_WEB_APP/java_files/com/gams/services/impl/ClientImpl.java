package com.gams.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.gams.dao.implmnt.ClientDaoImpl;
import com.gams.dao.interfaces.ClientDaoInterface;
import com.gams.models.Client;
import com.gams.models.ClientInfo;
import com.gams.models.ClientPayment;
import com.gams.services.interfaces.ClientInterface;
import com.gams.utilities.ProcessCalender;

public class ClientImpl implements ClientInterface {
	private ArrayList<Client> allClients;
	private ArrayList<Client> suggestedClients;
	private ClientDaoInterface dao, dao2;


	@Override
	public ArrayList<Client> getSuggestedClient(String input) {
		suggestedClients = new ArrayList<>();
		String name, id;
		if(!input.equals("")) {
			dao = new ClientDaoImpl();
			allClients = dao.getAllClients();
			for (Client client : allClients) {
				name = client.getClientName().toLowerCase();
				id = Integer.toString(client.getClientId());
				if(name.contains(input.toLowerCase())||id.equals(input)) {
					suggestedClients.add(client);
				}
			}
			
		}
		
		
		
		
		
		return suggestedClients;
	}


	@Override
	public ArrayList<Client> getTopClients(String topType, String topTime) {
		String query = setQuery(topType, topTime);
		dao = new ClientDaoImpl();
		ArrayList<Client> suggestedClient = new ArrayList<>(dao.fetchTopFiveClient(query));
		return suggestedClient;
	}


	private String setQuery(String topType, String topTime) {
		
		if(topType.equals("By Highest Pay Rate")&&topTime.equals("Today")) {
			return "SELECT * FROM client, client_payment"
					+ " WHERE client.c_id=client_payment.c_id"
					+ " AND client_payment.pay_date="+ProcessCalender.getInstance().getCurDate()
					+ " ORDER BY client.c_pay_rate DESC";
		}
		else if(topType.equals("By Highest Pay Rate")&&topTime.equals("Current Month")) {
			return "SELECT * FROM client, client_payment"
					+ " WHERE client.c_id=client_payment.c_id"
					+ " AND client_payment.pay_month='"+ProcessCalender.getInstance().getCurMonth()+"'"
					+ " ORDER BY client.c_pay_rate DESC";
		}
		else if(topType.equals("By Highest Pay Rate")&&topTime.equals("Current Year")) {
			return "SELECT * FROM client, client_payment"
					+ " WHERE client.c_id=client_payment.c_id"
					+ " AND client_payment.pay_year="+ProcessCalender.getInstance().getCurYear()
					+ " ORDER BY client.c_pay_rate DESC";
		}
		else if(topType.equals("By Lowest Pay Rate")&&topTime.equals("Today")) {
			return "SELECT * FROM client, client_payment"
					+ " WHERE client.c_id=client_payment.c_id"
					+ " AND client_payment.pay_date="+ProcessCalender.getInstance().getCurDate()
					+ " ORDER BY client.c_pay_rate ASC";
		}
		else if(topType.equals("By Lowest Pay Rate")&&topTime.equals("Current Month")) {
			return "SELECT * FROM client, client_payment"
					+ " WHERE client.c_id=client_payment.c_id"
					+ " AND client_payment.pay_month= '"+ProcessCalender.getInstance().getCurMonth()+"'"
					+ " ORDER BY client.c_pay_rate ASC";
		}
		else if(topType.equals("By Lowest Pay Rate")&&topTime.equals("Current Year")) {
			return "SELECT * FROM client, client_payment"
					+ " WHERE client.c_id=client_payment.c_id"
					+ " AND client_payment.pay_year="+ProcessCalender.getInstance().getCurYear()
					+ " ORDER BY client.c_pay_rate ASC";
		}
		else if(topType.equals("By Highest Bill")&&topTime.equals("Today")) {
			return "SELECT * FROM client, client_payment"
					+ " WHERE client.c_id=client_payment.c_id"
					+ " AND client_payment.pay_date="+ProcessCalender.getInstance().getCurDate()
					+ " ORDER BY client.c_total_bill DESC";
		}
		else if(topType.equals("By Highest Bill")&&topTime.equals("Current Month")) {
			return "SELECT * FROM client, client_payment"
					+ " WHERE client.c_id=client_payment.c_id"
					+ " AND client_payment.pay_month='"+ProcessCalender.getInstance().getCurMonth()+"'"
					+ " ORDER BY client.c_total_bill DESC";
		}
		else if(topType.equals("By Highest Bill")&&topTime.equals("Current Year")) {
			return "SELECT * FROM client, client_payment"
					+ " WHERE client.c_id=client_payment.c_id"
					+ " AND client_payment.pay_year="+ProcessCalender.getInstance().getCurYear()
					+ " ORDER BY client.c_total_bill DESC";
		}
		else
		return "Wrong";
	}//ends


	@Override
	public ArrayList<ClientInfo> getClients(int id) {
		dao2 = new ClientDaoImpl();	
		return dao2.fetchClientInfos(id);
	}


	@Override
	public ClientPayment saveClientPayment(int id, double payAmount) {
		ClientPayment payModel = new ClientPayment();
		payModel.setClientId(id);
		payModel.setPayAmount(payAmount);
		payModel.setPayDate(ProcessCalender.getInstance().getCurDate());
		payModel.setPayMonth(ProcessCalender.getInstance().getCurMonth());
		payModel.setPayYear(ProcessCalender.getInstance().getCurYear());
		setAccountStatus(payModel);
		new ClientDaoImpl().paymentTransaction(payModel);
		
		
		return payModel;
	}


	private void setAccountStatus(ClientPayment payModel) {
		ClientDaoImpl dao3 = new ClientDaoImpl();
		double totalBill=0, paidBill=0, payRate, arrear;
		try {
			totalBill = dao3.getClientAccountStatus(payModel.getClientId(), "c_total_bill");
			paidBill = dao3.getClientAccountStatus(payModel.getClientId(), "c_paid");
			paidBill = paidBill+payModel.getPayAmount();
			arrear = totalBill-paidBill;
			payRate = (100*paidBill)/totalBill;
			payModel.setClientTotalBill(totalBill);
			payModel.setClientPaid(paidBill);
			payModel.setClientArrear(arrear);
			payModel.setClientPayRate(payRate);
		} catch (SQLException e) {
			payModel.setPayNotice(e.getMessage());
			e.printStackTrace();
		}
		
	}

}
