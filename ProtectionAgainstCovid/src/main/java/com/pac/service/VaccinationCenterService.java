package com.pac.service;

import java.util.List;

import com.pac.excpetion.VaccinationCenterException;
import com.pac.model.VaccinationCenter;

public interface VaccinationCenterService {

	public List<VaccinationCenter> getAllVaccineCenters() throws VaccinationCenterException;
	
	public VaccinationCenter getVaccineCenter(Integer centerCode) throws VaccinationCenterException;
	
	public VaccinationCenter addVaccinationCenter(VaccinationCenter center) throws VaccinationCenterException;
	
	public VaccinationCenter updateVaccinationCenter(VaccinationCenter vaccinationCenter) throws VaccinationCenterException;
	
	public boolean deleteVaccinationCenter(VaccinationCenter vaccinationCenter) throws VaccinationCenterException;
	
}
