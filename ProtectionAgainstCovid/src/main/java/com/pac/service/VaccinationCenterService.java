package com.pac.service;

import java.util.List;

import com.pac.excpetion.LoginException;
import com.pac.excpetion.VaccinationCenterException;
import com.pac.model.VaccinationCenter;

public interface VaccinationCenterService {

	public List<VaccinationCenter> getAllVaccineCenters() throws VaccinationCenterException;
	
	public VaccinationCenter getVaccineCenter(Integer centerCode , String key) throws VaccinationCenterException,LoginException;
	
	public VaccinationCenter addVaccinationCenter(VaccinationCenter center,String key) throws VaccinationCenterException,LoginException;
	
	public VaccinationCenter updateVaccinationCenter(VaccinationCenter vaccinationCenter , String key) throws VaccinationCenterException,LoginException;
	
	public boolean deleteVaccinationCenter(VaccinationCenter vaccinationCenter,String key) throws VaccinationCenterException,LoginException;
	
}
