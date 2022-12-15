package com.pac.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;

import com.pac.dao.UserDao;
import com.pac.dao.UserSessionDao;
import com.pac.excpetion.AdminException;
import com.pac.excpetion.UserException;
import com.pac.model.Admin;
import com.pac.model.CurrentAdminSession;
import com.pac.model.CurrentUserSession;
import com.pac.model.User;
import com.pac.service.UserService;

public class UserServiceImplementation implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserSessionDao userSessionDao;

	@Override
	public User createUser(User user) throws UserException {

		//double check
				if(userDao.findByMobileNo(user.getMobileNo())==null && userDao.findByEmail(user.getEmail())==null) {
					return userDao.save(user);
				}
				else throw new UserException("This User account already exists");

	}

	@Override
	public User updateUser(User user, String key) throws UserException {

		CurrentUserSession currentUserSession = userSessionDao.findByUniqueUserId(key);
		
		if(currentUserSession != null) {
			User existingAdmin = userDao.findByMobileNo(user.getMobileNo());
				if(existingAdmin != null) {
					return userDao.save(user);
				}
				else throw new UserException("No such User exists. Please supply valid user profile to update it.");
		}
		else throw new UserException("User currently not logged in. Please log-in to use this feature.");
	}
	
	
	
}
