package com.pac.serviceImplementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pac.dao.VaccineInventoryRepo;
import com.pac.excpetion.VaccineInventoryException;
import com.pac.model.VaccineInventory;
import com.pac.service.VaccineInventoryService;

@Service
public class VaccineInventoryServiceImpl implements VaccineInventoryService {

	VaccineInventoryRepo vcir;
	
	@Override
	public List<VaccineInventory> allVaccineInventory() throws VaccineInventoryException {
		List<VaccineInventory> vc=vcir.findAll();
		if(vc.size()==0) {
			throw new VaccineInventoryException("Not Found...");
		}
		return vc;
	}

	@Override
	public Boolean deleteVaccineInventory(Integer inventoryId) throws VaccineInventoryException {
		vcir.findById(inventoryId).orElseThrow(()-> new VaccineInventoryException("not fount with this id"+inventoryId));
		vcir.deleteById(inventoryId);
		
		return true;
		
	}

	@Override
	public List<VaccineInventory> getVaccineInventoryByDate(LocalDate date) throws VaccineInventoryException {
		List<VaccineInventory> list = vcir.findBydate(date);
		if(list.isEmpty()) {
			throw new VaccineInventoryException("No VaccineInventory found with data : " + date);
		}else
			return list;
		
	}
	
	

}
