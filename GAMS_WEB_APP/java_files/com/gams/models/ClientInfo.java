package com.gams.models;

public class ClientInfo extends Client {
	private String cSaleMonth, cSaleTime;
	private int cSaleDate, cSaleYear;
	private double cSaleCost, cSalePaid;
	
	//Constructor
	public ClientInfo(int clientId,String clientName, String clientPhone,String clientAddress, double clientTotalBill, double clientArrear, double clientPaid,
			 double clientPayRate,double cSaleCost,double cSalePaid, String cSaleTime, int cSaleDate, String cSaleMonth, int cSaleYear) {
		
		super(clientId, clientPhone, clientTotalBill, clientArrear, clientPaid, clientName, clientAddress, clientPayRate);
		this.cSaleCost = cSaleCost;
		this.cSalePaid = cSalePaid;
		this.cSaleDate = cSaleDate;
		this.cSaleMonth = cSaleMonth;
		this.cSaleTime = cSaleTime;
		this.cSaleYear = cSaleYear;
		 
	}
	
	
	
	public String getcSaleMonth() {
		return cSaleMonth;
	}
	public String getcSaleTime() {
		return cSaleTime;
	}
	public int getcSaleDate() {
		return cSaleDate;
	}
	public int getcSaleYear() {
		return cSaleYear;
	}
	public double getcSaleCost() {
		return cSaleCost;
	}
	public double getcSalePaid() {
		return cSalePaid;
	}
	
	
}
