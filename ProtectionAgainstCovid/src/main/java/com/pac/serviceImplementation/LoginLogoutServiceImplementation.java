package com.pac.serviceImplementation;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.AdminDao;
import com.pac.dao.AdminSessionDao;
import com.pac.dao.UserDao;
import com.pac.dao.UserSessionDao;
import com.pac.excpetion.LoginException;
import com.pac.model.Admin;
import com.pac.model.AdminLogin;
import com.pac.model.CurrentAdminSession;
import com.pac.model.CurrentUserSession;
import com.pac.model.User;
import com.pac.model.UserLogin;
import com.pac.service.LogInLogOutService;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginLogoutServiceImplementation implements LogInLogOutService{

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private UserDao userDao; 
	
	@Autowired
	private AdminSessionDao adminSessionDao;
	
	@Autowired
	private UserSessionDao userSessionDao;

	@Override
	public String loginUser(UserLogin dto) throws LoginException {

		User existingUser = userDao.findByMobileNo(dto.getMobileNo());
		
		
		if(existingUser != null) {
			
			Optional<CurrentUserSession> existingUserSession = userSessionDao.findById(existingUser.getUserId());
			if(existingUserSession.isEmpty()) {
				CurrentUserSession currentUserSession = new CurrentUserSession();
				currentUserSession.setLocalDateTime(LocalDateTime.now());
				currentUserSession.setUniqueUserId(RandomString.make(6));
				currentUserSession.setUserId(existingUser.getUserId());
				
				userSessionDao.save(currentUserSession);
			}
			else throw new LoginException("User has already logged in with this credentials.");
			
		}
		else throw new LoginException("Sign-up as a User to log-in as a user.");
		return "User logged in successfully.";
	}

	@Override
	public String logOutUser(String key) throws LoginException {
		System.out.println("Check: "+key);

		CurrentUserSession currentUserSession = userSessionDao.findByUniqueUserId(key);
		if(currentUserSession != null) {
			userSessionDao.delete(currentUserSession);
		}
		else throw new LoginException("No such user has a current session. Please give valid input details.");
		return "User with ID "+ currentUserSession.getUserId()+ " logged out.";
	}

	@Override
	public String logInAdmin(AdminLogin dto) throws LoginException {
		Admin existingAdmin = adminDao.findByMobileNo(dto.getMobileNo());
		
		
		if(existingAdmin != null) {
			
			Optional<CurrentAdminSession> existingAdminSession = adminSessionDao.findById(existingAdmin.getAdminId());
			if(existingAdminSession.isEmpty()) {
				CurrentAdminSession currentAdminSession = new CurrentAdminSession();
				currentAdminSession.setLoginDateAndTime(LocalDateTime.now());
				currentAdminSession.setUniqueUserId(RandomString.make(6));
				currentAdminSession.setAdminId(existingAdmin.getAdminId());
				
				adminSessionDao.save(currentAdminSession);
			}
			else throw new LoginException("Admin has already logged in with this credentials.");
			
		}
		else throw new LoginException("Sign-up as a Admin to log-in as a admin.");
		return "Admin logged in successfully.";
	}

	@Override
	public String logOutAdmin(String key) throws LoginException {
		CurrentAdminSession currentAdminSession = adminSessionDao.findByUniqueUserId(key);
		if(currentAdminSession != null) {
			adminSessionDao.delete(currentAdminSession);
		}
		else throw new LoginException("No such user has a current session. Please give valid input details.");
		return "Admin with ID "+ currentAdminSession.getAdminId()+ " logged out.";
	}
	
	
}
