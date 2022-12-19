package com.pac.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.AdminSessionDao;
import com.pac.dao.UserSessionDao;
import com.pac.dao.VaccinationCenterDao;
import com.pac.exception.LoginException;
import com.pac.exception.VaccineCenterException;
import com.pac.model.CurrentAdminSession;
import com.pac.model.CurrentUserSession;
import com.pac.model.VaccinationCenter;
import com.pac.service.VaccinationCenterService;

@Service
public class VaccinationCenterServiceImpl implements VaccinationCenterService {

	@Autowired
	private VaccinationCenterDao vacenRepo;

	@Autowired
	private AdminSessionDao adminRepo;

	@Autowired
	private UserSessionDao userRepo;

	@Override
	public List<VaccinationCenter> getAllVaccineCenters() throws VaccineCenterException {

		List<VaccinationCenter> allvaccenters = vacenRepo.findAll();

		if (allvaccenters.size() == 0) {

			throw new VaccineCenterException("List is Empty , no Vaccinaton Center found");
		}

		return allvaccenters;

	}

	@Override
	public VaccinationCenter getVaccineCenter(Integer centerId, String key)
			throws VaccineCenterException, LoginException {

		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

		CurrentUserSession currentSessionUser = userRepo.findByuuid(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {

			Optional<VaccinationCenter> opt = vacenRepo.findById(centerId);

			if (opt.isPresent()) {

				VaccinationCenter vc = opt.get();

				return vc;

			} else
				throw new VaccineCenterException("Vaccination Center does not found with center id :" + centerId);
		} else
			throw new LoginException("Oops...! Login as a user/Admin first.");

	}

	@Override
	public VaccinationCenter addVaccinationCenter(VaccinationCenter center, String key)
			throws VaccineCenterException, LoginException {

		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

		CurrentUserSession currentSessionUser = userRepo.findByuuid(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {

			Optional<VaccinationCenter> opt = vacenRepo.findById(center.getCode());

			if (opt.isPresent()) {

				throw new VaccineCenterException("sorry this Vaccination center already exist");
			} else
				return vacenRepo.save(center);
		} else
			throw new LoginException("Oops...! Login as a user/Admin first.");

	}

	@Override
	public VaccinationCenter updateVaccinationCenter(VaccinationCenter center, String key)
			throws VaccineCenterException, LoginException {

		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

		CurrentUserSession currentSessionUser = userRepo.findByuuid(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {

			Optional<VaccinationCenter> opt = vacenRepo.findById(center.getCode());

			if (opt.isPresent()) {

				VaccinationCenter vc = opt.get();

				return vacenRepo.save(vc);
			} else
				throw new VaccineCenterException("Vaccination Center does not exist to update");
		} else {
			throw new LoginException("Oops...! Login as a user/Admin first.");
		}

	}

	@Override
	public boolean deleteVaccinationCenter(VaccinationCenter center, String key)
			throws VaccineCenterException, LoginException {

		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

		CurrentUserSession currentSessionUser = userRepo.findByuuid(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {

			Optional<VaccinationCenter> opt = vacenRepo.findById(center.getCode());

			if (opt.isPresent()) {

				VaccinationCenter vc = opt.get();

				vacenRepo.delete(vc);

				return true;

			} else {
				throw new VaccineCenterException("Vaccination Center cannot be deleted ");
			}
		} else
			throw new LoginException("Oops...! Login as a user/Admin first.");

	}

}
