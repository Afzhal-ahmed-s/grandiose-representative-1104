package com.pac.service;

import com.pac.exception.LoginException;
import com.pac.model.AdminLogin;
import com.pac.model.UserLogin;

public interface LoginLogoutService {
	public String logIntoAccount(UserLogin dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;
	
	public String logIntoAdmin(AdminLogin adl) throws LoginException;
	
	public String logOutAdmin(String key) throws LoginException;
	
}
