package com.gams.models;

import java.util.ArrayList;

public class Admin {
	private String userName, adminFullName, adminType, adminEmail, adminPassword, notification;
	boolean adminAvailable;
	private ArrayList<Admin> admin;
	private ArrayList<Admin> tempAdmin;
	public Admin(String ...adminInfo ) {
		try {
			this.userName = adminInfo[0];
			this.adminFullName = adminInfo[1];
			this.adminType = adminInfo[2];
			this.adminEmail = adminInfo[3];
			this.adminPassword = adminInfo[4];
		}catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Incomplete admin info in MOdel :"+e.getMessage());
		}
	}
	
	public Admin() {
		
	}
	
	
	
	public ArrayList<Admin> getTempAdmin() {
		return tempAdmin;
	}

	public void setTempAdmin(ArrayList<Admin> tempAdmin) {
		this.tempAdmin = tempAdmin;
	}

	public ArrayList<Admin> getAdmin() {
		return admin;
	}

	public void setAdmin(ArrayList<Admin> admin) {
		this.admin = admin;
	}

	public String getNotification() {
		return notification;
	}
	
	public void setNotification(String notification) {
		this.notification = notification;
	}

	public boolean isAdminAvailable() {
		return adminAvailable;
	}



	public void setAdminAvailable(boolean adminAvailable) {
		this.adminAvailable = adminAvailable;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public void setAdminFullName(String adminFullName) {
		this.adminFullName = adminFullName;
	}



	public void setAdminType(String adminType) {
		this.adminType = adminType;
	}



	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}



	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}



	public String getUserName() {
		return userName;
	}

	public String getAdminFullName() {
		return adminFullName;
	}

	public String getAdminType() {
		return adminType;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}
	
	
}
