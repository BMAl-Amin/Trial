package com.gams.services.interfaces;

import com.gams.models.Admin;

public interface AdminInterfaces {
	public void checkAdminAvailability(Admin model);
	public boolean canLog(Admin model);
}
