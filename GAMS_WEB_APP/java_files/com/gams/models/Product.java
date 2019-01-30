package com.gams.models;

import java.util.ArrayList;

public class Product {
	private String productName, productBrand, productType, productUnit, productUnitType, productNotice;
	private double productSalePrice, productPurchasePrice;
	private int productId;
	private ArrayList<Product> product;
	
	
	public Product(int productId, String productName, String productBrand, String productType, String productUnit,
			String productUnitType, double productSalePrice, double productPurchasePrice) {
		this.productId = productId;
		this.productName = productName;
		this.productBrand = productBrand;
		this.productType = productType;
		this.productUnit = productUnit;
		this.productUnitType = productUnitType;
		this.productSalePrice = productSalePrice;
		this.productPurchasePrice = productPurchasePrice;
	}
	
	public Product() {
		
	}
	
	public Product(int productId, String productName, double productPurchasePrice) {
		this.productId = productId;
		this.productName = productName;
		this.productPurchasePrice = productPurchasePrice;
	}
	
	
	public String getProductNotice() {
		return productNotice;
	}
	
	public void setProductNotice(String productNotice) {
		this.productNotice = productNotice;
	}
	
	
	
	
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	public String getProductUnitType() {
		return productUnitType;
	}

	public void setProductUnitType(String productUnitType) {
		this.productUnitType = productUnitType;
	}

	public double getProductSalePrice() {
		return productSalePrice;
	}

	public void setProductSalePrice(double productSalePrice) {
		this.productSalePrice = productSalePrice;
	}

	public double getProductPurchasePrice() {
		return productPurchasePrice;
	}

	public void setProductPurchasePrice(double productPurchasePrice) {
		this.productPurchasePrice = productPurchasePrice;
	}

	public ArrayList<Product> getProduct() {
		return product;
	}

	public void setProduct(ArrayList<Product> product) {
		this.product = product;
	}
	
	
	
	
}
