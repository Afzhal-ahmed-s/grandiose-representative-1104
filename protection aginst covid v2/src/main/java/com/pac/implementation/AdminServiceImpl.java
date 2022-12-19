package com.pac.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.AdminDao;
import com.pac.dao.AdminSessionDao;
import com.pac.exception.AdminException;
import com.pac.model.Admin;
import com.pac.model.CurrentAdminSession;
import com.pac.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private AdminSessionDao adminSRepo;
	@Override
	public Admin createCustomer(Admin admin) throws AdminException {
		
		Admin existingUser=adminDao.findByMobileNo(admin.getMobileNo());
		if(existingUser==null) {
			return adminDao.save(admin);
		}else {
			throw new AdminException("User already registered with mobile number");
		}
	}

	@Override
	public Admin updateCustomer(Admin admin, String key) throws AdminException {
		CurrentAdminSession loggedInUser=adminSRepo.findByuuid(key);
		if(loggedInUser==null) {
			throw new AdminException("Please provide a valid key to update a user");
		}
		
		if(admin.getUserId()==loggedInUser.getUserId()) {
			return adminDao.save(admin);
		}else {
			throw new AdminException("Invalid user details , please login first");
		}
	}

}
