package com.pac.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.pac.excpetion.VaccineException;
import com.pac.model.Vaccine;
import com.pac.service.VaccineService;

@RestController
public class VaccineController {
	@Autowired
	private VaccineService vs;
	
	@PostMapping("/vaccine")
	public ResponseEntity<Vaccine> registerVaccineHandler(@Valid @RequestBody Vaccine vac)throws VaccineException{
		
		Vaccine vc = vs.addVaccine(vac);
		
		return new ResponseEntity<Vaccine>(vc, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/allvaccine")
	public ResponseEntity<List<Vaccine>> getAllVaccineHandler()throws VaccineException{
		
		List<Vaccine> vccs = vs.allVaccine();
		
		return new ResponseEntity<List<Vaccine>>(vccs, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/vaccine")
    public ResponseEntity<List<Vaccine>> getVaccinebyNameHandler(@RequestParam String vaccineName)throws VaccineException{
		List<Vaccine> vccs =vs.getVaccineByName(vaccineName);
		
		return new ResponseEntity<List<Vaccine>>(vccs, HttpStatus.OK);
		
	}
	
	@GetMapping("/vaccine/{vacId}")
	public ResponseEntity<Vaccine> getVaccineByIdHandler(@PathVariable ("vacId")Integer vaccineId)throws VaccineException{
		
		Vaccine vc = vs.getVaccineById(vaccineId);
		
		return new ResponseEntity<Vaccine>(vc, HttpStatus.OK);
		
	}
	
	
	@PutMapping("/upvaccine")
	public ResponseEntity<Vaccine> updateVaccineHandler(@Valid @RequestBody Vaccine c)throws VaccineException{
		
		Vaccine vc = vs.updateVaccine(c);
		
		return new ResponseEntity<Vaccine>(vc, HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/vaccine")
	public ResponseEntity< Boolean > deleteVaccine( @Valid @RequestBody Vaccine v ) throws VaccineException{
		
		Boolean boolean1 = vs.deleteVaccine( v );
		
		
		return new ResponseEntity<Boolean>( boolean1 , HttpStatus.ACCEPTED );
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	


	
}
