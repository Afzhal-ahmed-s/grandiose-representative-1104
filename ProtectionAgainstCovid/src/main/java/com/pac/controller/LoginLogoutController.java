package com.pac.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pac.excpetion.LoginException;
import com.pac.model.AdminLogin;
import com.pac.model.UserLogin;
import com.pac.service.LogInLogOutService;

import lombok.val;

@RestController
@RequestMapping("/authenticate")
public class LoginLogoutController {

	@Autowired
	private LogInLogOutService logInLogOutService;
	
	@PostMapping("/adminLogin")
	public ResponseEntity<String> adminLogin(@RequestBody AdminLogin adminDto) throws LoginException{
		
		String output = logInLogOutService.logInAdmin(adminDto);
		return new ResponseEntity<String>(output, HttpStatus.CREATED);
	}
	
	@PostMapping("/userLogin")
	public ResponseEntity<String> userLogin(@RequestBody UserLogin userDto) throws LoginException{

		String output = logInLogOutService.loginUser(userDto);
		return new ResponseEntity<String>(output, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/adminLogout/{key}")
	public ResponseEntity<String> adminLogout(@PathParam(value = "key") String key) throws LoginException{
		return  new ResponseEntity<String>(logInLogOutService.logOutAdmin(key), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/userLogout/{key}")
	public ResponseEntity<String> userLogout(@PathParam(value = "key") String key) throws LoginException{
		return  new ResponseEntity<String>(logInLogOutService.logOutUser(key), HttpStatus.ACCEPTED);
	}
	
}
