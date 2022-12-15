package com.pac.serviceImplementation;

import java.util.List;
import java.util.Optional;

import javax.management.BadAttributeValueExpException;

import org.springframework.beans.factory.annotation.Autowired;

import com.pac.dao.VaccineRegistrationDao;
import com.pac.excpetion.VaccineRegistrationException;
import com.pac.model.Member;
import com.pac.model.VaccineRegistration;
import com.pac.service.VaccineRegistrationService;


public class VaccineRegistrationServiceImpl implements VaccineRegistrationService{

	@Autowired
	private VaccineRegistrationDao daoVacRegistration;
	
//	@Autowired
//	private AdminLoginDao daoAdminLogin;
//	
//	@Autowired
//	private MemberLoginDao daoMemberLogin;
	

	
	@Override
	public VaccineRegistration getVaccineRegistration(Long mobileNumber) throws VaccineRegistrationException {
		      
		Optional<VaccineRegistration> optRegis= daoVacRegistration.findById(mobileNumber);
	    
	    if(optRegis.isPresent())
	    {
	    	  return optRegis.get();
	    }
	    throw new VaccineRegistrationException("Vaccine Registration not found with this Number..");
		    
	}
	@Override
	public Member getAllMember(Long mobileNumber) throws VaccineRegistrationException {


		Optional<VaccineRegistration> opt = daoVacRegistration.findById(mobileNumber);
		
		if(opt.isPresent()) {
			VaccineRegistration vg = opt.get();
			Member members = vg.getMembers();
			return members;
		}
		else {
			throw new VaccineRegistrationException("No member");
		}
		   
	}
	
	@Override
	public VaccineRegistration addVaccineRegistration(VaccineRegistration registration) throws VaccineRegistrationException {
		 
		//Login the members first
		
		 VaccineRegistration registraionDone=daoVacRegistration.save(registration);
	    
	     return registraionDone;
		   
	}
	
	@Override
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration registration) throws VaccineRegistrationException {
		            

		Optional<VaccineRegistration> optRegis= daoVacRegistration.findById(registration.getMobileNo());
        
        if(!optRegis.isPresent())
        {
        	throw new VaccineRegistrationException("Vaccine registration doesn't exist..");
        }
        
        daoVacRegistration.save(registration);
        return optRegis.get();
		           
	}
	
	@Override
	public boolean deleteVaccineRegistration(VaccineRegistration registration) throws VaccineRegistrationException {
		     

		Optional<VaccineRegistration> optRegisDelete= daoVacRegistration.findById(registration.getMobileNo());
        
        if(!optRegisDelete.isPresent())
        {
      	  throw new VaccineRegistrationException("Vaccine registration can not delete because its not exist..");
      	  
        }
        
        daoVacRegistration.delete(registration);
        
        return true;
    
	}

}
