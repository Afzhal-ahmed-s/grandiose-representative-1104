package com.pac.serviceImplementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.AdminDao;
import com.pac.dao.AdminSessionDao;
import com.pac.dao.AppointmentDao;
import com.pac.dao.MemberDao;
import com.pac.dao.UserSessionDao;
//import com.pac.dao.VaccinationCenterRepo;
import com.pac.dao.VaccineCenterDao;
import com.pac.dao.VaccineCountrepo;
import com.pac.dao.VaccineInventoryRepo;
import com.pac.dao.VaccineRepo;
import com.pac.excpetion.LoginException;
import com.pac.excpetion.VaccineException;
import com.pac.excpetion.VaccineInventoryException;
import com.pac.model.CurrentAdminSession;
import com.pac.model.VaccinationCenter;
import com.pac.model.Vaccine;
import com.pac.model.VaccineCount;
import com.pac.model.VaccineInventory;
import com.pac.service.MemberService;
import com.pac.service.VaccinationCenterService;
import com.pac.service.VaccineInventoryService;
import com.pac.service.VaccineRegistrationService;

@Service
public class VaccineInventoryServiceImpl implements VaccineInventoryService {
	@Autowired
	private AppointmentDao appointmentDao;
	
	@Autowired
	private AdminSessionDao adminDao ;
//
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private UserSessionDao userSessionDao;
	
//
	@Autowired
	private VaccineCenterDao vaccinationCenterService;
	
	@Autowired
	private VaccineRegistrationService registrationService;
	
	@Autowired
	private VaccineInventoryRepo viRepo;
	
	
	
	
	@Override
	public VaccineInventory getVaccineInventoryByCenter(Integer Centerid, String key)
			throws VaccineException,LoginException {
		CurrentAdminSession currentSessionAdmin = adminDao.findByUniqueUserId(key);

		if (currentSessionAdmin != null) {

			VaccinationCenter vc = vaccinationCenterService.getVaccineCenterById(Centerid);

			if (vc != null) {
				System.out.println(vc.getCode() + " " + vc.getVaccineInventory());
				return vc.getVaccineInventory();
			} else {
				throw new VaccineException("Enter valid center id");
			}

		} else {
			throw new LoginException("Oops...! Login as admin first.");
		}

	}

	@Override
	public VaccineInventory addVaccineCount(VaccineInventory vinv, Integer count, String key)
			throws VaccineException, LoginException {
		// TODO Auto-generated method stub

		CurrentAdminSession currentSessionAdmin = adminDao.findByUniqueUserId(key);

		if (currentSessionAdmin != null) {

			Optional<VaccineInventory> opt = viRepo.findById(vinv.getInventoryId());

			if (opt.isPresent()) {

				VaccineInventory vi = opt.get();

				if (count >= 0) {

					VaccineCount vcCount = vi.getVaccineCount();

					Integer update = vcCount.getQuantity() + count;

					Double price = vcCount.getPrice();

					vcCount.setQuantity(update);
					vcCount.setPrice(price);

					vi.setVaccineCount(vcCount);

					return viRepo.save(vi);
				} else {
					throw new VaccineException("Count must be greater than 0.");
				}
			} else {
				throw new VaccineException("Vaccine Inventory not found.");
			}
		} else {
			throw new LoginException("Oops...! Login as admin first.");
		}

	}

	@Override
	public VaccineInventory updateVaccineInventory(VaccineInventory vinv, String key)
			throws VaccineException, LoginException {

		CurrentAdminSession currentSessionAdmin = adminDao.findByUniqueUserId(key);

		if (currentSessionAdmin != null) {

			// TODO Auto-generated method stub
			Optional<VaccineInventory> opt = viRepo.findById(vinv.getInventoryId());

			if (opt.isPresent()) {

				VaccineInventory vi = opt.get();

				vi.setDate(vinv.getDate());
				vi.setInventoryId(vinv.getInventoryId());
				vi.setVaccinationCenter(vinv.getVaccinationCenter());
				vi.setVaccine(vinv.getVaccine());
				vi.setVaccineCount(vinv.getVaccineCount());

				return vi;
			} else
				throw new VaccineException("Vaccine inventory not found");
		} else {
			throw new LoginException("Oops...! Login as admin first.");
		}

	}

	@Override
	public boolean deleteVaccineInventory(VaccineInventory vinv, String key) throws VaccineException, LoginException {
		// TODO Auto-generated method stub

		CurrentAdminSession currentSessionAdmin = adminDao.findByUniqueUserId(key);

		if (currentSessionAdmin != null) {

			Optional<VaccineInventory> opt = viRepo.findById(vinv.getInventoryId());

			if (opt != null) {

				VaccineInventory vi = opt.get();

				viRepo.delete(vi);

				return true;
			} else
				return false;

		} else {
			throw new LoginException("Oops...! Login as admin first.");
		}

	}

	@Override
	public List<VaccineInventory> getVaccineInventoryByDate(LocalDate ld, String key)
			throws VaccineException, LoginException {
		// TODO Auto-generated method stub

		CurrentAdminSession currentSessionAdmin = adminDao.findByUniqueUserId(key);;

		if (currentSessionAdmin != null) {

			List<VaccineInventory> inventorylist = viRepo.findBydate(ld);

			if (inventorylist.size() > 0) {

				return inventorylist;
			} else
				throw new VaccineException("Vaccine inventory not found");
		} else {
			throw new LoginException("Oops...! Login as admin first.");
		}
	}

	@Override
	public VaccineInventory getVaccineInventoryByVaccine(Vaccine vc, String key)
			throws VaccineException, LoginException {
		// TODO Auto-generated method stub

		CurrentAdminSession currentSessionAdmin = adminDao.findByUniqueUserId(key);

		if (currentSessionAdmin != null) {
			VaccineInventory inventorylist = viRepo.getVaccineInventoryByVaccine(vc.getVaccineId());

			if (inventorylist != null) {
				return inventorylist;
			} else {
				throw new VaccineException("Vaccine inventory not found vaccine id - " + vc.getVaccineId());
			}
		} else {
			throw new LoginException("Oops...! Login as admin first.");
		}

	}

	@Override
	public VaccineInventory addVaccineInventory(VaccineInventory vaccineInventory, String key)
			throws VaccineException, LoginException {

		CurrentAdminSession currentSessionAdmin = adminDao.findByUniqueUserId(key);

		if (currentSessionAdmin != null) {

			return viRepo.save(vaccineInventory);

		} else {
			throw new LoginException("Oops...! Login as admin first.");
		}

	}


}