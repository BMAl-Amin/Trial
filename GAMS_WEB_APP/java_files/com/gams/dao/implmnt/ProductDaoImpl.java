package com.gams.dao.implmnt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gams.dao.interfaces.ProductDaoInt;
import com.gams.models.Product;
import com.gams.utilities.DBC;

public class ProductDaoImpl implements ProductDaoInt {
	private Connection con;
	PreparedStatement ps;
	private ArrayList<Product> allProduct;
	private Statement st;
	private ResultSet rs;
	
	@Override
	public void saveProduct(Product model) throws SQLException {
		String query = "INSERT INTO product(p_name, p_type, p_brand, p_unit, p_unit_class, p_sale_price, p_purchase_price) VALUES(?,?,?,?,?,?,?)";
		con = DBC.getInstance().getConnection();
		ps = con.prepareStatement(query);
		ps.setString(1, model.getProductName());
		ps.setString(2, model.getProductType());
		ps.setString(3, model.getProductBrand());
		ps.setString(4, model.getProductUnit());
		ps.setString(5, model.getProductUnitType());
		ps.setDouble(6, model.getProductSalePrice());
		ps.setDouble(7, model.getProductPurchasePrice());
		ps.executeUpdate();
		ps.close();
		con.close();
	}

	@Override
	public ArrayList<Product> getAllProducts() throws SQLException {
		
		allProduct = new ArrayList<Product>();
		con=DBC.getInstance().getConnection();
		st=con.createStatement();
		rs=st.executeQuery("SELECT * FROM product");
		while(rs.next()) {
			allProduct.add(new Product(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(4),
						rs.getString(3),
						rs.getString(5),
						rs.getString(6),
						rs.getDouble(7),
						rs.getDouble(8)
					));
		}
		rs.close();
		st.close();
		con.close();
		
		return allProduct;
	}//End of getAllProducts function

	
	//THIS METHOD UPDATES PRODUCT TABLE ON DATABASE
	@Override
	public boolean productUpdated(Product model) {
		boolean updated = false;
		String query = "UPDATE product SET p_name = ?"
				+ ", p_brand = ?"
				+ ", p_unit = ?"
				+ ", p_sale_price = ?"
				+ ", p_purchase_price = ?"
				+ " WHERE p_id = ?";
		
		con = DBC.getInstance().getConnection();
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, model.getProductName());
			ps.setString(2, model.getProductBrand());
			ps.setString(3, model.getProductUnit());
			ps.setDouble(4, model.getProductSalePrice());
			ps.setDouble(5, model.getProductPurchasePrice());
			ps.setInt(6, model.getProductId());
			ps.executeUpdate();
			
			model.setProductNotice("Id : "+model.getProductId()+" is successfully updated on to database.");
			updated = true;
			ps.close();
			con.close();
		} catch (SQLException e) {
			model.setProductNotice("Update failed ! "+e.getMessage());
		}
		
		
		return updated;
	}

	
	
	@Override  //THIS METHOD RETURNS THE LIST OF PRODUCT ID AND NAME WITHIN MODEL
	public ArrayList<Product> getProduct(String typeOfProduct) throws SQLException {
		ArrayList<Product> products = new ArrayList<Product>();
		String query = "SELECT p_id, p_name, p_purchase_price FROM product "
				+ "WHERE p_type = '"+typeOfProduct+"'";
		
		con = DBC.getInstance().getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query);
		
		while(rs.next()) {
			products.add(new Product(
						rs.getInt(1),
						rs.getString(2),
						rs.getDouble(3)
					));
		}
		rs.close();
		st.close();
		con.close();
		return products;
	}
	
	
	
	public double getPriceOf(int id) throws SQLException {
		double price=0;
		String query= "SELECT p_sale_price FROM product WHERE p_id = '"+id+"'";
		con = DBC.getInstance().getConnection();
		st = con.createStatement();
		rs = st.executeQuery(query);
		while(rs.next()) {
			price = rs.getDouble(1);
		}
		rs.close();
		st.close();
		con.close();
		return price;
	}

}
