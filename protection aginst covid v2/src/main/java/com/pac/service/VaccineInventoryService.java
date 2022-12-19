package com.pac.service;

import java.time.LocalDate;
import java.util.List;

import com.pac.exception.LoginException;
import com.pac.exception.VaccineException;
import com.pac.model.Vaccine;
import com.pac.model.VaccineInventory;

public interface VaccineInventoryService {

	public VaccineInventory getVaccineInventoryByCenter(Integer Centerid, String key)
			throws VaccineException, LoginException;

	public VaccineInventory addVaccineCount(VaccineInventory vinv, Integer count, String key)
			throws VaccineException, LoginException;

	public VaccineInventory updateVaccineInventory(VaccineInventory vinv, String key)
			throws VaccineException, LoginException;

	public boolean deleteVaccineInventory(VaccineInventory vinv, String key) throws VaccineException, LoginException;

	public List<VaccineInventory> getVaccineInventoryByDate(LocalDate ld, String key)
			throws VaccineException, LoginException;

	public VaccineInventory getVaccineInventoryByVaccine(Vaccine vc, String key)
			throws VaccineException, LoginException;

	public VaccineInventory addVaccineInventory(VaccineInventory vaccineInventory, String key)
			throws VaccineException, LoginException;
}