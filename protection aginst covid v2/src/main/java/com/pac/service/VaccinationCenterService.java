package com.pac.service;

import java.util.List;

import com.pac.exception.LoginException;
import com.pac.exception.VaccineCenterException;
import com.pac.model.VaccinationCenter;

public interface VaccinationCenterService {

	public List<VaccinationCenter> getAllVaccineCenters() throws VaccineCenterException;

	public VaccinationCenter getVaccineCenter(Integer centerId, String key)
			throws VaccineCenterException, LoginException;

	public VaccinationCenter addVaccinationCenter(VaccinationCenter center, String key)
			throws VaccineCenterException, LoginException;

	public VaccinationCenter updateVaccinationCenter(VaccinationCenter center, String key)
			throws VaccineCenterException, LoginException;

	public boolean deleteVaccinationCenter(VaccinationCenter center, String key)
			throws VaccineCenterException, LoginException;

}
