package com.pac.service;

import com.pac.excpetion.LoginException;
import com.pac.model.AdminLogin;
import com.pac.model.UserLogin;

public interface LogInLogOutService {
	
	public String loginUser(UserLogin dto)throws LoginException;

	public String logOutUser(String key)throws LoginException;
	
	public String logInAdmin(AdminLogin adl) throws LoginException;
	
	public String logOutAdmin(String key) throws LoginException;

}
