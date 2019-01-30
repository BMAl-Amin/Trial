package com.gams.dao.implmnt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gams.dao.interfaces.AdminDaoInterface;
import com.gams.models.Admin;
import com.gams.utilities.DBC;

public class AdminDaoImplementation implements AdminDaoInterface {
	private ArrayList<String> userNames;
	private Connection con;
	private ArrayList<Admin> admin;
	private ArrayList<Admin> tempAdmin;
	
	
	//CONSTRUCTOR
	public AdminDaoImplementation() {
		userNames = new ArrayList<String>();
		admin = new ArrayList<Admin>();
		
	}
	
	
	
	
	@Override
	public ArrayList<String> getAllUserNames() {
		String query = "SELECT username FROM admin";
		Statement st=null;
		ResultSet rs = null;
		con = DBC.getInstance().getConnection();
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				userNames.add(rs.getString(1));
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("In Admin Dao :");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return userNames;
	}




	@Override
	public void saveNewAdmin(Admin model) {
		con = DBC.getInstance().getConnection();
		String query = "INSERT INTO temp_admin(username, admin_full_name, email, password, admin_type) VALUES(?,?,?,?,?)";
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, model.getUserName());
			ps.setString(2, model.getAdminFullName());
			ps.setString(3, model.getAdminEmail());
			ps.setString(4, model.getAdminPassword());
			ps.setString(5, model.getAdminType());
			ps.executeUpdate();
			
			ps.close();con.close();
		} catch (SQLException e) {
			System.out.println("Error in Admin Dao, while saving new admin: ");
			e.printStackTrace();
		}
		
	}




	@Override
	public ArrayList<Admin> getAdmin() {
		String query = "SELECT * FROM admin";
		Statement st=null;
		ResultSet rs = null;
		con = DBC.getInstance().getConnection();
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				admin.add(new Admin(
							rs.getString(2),
							rs.getString(3),
							rs.getString(6),
							rs.getString(4),
							rs.getString(5)
						));
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("In dao, error :"+e.getMessage());
		}
		
		return admin;
	}




	@Override
	public ArrayList<Admin> getAdmin(String tableName) {
		String query = "SELECT * FROM "+tableName+"";
		Statement st=null;
		ResultSet rs = null;
		con = DBC.getInstance().getConnection();
		tempAdmin = new ArrayList<Admin>();
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				tempAdmin.add(new Admin(
							rs.getString(1),
							rs.getString(2),
							rs.getString(5),
							rs.getString(3),
							rs.getString(4)
						));
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("In dao, error :"+e.getMessage());
		}
		
		return tempAdmin;
	}

}
