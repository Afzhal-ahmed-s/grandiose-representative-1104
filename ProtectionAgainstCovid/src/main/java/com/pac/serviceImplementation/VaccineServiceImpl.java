package com.pac.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.VaccineRepo;
import com.pac.excpetion.VaccineException;
import com.pac.model.Vaccine;
import com.pac.service.VaccineService;

@Service
public class VaccineServiceImpl implements VaccineService{
	@Autowired
	VaccineRepo vr;
	
	
	@Override
	public List<Vaccine> allVaccine() throws VaccineException {
		List<Vaccine> vac = vr.findAll();
		if(vac.size() == 0) {
			throw new VaccineException("No  Present in Database");
		}
		
		return vac;
	}

	@Override
	public List<Vaccine> getVaccineByName(String vaccineName) throws VaccineException {
		List<Vaccine> vac=vr.findByVaccineName(vaccineName);
		if(vac.isEmpty())
			throw new VaccineException("No vaccine found"+vaccineName);
		return vac;
	}

	@Override
	public Vaccine getVaccineById(Integer vaccineId) throws VaccineException {
		Vaccine vc=vr.findById(vaccineId)
				.orElseThrow(() -> new VaccineException("No vaccine Present With this "+vaccineId));
		return vc;
	}

	@Override
	public Vaccine addVaccine(Vaccine vaccine) throws VaccineException {
		Vaccine vc = vr.save(vaccine);
			
			if(vc == null) {
				throw new VaccineException("Failed to Add..");
			}
			
			return vc;
	}

	@Override
	public Vaccine updateVaccine(Vaccine vaccine) throws VaccineException {
		vr.findById(vaccine.getVaccineId())
		.orElseThrow(() -> new VaccineException("No vaccine Present With this "+vaccine.getVaccineId()));
		
		return vr.save(vaccine);
	}

	@Override
	public Boolean deleteVaccine(Vaccine vaccine) throws VaccineException {
		Optional<Vaccine> op = vr.findById(vaccine.getVaccineId());
		if(op.isPresent())
		{
			vr.deleteById(vaccine.getVaccineId());
			return true;
		}
		throw new VaccineException("Unable to Delete");
		
	
	}

}
