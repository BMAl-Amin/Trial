package com.gams.models;

public class ClientPayment extends Client {
	private double payAmount;
	private int payDate, payYear;
	private String payMonth, payNotice;
	
	public String getPayNotice() {
		return payNotice;
	}
	
	public void setPayNotice(String payNotice) {
		this.payNotice = payNotice;
	}
	
	public double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}
	public int getPayDate() {
		return payDate;
	}
	public void setPayDate(int payDate) {
		this.payDate = payDate;
	}
	public int getPayYear() {
		return payYear;
	}
	public void setPayYear(int payYear) {
		this.payYear = payYear;
	}
	public String getPayMonth() {
		return payMonth;
	}
	public void setPayMonth(String payMonth) {
		this.payMonth = payMonth;
	}
	
	
}
