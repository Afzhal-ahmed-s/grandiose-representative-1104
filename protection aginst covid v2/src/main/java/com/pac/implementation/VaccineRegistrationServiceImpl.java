package com.pac.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.AdminSessionDao;
import com.pac.dao.MemberDao;
import com.pac.dao.UserSessionDao;
import com.pac.dao.VaccineRegistrationDao;
import com.pac.exception.LoginException;
import com.pac.exception.VaccineRegistrationException;
import com.pac.model.CurrentAdminSession;
import com.pac.model.CurrentUserSession;
import com.pac.model.Member;
import com.pac.model.VaccineRegistration;
import com.pac.service.VaccineRegistrationService;

@Service
public class VaccineRegistrationServiceImpl implements VaccineRegistrationService {

	@Autowired
	private VaccineRegistrationDao vaccineRegistrationDao;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private AdminSessionDao adminRepo;

	@Autowired
	private UserSessionDao userRepo;

	@Override

	public VaccineRegistration getVaccineRegistration(Long moblieno, String key)
			throws VaccineRegistrationException, LoginException {

		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

		CurrentUserSession currentSessionUser = userRepo.findByuuid(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {

			VaccineRegistration vaccineRegistration = vaccineRegistrationDao.findByMobileno(moblieno);
			if (vaccineRegistration != null) {
				return vaccineRegistration;
			} else {
				throw new VaccineRegistrationException("No VaccineRegistration found...");
			}
		} else
			throw new LoginException("Oops...! Login as a user/Admin first.");
	}

	@Override

	public List<Member> getAllMember(Long mobileno, String key) throws VaccineRegistrationException, LoginException {

		List<Member> member = memberDao.findAll();

		if (member.size() == 0) {
			throw new VaccineRegistrationException("No memeber Found");
		} else {
			return member;
		}

	}

	@Override

	public VaccineRegistration addVaccineRegistration(VaccineRegistration regs, String key)
			throws VaccineRegistrationException, LoginException {

		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

		CurrentUserSession currentSessionUser = userRepo.findByuuid(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {

			VaccineRegistration addVaccineRegistration = vaccineRegistrationDao.save(regs);

			return addVaccineRegistration;

		} else
			throw new LoginException("Oops...! Login as a user/Admin first.");
	}

	@Override

	public VaccineRegistration updateVaccineRegistration(VaccineRegistration regs, String key)
			throws VaccineRegistrationException, LoginException {

		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

		CurrentUserSession currentSessionUser = userRepo.findByuuid(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {

			Optional<VaccineRegistration> opt = vaccineRegistrationDao.findById(regs.getRegistrationNo());
			if (opt.isPresent()) {
				VaccineRegistration updatedVaccineRegs = vaccineRegistrationDao.save(regs);
				return updatedVaccineRegs;
			} else {
				throw new VaccineRegistrationException("Invalid VaccineRegistration");
			}
		} else
			throw new LoginException("Oops...! Login as a user/Admin first.");
	}

	@Override

	public boolean deleteVaccineRegistration(Integer regs, String key)
			throws VaccineRegistrationException, LoginException {

		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

		CurrentUserSession currentSessionUser = userRepo.findByuuid(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {

			Optional<VaccineRegistration> opt = vaccineRegistrationDao.findById(regs);
			if (!opt.isPresent()) {
				throw new RuntimeException("not able to access");
			} else {
				VaccineRegistration vaccineRegestration = vaccineRegistrationDao.findById(regs)
						.orElseThrow(() -> new VaccineRegistrationException("Vaccine Registration not Found"));
				vaccineRegistrationDao.delete(vaccineRegestration);
				return true;
			}
		} else
			throw new LoginException("Oops...! Login as a user/Admin first.");
	}

}
