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
import org.springframework.web.bind.annotation.RestController;

import com.pac.excpetion.LoginException;
import com.pac.excpetion.VaccinationCenterException;
import com.pac.model.VaccinationCenter;
import com.pac.service.VaccinationCenterService;

@RestController
public class VaccinationCenterController {

	@Autowired
	private VaccinationCenterService vcService;
	
	@GetMapping("/getvcCenters")
	public ResponseEntity<List<VaccinationCenter>> getAllVaccinationCenterHandler() throws VaccinationCenterException{
		
		List<VaccinationCenter> allvcCenter = vcService.getAllVaccineCenters();
		
		return new ResponseEntity<List<VaccinationCenter>>(allvcCenter, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getvcCenters/{code}")
	public ResponseEntity<VaccinationCenter> getVaccinationCenterByIdHandler(@PathVariable("code") Integer code , @RequestParam(value = "key" , required = false) String key) throws VaccinationCenterException,LoginException{
		
		VaccinationCenter vc = vcService.getVaccineCenter(code,key);
		
		return new ResponseEntity<VaccinationCenter>(vc, HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("/getvcCenters")
	public ResponseEntity<VaccinationCenter> addVaccinationCenterHandler(@RequestBody VaccinationCenter vaccinationCenter, @RequestParam(value = "key" , required = false) String key) throws VaccinationCenterException, LoginException{
		
		VaccinationCenter addVcCenter = vcService.addVaccinationCenter(vaccinationCenter,key);
		
		return new ResponseEntity<VaccinationCenter>(addVcCenter, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/getvcCenters")
	public ResponseEntity<VaccinationCenter> updateVaccinationCenterHandler(@RequestBody VaccinationCenter vaccinationCenter, @RequestParam(value = "key" , required = false) String key) throws VaccinationCenterException,LoginException{
		
		VaccinationCenter vc = vcService.updateVaccinationCenter(vaccinationCenter,key);
		
		return new ResponseEntity<VaccinationCenter>(vc, HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/getvcCenters/{code}")
	public ResponseEntity<VaccinationCenter> deleteVaccinationCenterHandler(@PathVariable("code") @RequestBody VaccinationCenter vaccinationCenter, @RequestParam(value = "key" , required = false) String key) throws VaccinationCenterException,LoginException{
		
		boolean vc = vcService.deleteVaccinationCenter(vaccinationCenter,key);
		return new ResponseEntity<VaccinationCenter>(vaccinationCenter, HttpStatus.ACCEPTED);
	}
	
}
