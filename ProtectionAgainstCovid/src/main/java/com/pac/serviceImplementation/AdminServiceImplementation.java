package com.pac.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.AdminDao;
import com.pac.dao.AdminSessionDao;
import com.pac.excpetion.AdminException;
import com.pac.model.Admin;
import com.pac.model.CurrentAdminSession;
import com.pac.service.AdminService;

@Service
public class AdminServiceImplementation implements AdminService{

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private AdminSessionDao adminSessionDao;
	
	@Override
	public Admin createAdmin(Admin admin) throws AdminException {

		//&& adminDao.findByEmail(admin.getEmail()) ==null  adminDao.findByMobileNo(admin.getMobileNo()) ==null && 
		//double check
		if(adminDao.findByEmail(admin.getEmail()).size()==0 && adminDao.findByMobileNo(admin.getMobileNo()) ==null) {
			return adminDao.save(admin);
		}
		else throw new AdminException("A Admin already exists with the same mobile number or emailId. Please give any other credentials.");
	}

	@Override
	public Admin updateAdmin(Admin admin, String key) throws AdminException {

		CurrentAdminSession currentAdminSession = adminSessionDao.findByUniqueUserId(key);
		
		if(currentAdminSession != null) {
			Admin existingAdmin = adminDao.findByMobileNo(admin.getMobileNo());
				if(existingAdmin != null) {
					return adminDao.save(admin);
				}
				else throw new AdminException("No such admin exists. Please supply valid admin profile to update it.");
		}
		else throw new AdminException("Admin currently not logged in. Please log-in to use this feature.");
	}

}
