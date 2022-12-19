package com.pac.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pac.excpetion.LoginException;
import com.pac.excpetion.VaccinationCenterException;
import com.pac.excpetion.VaccineException;
import com.pac.excpetion.VaccineInventoryException;
import com.pac.model.VaccinationCenter;
import com.pac.model.Vaccine;
import com.pac.model.VaccineInventory;
import com.pac.service.VaccinationCenterService;
import com.pac.service.VaccineInventoryService;

@RestController
public class VaccineInventoriesController {
	@Autowired
	public VaccineInventoryService vis;
	
	@Autowired
	public VaccinationCenterService vacCntrSer;
	
	
	@PostMapping("/vaccineInventory/{key}")
	public ResponseEntity<VaccineInventory> registerVaccineHandler(@Valid @RequestBody VaccineInventory vac, String key)throws VaccineInventoryException, VaccineException, LoginException{
		
		VaccineInventory vi = vis.addVaccineInventory(vac, key);
		
		return new ResponseEntity<VaccineInventory>(vi, HttpStatus.CREATED);
		
	}
	
	
//	@GetMapping("/vaccineInventories")
//	public ResponseEntity <List<VaccineInventory>> getAllVacciceInventory() throws VaccineInventoryException {
//		
//		List <VaccineInventory>  vi =  vis.
//		
//		return new ResponseEntity<List<VaccineInventory>>(vi, HttpStatus.OK);
//	}
	
	
	@DeleteMapping("/vaccineInventory/{vi}/{key}")
	public ResponseEntity<VaccineInventory> deleteVaccineInventory( @PathVariable("vi")@RequestBody VaccineInventory vaccineInventory, String key ) throws VaccineInventoryException, VaccineException, LoginException{
	
		Boolean b =  vis.deleteVaccineInventory( vaccineInventory, key );
		
		return new ResponseEntity<VaccineInventory>(vaccineInventory, HttpStatus.ACCEPTED);
	} 
	
	@GetMapping("/vaccineInventory/{date}")
    public ResponseEntity<List<VaccineInventory>> getVaccinebyDateHandler(@PathVariable String date, String key)throws VaccineInventoryException, VaccineException, LoginException{
		List<VaccineInventory> vccs =vis.getVaccineInventoryByDate(LocalDate.parse(date), key);

		return new ResponseEntity<List<VaccineInventory>>(vccs, HttpStatus.OK);
		
	}
	
//	
//	@GetMapping("/getvcCenters/{code}")
//	public ResponseEntity<VaccineInventory> getVaccineInventorybyCenteridhandler(@PathVariable("code") Integer code) throws VaccineInventoryException, VaccinationCenterException{
//		System.out.println(code);
//		VaccinationCenter vaccinationCenter=vacCntrSer.getVaccineCenter(code);
//		VaccineInventory vaccineInventory=vaccinationCenter.getVaccineInventory();
////		
//		System.out.println(vaccineInventory);
//		return new ResponseEntity<VaccineInventory>(vaccineInventory, HttpStatus.ACCEPTED);
//	}
	
}
