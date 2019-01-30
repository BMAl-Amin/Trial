package com.gams.services.impl;

import com.gams.dao.implmnt.SaleDaoImpl;
import com.gams.dao.interfaces.SaleDaoInt;
import com.gams.models.SaleModel;
import com.gams.services.interfaces.SaleService;

public class SaleServiceImpl implements SaleService {
	private SaleModel model;
	private SaleDaoInt dao;
	private float totalBill, paidBill, arrear;
	
	public SaleServiceImpl() {
		dao = new SaleDaoImpl();
	}
	
	@Override
	public void processProductSale(SaleModel model) {
		this.model = model;
		dao.getClientAccountState(this.model);
		totalBill = this.model.getClientTotalBill()+this.model.getSaleCost();
		paidBill = this.model.getClientPaidBill()+this.model.getSalePaidCost();
		arrear = this.model.getClientArrear()+this.model.getSaleArrear();
		this.model.setClientTotalBill(totalBill);
		this.model.setClientPaidBill(paidBill);
		this.model.setClientArrear(arrear);
		this.model.setClientPayRate(calculateClientPayRate(this.model.getClientTotalBill(), this.model.getClientPaidBill()));
		dao.doSaleInvoiceTransaction(this.model);

	}

	private float calculateClientPayRate(float clientTotalBill, float clientPaidBill) {
		return (100*clientPaidBill)/clientTotalBill;
	}

	

}
