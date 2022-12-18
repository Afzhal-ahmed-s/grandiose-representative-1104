package com.pac.serviceImplementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.VaccinationCenterRepo;
import com.pac.dao.VaccineCountrepo;
import com.pac.dao.VaccineInventoryRepo;
import com.pac.dao.VaccineRepo;
import com.pac.excpetion.VaccineException;
import com.pac.excpetion.VaccineInventoryException;
import com.pac.model.VaccinationCenter;
import com.pac.model.Vaccine;
import com.pac.model.VaccineCount;
import com.pac.model.VaccineInventory;
import com.pac.service.VaccineInventoryService;

@Service
public class VaccineInventoryServiceImpl implements VaccineInventoryService {
	@Autowired
	private VaccineInventoryRepo vcir;
	@Autowired
	private VaccineCountrepo vcrepo;
	
	@Autowired
	private VaccineRepo vr;
	
	@Autowired
	private VaccinationCenterRepo vaccinationCenterRepo;
	
	
	@Override
	public List<VaccineInventory> allVaccineInventory() throws VaccineInventoryException {
		List<VaccineInventory> vc=vcir.findAll();
		if(vc.size()==0) {
			throw new VaccineInventoryException("Not Found...");
		}
		return vc;
	}

	@Override
	public Boolean deleteVaccineInventory(VaccineInventory vaccineInventory) throws VaccineInventoryException {
		
		Optional<VaccineInventory> opt=vcir.findById(vaccineInventory.getInventoryId());
		if(opt.isPresent()) {
			VaccineInventory vi=opt.get();
			vcir.delete(vi);
			return true;
		}
		else {
			throw new VaccineInventoryException("not fount with this id");
		}
//		vcir.findById(inventoryId).orElseThrow(()-> new VaccineInventoryException("not fount with this id"+inventoryId));
//		vcir.deleteById(inventoryId);
		
		
		
	}

	@Override
	public List<VaccineInventory> getVaccineInventoryByDate(String date) throws VaccineInventoryException {
		
		List<VaccineInventory> list = vcir.findBydate(LocalDate.parse(date));
		if(list.isEmpty()) {
			throw new VaccineInventoryException("No VaccineInventory found with data : " + date);
		}else {
			System.out.println("check");
			return list;
		}
	}

	@Override
	public VaccineInventory getVaccineInventoryByCenter(Integer centerid) throws VaccineInventoryException{

		Optional<VaccinationCenter> vci=vaccinationCenterRepo.findById(centerid);
		
		VaccineInventory vi=vci.get().getVaccineInventory();
		if(vi==null) {
			throw new VaccineInventoryException("not found");
		}
		
		else {
			
			return vi;
		}
		
		

		
		
	}

	@Override
	public VaccineCount addVaccine(VaccineCount vaccineCount) throws VaccineInventoryException {
		Optional<VaccineCount>  vc=vcrepo.findById(vaccineCount.getVaccinecountid());
		if(vc==null) {
			return vcrepo.save(vaccineCount);
		}
		throw new VaccineInventoryException("Failed to add");
	}

	@Override
	public VaccineInventory updateVaccine(VaccineInventory vaccineinventory) throws VaccineInventoryException {
		vcir.findById(vaccineinventory.getInventoryId())
		.orElseThrow(() -> new VaccineInventoryException("Not Present With this "+vaccineinventory.getInventoryId()));
		return vcir.save(vaccineinventory);
	}

	@Override
	public List<VaccineInventory> getVaccineInventoryByVaccine(Vaccine vaccine) throws VaccineInventoryException {
		//List<VaccineInventory> v=v.findAll().orElseThrow(() -> new VaccineInventoryException("Not Present With this "+vaccine.getVaccineId()));
		return null;
	}
	
	

}
