package com.pac.service;

import java.time.LocalDate;
import java.util.List;

import com.pac.excpetion.VaccineException;
import com.pac.excpetion.VaccineInventoryException;
import com.pac.model.Vaccine;
import com.pac.model.VaccineCount;
import com.pac.model.VaccineInventory;

public interface VaccineInventoryService {
	public List<VaccineInventory> allVaccineInventory()throws VaccineInventoryException;
	public Boolean deleteVaccineInventory(VaccineInventory vaccineInventory)throws VaccineInventoryException;
	public List<VaccineInventory> getVaccineInventoryByDate(String  date)throws VaccineInventoryException;
	public VaccineInventory getVaccineInventoryByCenter(Integer centerid)throws VaccineInventoryException;
	public VaccineCount addVaccine(VaccineCount VaccineCount)throws VaccineInventoryException;
	public VaccineInventory updateVaccine(VaccineInventory vaccineinventory)throws VaccineInventoryException;
	public List<VaccineInventory> getVaccineInventoryByVaccine(Vaccine vaccine)throws VaccineInventoryException;
}