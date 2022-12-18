package com.pac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.pac.excpetion.AdminLoginException;
import com.pac.excpetion.MemberException;
import com.pac.excpetion.VaccineRegistrationException;
import com.pac.model.Member;
import com.pac.model.VaccineRegistration;
import com.pac.service.VaccineRegistrationService;

public class VaccineRegistrationController {

	@Autowired
    private VaccineRegistrationService registrationService;
	
	
	@GetMapping("/Allregistrations")
	     public ResponseEntity<List<VaccineRegistration>> getallRegistration() throws VaccineRegistrationException{
	    	 
	    	        List<VaccineRegistration> allreigs= registrationService.getAllVaccineRegistration();
	    	        
	    	        return new ResponseEntity<List<VaccineRegistration>>(allreigs, HttpStatus.OK);
	    	        
	     }
	
	@GetMapping("/member/{mobileNumber}")
	 public ResponseEntity<Member> getAllMemberByNu(@PathVariable("mobileNumber") Long mobileNumber,@RequestParam String key) throws VaccineRegistrationException, AdminLoginException, MemberException{
		 
		    return new ResponseEntity<Member>(registrationService.getAllMember(mobileNumber, key),HttpStatus.OK);
	 }
	
	
	  @PostMapping("/registration")
	   public ResponseEntity<VaccineRegistration> addRegistration( @RequestBody VaccineRegistration registration,@RequestParam String key) throws VaccineRegistrationException, MemberException{
		   
		      return new ResponseEntity<VaccineRegistration>(registrationService.addVaccineRegistration(registration,key), HttpStatus.OK);
	   }
	  
	  @GetMapping("/registrations/{mobileNumber}")
	  public ResponseEntity<VaccineRegistration> getVaccineRegistration( @PathVariable("mobileNumber") Long mobileNumber,@RequestParam String key) throws VaccineRegistrationException, MemberException, AdminLoginException{
		  
		    return new ResponseEntity<VaccineRegistration>(registrationService.getVaccineRegistration(mobileNumber,key),HttpStatus.OK);
	  }
	  
	  @PutMapping("/registration")
	  public ResponseEntity<VaccineRegistration> UpdateRegistration( @RequestBody VaccineRegistration registration,@RequestParam String key) throws VaccineRegistrationException, MemberException{
		  
		      return new ResponseEntity<VaccineRegistration>(registrationService.updateVaccineRegistration(registration,key),HttpStatus.OK);
	  }
	  
	  @DeleteMapping("/registration")
	  public boolean DeleteRegistraion( @RequestBody VaccineRegistration registration,@RequestParam String key) throws VaccineRegistrationException, MemberException{
		        
		      boolean x= registrationService.deleteVaccineRegistration(registration,key);
		              
		               
		      return x;
	  }
	
	
	
}
