package com.pac.service;

import java.time.LocalDate;
import java.util.List;

import com.pac.excpetion.VaccineInventoryException;
import com.pac.model.VaccineInventory;

public interface VaccineInventoryService {
	public List<VaccineInventory> allVaccineInventory()throws VaccineInventoryException;
	public Boolean deleteVaccineInventory(Integer inventoryId)throws VaccineInventoryException;
	public List<VaccineInventory> getVaccineInventoryByDate(LocalDate date)throws VaccineInventoryException;
	public VaccineInventory  getVaccineInventoryByCenter(Integer code);
}
