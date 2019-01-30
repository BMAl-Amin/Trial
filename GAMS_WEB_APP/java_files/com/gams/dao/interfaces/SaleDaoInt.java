package com.gams.dao.interfaces;

import com.gams.models.SaleModel;

public interface SaleDaoInt {
	public void doSaleInvoiceTransaction(SaleModel model);
	public SaleModel getClientAccountState(SaleModel model);
}
