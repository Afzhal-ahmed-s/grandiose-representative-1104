package com.pac.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.VaccineCenterDao;
import com.pac.excpetion.VaccinationCenterException;
import com.pac.model.VaccinationCenter;
import com.pac.service.VaccinationCenterService;

@Service
public class VaccineCenterServiceImpl implements VaccinationCenterService{
	
	@Autowired
	private VaccineCenterDao vcdao;
	

	@Override
	public List<VaccinationCenter> getAllVaccineCenters() throws VaccinationCenterException {

		List<VaccinationCenter> allVaccinationCenters = vcdao.findAll();
		
		if(allVaccinationCenters.isEmpty())
			throw new VaccinationCenterException("No Vaccination Center Data Available.....");
		else
			return allVaccinationCenters;
		
	}

	@Override
	public VaccinationCenter getVaccineCenter(Integer centerCode) throws VaccinationCenterException {

		Optional<VaccinationCenter> opt = vcdao.findById(centerCode);
		
		if(opt.isPresent()) {
			VaccinationCenter vc = opt.get();
			return vc;
		}
		else
			throw new VaccinationCenterException("No Vaccination Center Found with Code: "+centerCode);
		
	}

	@Override
	public VaccinationCenter addVaccinationCenter(VaccinationCenter center) throws VaccinationCenterException {

		VaccinationCenter addVaccinationCenter = vcdao.save(center);
		
		return addVaccinationCenter;
		
	}

	@Override
	public VaccinationCenter updateVaccinationCenter(VaccinationCenter vaccinationCenter) throws VaccinationCenterException {

		Optional<VaccinationCenter> opt = vcdao.findById(vaccinationCenter.getCode());
		
		if(opt.isPresent()) {
			VaccinationCenter vc = opt.get();
			return vcdao.save(vc);
		}
		else
			throw new VaccinationCenterException("No Vaccination Center Exisits");
	}

	@Override
	public boolean deleteVaccinationCenter(VaccinationCenter vaccinationCenter) throws VaccinationCenterException {

		Optional<VaccinationCenter> opt = vcdao.findById(vaccinationCenter.getCode());
		
		if(opt.isPresent()) {
			VaccinationCenter vc = opt.get();
			 vcdao.delete(vc);
			 return true;
		}
		else
			throw new VaccinationCenterException("No Vaccination Center Exisits");
		
	}

}
