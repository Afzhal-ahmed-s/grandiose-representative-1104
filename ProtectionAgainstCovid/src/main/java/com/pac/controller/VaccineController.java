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
	
	@PostMapping("/vaccine/{key}")
	public ResponseEntity<Vaccine> registerVaccineHandler(@Valid @RequestBody Vaccine vac, String key)throws VaccineException, LoginException{
		
		Vaccine vc = vs.addVaccine(vac, key);
		
		return new ResponseEntity<Vaccine>(vc, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/allvaccine")
	public ResponseEntity<List<Vaccine>> getAllVaccineHandler()throws VaccineException{
		
		List<Vaccine> vccs = vs.allVaccines();
		
		return new ResponseEntity<List<Vaccine>>(vccs, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/vaccine/{key}")
    public ResponseEntity<List<Vaccine>> getVaccinebyNameHandler(@RequestParam String vaccineName, String key)throws VaccineException, LoginException{
		List<Vaccine> vccs =vs.getVaccineByName(vaccineName, key);
		
		return new ResponseEntity<List<Vaccine>>(vccs, HttpStatus.OK);
		
	}
	
	@GetMapping("/vaccine/{vacId}/{key}")
	public ResponseEntity<Vaccine> getVaccineByIdHandler(@PathVariable ("vacId")Integer vaccineId, String key)throws VaccineException, LoginException{
		
		Vaccine vc = vs.getVaccineById(vaccineId, key);
		
		return new ResponseEntity<Vaccine>(vc, HttpStatus.OK);
		
	}
	
	
	@PutMapping("/upvaccine/{key}")
	public ResponseEntity<Vaccine> updateVaccineHandler(@Valid @RequestBody Vaccine c, String key)throws VaccineException, LoginException{
		
		Vaccine vc = vs.updateVaccine(c, key);
		
		return new ResponseEntity<Vaccine>(vc, HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/vaccine/{key}")
	public ResponseEntity< Boolean > deleteVaccine( @Valid @RequestBody Vaccine v ,String key) throws VaccineException, LoginException{
		
		Boolean boolean1 = vs.deleteVaccine(v.getVaccineId(), key);
		
		
		return new ResponseEntity<Boolean>( boolean1 , HttpStatus.ACCEPTED );
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	


	
}
