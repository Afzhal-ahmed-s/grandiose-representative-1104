package com.pac.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pac.dao.AdminSessionDao;
import com.pac.dao.VaccinationCenterDao;
import com.pac.dao.VaccineInventoryDao;
import com.pac.dao.VaccineDao;
import com.pac.exception.LoginException;
import com.pac.exception.VaccineException;
import com.pac.model.CurrentAdminSession;
import com.pac.model.VaccinationCenter;
import com.pac.model.Vaccine;
import com.pac.model.VaccineCount;
import com.pac.model.VaccineInventory;
import com.pac.service.VaccineInventoryService;

@Service
public class VaccineInventoryImpl implements VaccineInventoryService {

	@Autowired
	private VaccineInventoryDao viRepo;

	@Autowired
	private VaccineDao vRepo;

	@Autowired
	private AdminSessionDao adminRepo;

	@Autowired
	private VaccinationCenterDao vcRepo;

	@Override
	public VaccineInventory getVaccineInventoryByCenter(Integer Centerid, String key)
			throws VaccineException, LoginException {
		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

		if (currentSessionAdmin != null) {

			VaccinationCenter vc = vcRepo.getVaccineCenterById(Centerid);

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

		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

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

		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

		if (currentSessionAdmin != null) {

			// TODO Auto-generated method stub
			Optional<VaccineInventory> opt = viRepo.findById(vinv.getInventoryId());

			if (opt.isPresent()) {

				VaccineInventory vi = opt.get();

				vi.setDate(vinv.getDate());
				vi.setInventoryId(vinv.getInventoryId());
				vi.setLocation(vinv.getLocation());
				vi.setVaccinationCenters(vinv.getVaccinationCenters());
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

		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

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

		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

		if (currentSessionAdmin != null) {

			List<VaccineInventory> inventorylist = viRepo.findByDate(ld);

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

		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

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

		CurrentAdminSession currentSessionAdmin = adminRepo.findByuuid(key);

		if (currentSessionAdmin != null) {

			return viRepo.save(vaccineInventory);

		} else {
			throw new LoginException("Oops...! Login as admin first.");
		}

	}

}
