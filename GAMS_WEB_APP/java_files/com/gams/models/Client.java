package com.gams.models;

public class Client {
	private int clientId;
	private double clientTotalBill, clientArrear, clientPaid, clientPayRate;
	private String clientName, clientAddress, clientPhone;
	
	
	
	
	
	public Client(int clientId, String clientPhone, double clientTotalBill, double clientArrear, double clientPaid,
			String clientName, String clientAddress, double clientPayRate) {
		this.clientId = clientId;
		this.clientPhone = clientPhone;
		this.clientTotalBill = clientTotalBill;
		this.clientArrear = clientArrear;
		this.clientPaid = clientPaid;
		this.clientName = clientName;
		this.clientAddress = clientAddress;
		this.clientPayRate = clientPayRate;
	}
	
	
	public Client() {
		
	}
	
	public void setClientPayRate(double clientPayRate) {
		this.clientPayRate = clientPayRate;
	}
	
	public double getClientPayRate() {
		return clientPayRate;
	}
	
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getClientPhone() {
		return clientPhone;
	}
	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}
	public double getClientTotalBill() {
		return clientTotalBill;
	}
	public void setClientTotalBill(double clientTotalBill) {
		this.clientTotalBill = clientTotalBill;
	}
	public double getClientArrear() {
		return clientArrear;
	}
	public void setClientArrear(double clientArrear) {
		this.clientArrear = clientArrear;
	}
	public double getClientPaid() {
		return clientPaid;
	}
	public void setClientPaid(double clientPaid) {
		this.clientPaid = clientPaid;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	
	
}
