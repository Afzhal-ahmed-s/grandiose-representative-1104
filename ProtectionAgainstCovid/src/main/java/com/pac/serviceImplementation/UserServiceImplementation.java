package com.pac.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.UserDao;
import com.pac.dao.UserSessionDao;
import com.pac.excpetion.UserException;
import com.pac.model.CurrentUserSession;
import com.pac.model.User;
import com.pac.service.UserService;

@Service
public class UserServiceImplementation implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserSessionDao userSessionDao;

	@Override
	public User createUser(User user) throws UserException {

		//double check
				if(userDao.findByMobileNo(user.getMobileNo())==null && userDao.findByEmail(user.getEmail()).size()==0) {
					return userDao.save(user);
				}
				else throw new UserException("A User already exists with the same mobile number or emailId. Please give any other credentials.");

	}

	@Override
	public User updateUser(User user, String key) throws UserException {

		CurrentUserSession currentUserSession = userSessionDao.findByUniqueUserId(key);
		System.out.println("Check 1: "+ key);
		if(currentUserSession != null) {
			User existingUser =userDao.findByMobileNo(user.getMobileNo());
				if(existingUser != null) {
					
					if(user.getEmail() != null)existingUser.setEmail(user.getEmail());
//					if(user.getMobileNo() != null)existingUser.setMobileNo(user.getMobileNo());
					if(user.getName() != null)existingUser.setName(user.getName());
					if(user.getPassword() != null)existingUser.setPassword(user.getPassword());
					System.out.println("Check 2: "+ existingUser);

					return userDao.save(existingUser);
				}
				else throw new UserException("No such User exists. Please supply valid user profile to update it.");
		}
		else throw new UserException("User currently not logged in. Please log-in to use this feature.");
	}
	
	
	
}
