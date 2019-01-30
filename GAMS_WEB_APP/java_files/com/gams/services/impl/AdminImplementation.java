package com.gams.services.impl;
import java.util.ArrayList;

import com.gams.dao.implmnt.AdminDaoImplementation;
import com.gams.dao.interfaces.AdminDaoInterface;
import com.gams.models.Admin;
import com.gams.services.interfaces.AdminInterfaces;

public class AdminImplementation implements AdminInterfaces {
	private ArrayList<String> userNames;
	private AdminDaoInterface daoModel;
	
	
	@Override
	public void checkAdminAvailability(Admin model) {
		userNames = new AdminDaoImplementation().getAllUserNames();
		model.setAdminAvailable(false);
		for (String currentName : userNames) {
			if(currentName.equals(model.getUserName())) {
				model.setAdminAvailable(true);
				break;
			}
		}
	}



	@Override
	public boolean canLog(Admin model) {
		boolean can = false;
		daoModel = new AdminDaoImplementation();
		for (Admin admin : daoModel.getAdmin()) {
			if(admin.getUserName().equals(model.getUserName())) {
				//Login condition
				if(admin.getAdminPassword().equals(model.getAdminPassword())) {
					can = true;
					model.setAdminFullName(admin.getAdminFullName());
					model.setAdminType(admin.getAdminType());
					model.setAdminEmail(admin.getAdminEmail());
					model.setAdminPassword(admin.getAdminPassword());
				}
				
				break;
			}
		}
		return can;
	}

}
