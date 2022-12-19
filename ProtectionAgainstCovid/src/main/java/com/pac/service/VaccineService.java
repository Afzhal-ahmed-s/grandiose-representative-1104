package com.pac.service;

import java.util.List;

import com.pac.excpetion.LoginException;
import com.pac.excpetion.VaccineException;
import com.pac.model.Vaccine;

public interface VaccineService {
	
	public List<Vaccine> allVaccines() throws VaccineException;

	public List<Vaccine> getVaccineByName(String vaccineName, String key) throws VaccineException, LoginException;

	public Vaccine getVaccineById(Integer vaccineId, String key) throws VaccineException, LoginException;

	public Vaccine addVaccine(Vaccine vaccine, String key) throws VaccineException, LoginException;

	public Vaccine updateVaccine(Vaccine vaccine, String key) throws VaccineException, LoginException;

	public Boolean deleteVaccine(Integer vaccineId, String key) throws VaccineException, LoginException;

}