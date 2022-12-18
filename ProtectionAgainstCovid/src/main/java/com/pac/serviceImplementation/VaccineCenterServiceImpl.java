package com.pac.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.AdminSessionDao;
import com.pac.dao.UserSessionDao;
import com.pac.dao.VaccineCenterDao;
import com.pac.excpetion.LoginException;
import com.pac.excpetion.VaccinationCenterException;
import com.pac.model.CurrentAdminSession;
import com.pac.model.CurrentUserSession;
import com.pac.model.VaccinationCenter;
import com.pac.service.VaccinationCenterService;

@Service
public class VaccineCenterServiceImpl implements VaccinationCenterService{
	
	@Autowired
	private VaccineCenterDao vcdao;
	
	@Autowired
	private AdminSessionDao admindao;
	
	@Autowired
	private UserSessionDao userdao;
	

	@Override
	public List<VaccinationCenter> getAllVaccineCenters() throws VaccinationCenterException {

		List<VaccinationCenter> allVaccinationCenters = vcdao.findAll();
		
		if(allVaccinationCenters.isEmpty()) {
			throw new VaccinationCenterException("No Vaccination Center Data Available.....");
		}		
		else
			return allVaccinationCenters;
		
	}

	@Override
	public VaccinationCenter getVaccineCenter(Integer centerCode,String key) throws VaccinationCenterException,LoginException {

		CurrentAdminSession cuurentAdminSession = admindao.findByUniqueUserId(key);
		
		CurrentUserSession currentUserSession = userdao.findByUniqueUserId(key);
		
		if(currentUserSession != null || cuurentAdminSession != null) {
			
		Optional<VaccinationCenter> opt = vcdao.findById(centerCode);
		
			if(opt.isPresent()) {
				VaccinationCenter vc = opt.get();
				return vc;
			}
			else
				throw new VaccinationCenterException("No Vaccination Center Found with Code: "+centerCode);
			
		}
		
		else
			throw new LoginException("Login as a User/Admin first !!!");
		
	}

	@Override
	public VaccinationCenter addVaccinationCenter(VaccinationCenter center,String key) throws VaccinationCenterException,LoginException {

		CurrentAdminSession cuurentAdminSession = admindao.findByUniqueUserId(key);
		
		CurrentUserSession currentUserSession = userdao.findByUniqueUserId(key);
		
		if(currentUserSession != null || cuurentAdminSession != null) {
		
//			Optional<VaccinationCenter> opt = vcdao.findById(center.getCode());
//			
//			if(opt.isPresent()) {
//				throw new VaccinationCenterException("This Vaccine Center Already Exists");
//			}
//		
//			else {
//				VaccinationCenter addVaccinationCenter = vcdao.save(center);
//
//				return addVaccinationCenter;
//			}
			
			VaccinationCenter addVaccinationCenter = vcdao.save(center);
			
			return addVaccinationCenter;
		
		}
		else
			throw new LoginException("Login as a User/Admin first !!!");
		
	}

	@Override
	public VaccinationCenter updateVaccinationCenter(VaccinationCenter vaccinationCenter,String key) throws VaccinationCenterException,LoginException {

		CurrentAdminSession cuurentAdminSession = admindao.findByUniqueUserId(key);
		
		CurrentUserSession currentUserSession = userdao.findByUniqueUserId(key);
		
		if(currentUserSession != null || cuurentAdminSession != null) {
		
		Optional<VaccinationCenter> opt = vcdao.findById(vaccinationCenter.getCode());
		
		if(opt.isPresent()) {
			VaccinationCenter vc = opt.get();
			return vcdao.save(vc);
		}
		else
			throw new VaccinationCenterException("No Vaccination Center Exisits");
		}
		else
			throw new LoginException("Login as a User/Admin first !!!");
	}

	@Override
	public boolean deleteVaccinationCenter(VaccinationCenter vaccinationCenter,String key) throws VaccinationCenterException,LoginException {

		CurrentAdminSession cuurentAdminSession = admindao.findByUniqueUserId(key);
		
		CurrentUserSession currentUserSession = userdao.findByUniqueUserId(key);
		
		if(currentUserSession != null || cuurentAdminSession != null) {
		
			Optional<VaccinationCenter> opt = vcdao.findById(vaccinationCenter.getCode());
			
			if(opt.isPresent()) {
				VaccinationCenter vc = opt.get();
				 vcdao.delete(vc);
				 return true;
			}
			else {
				throw new VaccinationCenterException("No Vaccination Center Exisits");
			}
		}
		else
			throw new LoginException("Login as a User/Admin first !!!");
		
	}

}
