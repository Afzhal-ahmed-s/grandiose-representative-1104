package com.pac.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pac.dao.UserSessionDao;
import com.pac.excpetion.UserException;
import com.pac.model.User;
import com.pac.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSessionDao userSessionDao;
	
	@PostMapping("/signUp")
	public ResponseEntity<User> signUpUser(@RequestBody User user) throws UserException{
		User signedUpUser = userService.createUser(user);
		return new ResponseEntity<User>(signedUpUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser/{uuidKey}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathParam("uuidKey")String key) throws UserException{
		User updatedUser = userService.updateUser(user, key);
		return new ResponseEntity<User>(updatedUser, HttpStatus.ACCEPTED);
	}
	
}
