package com.gams.models;

import java.util.ArrayList;

public class SaleModel {
	private int saleId, clientId;
	private float saleCost, salePaidCost, saleArrear, clientTotalBill, clientPaidBill, clientArrear, clientPayRate;
	private ArrayList<Integer> pIds, pAmounts;
	private String saleNotice;
	
	public void setSaleNotice(String saleNotice) {
		this.saleNotice = saleNotice;
	}
	
	public void setClientPayRate(float clientPayRate) {
		this.clientPayRate = clientPayRate;
	}
	
	public float getClientPayRate() {
		return clientPayRate;
	}
	
	public String getSaleNotice() {
		return saleNotice;
	}
	
	
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public float getSaleCost() {
		return saleCost;
	}
	public void setSaleCost(float saleCost) {
		this.saleCost = saleCost;
	}
	public float getSalePaidCost() {
		return salePaidCost;
	}
	public void setSalePaidCost(float salePaidCost) {
		this.salePaidCost = salePaidCost;
	}
	public float getSaleArrear() {
		return saleArrear;
	}
	public void setSaleArrear(float saleArrear) {
		this.saleArrear = saleArrear;
	}
	public float getClientTotalBill() {
		return clientTotalBill;
	}
	public void setClientTotalBill(float clientTotalBill) {
		this.clientTotalBill = clientTotalBill;
	}
	public float getClientPaidBill() {
		return clientPaidBill;
	}
	public void setClientPaidBill(float clientPaidBill) {
		this.clientPaidBill = clientPaidBill;
	}
	public float getClientArrear() {
		return clientArrear;
	}
	public void setClientArrear(float clientArrear) {
		this.clientArrear = clientArrear;
	}
	public ArrayList<Integer> getpIds() {
		return pIds;
	}
	public void setpIds(ArrayList<Integer> pIds) {
		this.pIds = pIds;
	}
	public ArrayList<Integer> getpAmounts() {
		return pAmounts;
	}
	public void setpAmounts(ArrayList<Integer> pAmounts) {
		this.pAmounts = pAmounts;
	}
	
	
}
