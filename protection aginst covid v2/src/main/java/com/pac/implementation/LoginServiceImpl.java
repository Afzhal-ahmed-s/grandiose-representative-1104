package com.pac.implementation;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.AdminDao;
import com.pac.dao.AdminSessionDao;
import com.pac.dao.UserDao;
import com.pac.dao.UserSessionDao;
import com.pac.exception.LoginException;
import com.pac.model.Admin;
import com.pac.model.AdminLogin;
import com.pac.model.CurrentAdminSession;
import com.pac.model.CurrentUserSession;
import com.pac.model.User;
import com.pac.model.UserLogin;
import com.pac.service.LoginLogoutService;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginLogoutService{
	
	@Autowired
	private UserDao uRepo;
	@Autowired
	private UserSessionDao usRepo;
	
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private AdminSessionDao adminSRepo;
	@Override
	public String logIntoAccount(UserLogin login) throws LoginException {
		User existingUser=uRepo.findByMobileNo(login.getMobileNo());
		
		if(existingUser==null) {
			throw new LoginException("Please enter a valid mobile number");
		}
		
		Optional<CurrentUserSession> opt=usRepo.findById(existingUser.getUserId());
		
		if(opt.isPresent()) {
			throw new LoginException("User already logged in with this number");
		}
		
		if(existingUser.getPassword().equals(login.getPassword())) {
			String key=RandomString.make(6);
			CurrentUserSession currentUserSession=new CurrentUserSession(existingUser.getUserId(),key,LocalDateTime.now());
			usRepo.save(currentUserSession);
			return currentUserSession.toString();
		}else {
			throw new LoginException("Please Enter a valid password");
		}
	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		CurrentUserSession validUserSession = usRepo.findByuuid(key);
		
		
		if(validUserSession == null) {
			throw new LoginException("User Not Logged In with this number");
			
		}
		
		
		usRepo.delete(validUserSession);
		
		
		return "Logged Out !";
	}

	@Override
	public String logIntoAdmin(AdminLogin adl) throws LoginException {
		Admin existingUser=adminDao.findByMobileNo(adl.getMobileNo());
		
		if(existingUser==null) {
			throw new LoginException("Please enter a valid mobile number");
		}
		
		Optional<CurrentAdminSession> opt=adminSRepo.findById(existingUser.getUserId());
		
		if(opt.isPresent()) {
			throw new LoginException("Admin already logged in with this number");
		}
		
		if(existingUser.getPassword().equals(adl.getPassword())) {
			String key=RandomString.make(6);
			CurrentAdminSession currentUserSession=new CurrentAdminSession(existingUser.getUserId(),key,LocalDateTime.now());
			adminSRepo.save(currentUserSession);
			return currentUserSession.toString();
		}else {
			throw new LoginException("Please Enter a valid password");
		}
	}

	@Override
	public String logOutAdmin(String key) throws LoginException {
		CurrentAdminSession validUserSession = adminSRepo.findByuuid(key);
		
		
		if(validUserSession == null) {
			throw new LoginException("User Not Logged In with this number");
			
		}
		
		
		adminSRepo.delete(validUserSession);
		
		
		return "Logged Out !";
	}

}
