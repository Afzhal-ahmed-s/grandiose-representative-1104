package com.pac.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.AdminSessionDao;
import com.pac.dao.UserSessionDao;
import com.pac.dao.VaccineDao;
import com.pac.exception.LoginException;
import com.pac.exception.VaccineException;
import com.pac.model.CurrentAdminSession;
import com.pac.model.CurrentUserSession;
import com.pac.model.Vaccine;
import com.pac.service.VaccineService;

@Service
public class VaccineServiceImpl implements VaccineService {

	@Autowired
	private VaccineDao vaccineDao;

	@Autowired
	private AdminSessionDao adminRepo;

	@Autowired
	private UserSessionDao userRepo;

	@Override
	public List<Vaccine> allVaccines() throws VaccineException {
		List<Vaccine> vaccines = vaccineDao.findAll();
		if (vaccines.size() != 0) {
			return vaccines;
		} else {
			throw new VaccineException("Vaccine not found");
		}
	}

	@Override
	public List<Vaccine> getVaccineByName(String vaccineName, String key) throws VaccineException, LoginException {

		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

		CurrentUserSession currentSessionUser = userRepo.findByuuid(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {

			List<Vaccine> vaccines = vaccineDao.findVaccineByName(vaccineName, key);
			if (vaccines.size() != 0) {
				return vaccines;
			} else {
				throw new VaccineException("Vaccine not found with vaccine name - " + vaccineName);
			}
		} else
			throw new LoginException("Oops...! Login as a user/Admin first.");
	}

	@Override
	public Vaccine getVaccineById(Integer vaccineId, String key) throws VaccineException, LoginException {

		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

		CurrentUserSession currentSessionUser = userRepo.findByuuid(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {

			Optional<Vaccine> opt = vaccineDao.findById(vaccineId);
			if (opt.isPresent()) {
				Vaccine vaccine = opt.get();
				return vaccine;
			} else {
				throw new VaccineException("Vaccine not found with vaccine id - " + vaccineId);
			}
		} else
			throw new LoginException("Oops...! Login as a user/Admin first.");
	}

	@Override
	public Vaccine addVaccine(Vaccine vaccine, String key) throws VaccineException, LoginException {
		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

		CurrentUserSession currentSessionUser = userRepo.findByuuid(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {

			Vaccine vacc = vaccineDao.save(vaccine);
			if (vacc != null) {
				return vacc;
			} else {
				throw new VaccineException("Vaccine not added");
			}
		} else
			throw new LoginException("Oops...! Login as a user/Admin first.");

	}

	@Override
	public Vaccine updateVaccine(Vaccine vaccine, String key) throws VaccineException, LoginException {

		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

		CurrentUserSession currentSessionUser = userRepo.findByuuid(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {

			Optional<Vaccine> opt = vaccineDao.findById(vaccine.getVaccineId());
			if (opt.isPresent()) {
				Vaccine updatedVaccine = vaccineDao.save(vaccine);
				return updatedVaccine;
			} else {
				throw new VaccineException("Vaccine not found ");
			}
		} else
			throw new LoginException("Oops...! Login as a user/Admin first.");

	}

	@Override
	public Boolean deleteVaccine(Integer vaccineId, String key) throws VaccineException, LoginException {

		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

		CurrentUserSession currentSessionUser = userRepo.findByuuid(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {

			boolean ans = false;
			Optional<Vaccine> opt = vaccineDao.findById(vaccineId);
			if (opt.isPresent()) {
				Vaccine v = opt.get();
				if (v.getVaccineCount().getQuantity() == 0) {
					vaccineDao.delete(v);
					ans = true;

				}

			} else {
				throw new VaccineException("Vaccine not found ");
			}
			return ans;
		} else
			throw new LoginException("Oops...! Login as a user/Admin first.");
	}

}
