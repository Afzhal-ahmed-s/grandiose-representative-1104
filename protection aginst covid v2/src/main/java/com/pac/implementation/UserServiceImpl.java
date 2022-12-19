package com.pac.implementation;

import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.UserDao;
import com.pac.dao.UserSessionDao;
import com.pac.exception.UserException;
import com.pac.model.CurrentUserSession;
import com.pac.model.User;
import com.pac.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao uRepo;
	
	@Autowired
	private UserSessionDao usRepo;
	@Override
	public User createCustomer(User user) throws UserException {
		User existingUser=uRepo.findByMobileNo(user.getMobileNo());
		if(existingUser==null) {
			return uRepo.save(user);
		}else {
			throw new UserException("User already registered with mobile number");
		}
	}

	@Override
	public User updateCustomer(User user, String key) throws UserException {
		CurrentUserSession loggedInUser=usRepo.findByuuid(key);
		if(loggedInUser==null) {
			throw new UserException("Please provide a valid key to update a user");
		}
		
		if(user.getUserId()==loggedInUser.getUserId()) {
			return uRepo.save(user);
		}else {
			throw new UserException("Invalid user details , please login first");
		}
	}

}
