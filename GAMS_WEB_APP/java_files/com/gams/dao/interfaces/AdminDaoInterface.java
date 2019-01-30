package com.gams.dao.interfaces;

import java.util.ArrayList;

import com.gams.models.Admin;

public interface AdminDaoInterface {
	public ArrayList<String> getAllUserNames();
	public void saveNewAdmin(Admin model);
	public ArrayList<Admin> getAdmin();
	public ArrayList<Admin> getAdmin(String tableName);
}
