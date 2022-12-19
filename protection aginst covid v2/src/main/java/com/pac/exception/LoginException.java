package com.pac.exception;

import org.springframework.beans.factory.annotation.Autowired;

import com.pac.dao.UserDao;
import com.pac.dao.UserSessionDao;

public class LoginException extends Exception{
	
	public LoginException() {
		
	}
	
	public LoginException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
}
