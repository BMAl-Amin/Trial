package com.gams.dao.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import com.gams.models.Product;

public interface ProductDaoInt {
	public void saveProduct(Product model) throws SQLException;
	public ArrayList<Product> getAllProducts() throws SQLException;
	public ArrayList<Product> getProduct(String typeOfProduct) throws SQLException;
	public boolean productUpdated(Product model);
}
