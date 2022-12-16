package com.pac.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pac.excpetion.AdminException;
import com.pac.model.Admin;
import com.pac.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/signUp")
	public ResponseEntity<Admin> signUpAdmin(@Valid @RequestBody Admin admin) throws AdminException{
		
		Admin createdAdmin = adminService.createAdmin(admin);
		return new ResponseEntity<Admin>(createdAdmin, HttpStatus.CREATED);
	}
	
	@PutMapping("/profileUpdate/{uuidKey}")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin, @PathVariable(value = "uuidKey") String key) throws AdminException{
		
		Admin updatedAdmin = adminService.updateAdmin(admin, key);
		return new ResponseEntity<Admin>(updatedAdmin, HttpStatus.ACCEPTED);

	}
	
}
