package com.pac.service;

import com.pac.exception.AdminException;
import com.pac.model.Admin;

public interface AdminService {
	public Admin createCustomer(Admin admin) throws AdminException;

	public Admin updateCustomer(Admin admin, String key) throws AdminException;
}
