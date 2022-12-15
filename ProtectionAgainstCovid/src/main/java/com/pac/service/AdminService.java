package com.pac.service;

import com.pac.excpetion.AdminException;
import com.pac.model.Admin;

public interface AdminService {

	public Admin createAdmin(Admin admin) throws AdminException;

	public Admin updateAdmin(Admin admin, String key) throws AdminException;
}
