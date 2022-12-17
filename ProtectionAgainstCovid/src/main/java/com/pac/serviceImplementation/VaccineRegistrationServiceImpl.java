package com.pac.serviceImplementation;

import java.util.List;
import java.util.Optional;

import javax.management.BadAttributeValueExpException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.AdminSessionDao;
import com.pac.dao.AppointmentDao;
import com.pac.dao.MemberDao;
import com.pac.dao.UserSessionDao;
import com.pac.dao.VaccineRegistrationDao;
import com.pac.excpetion.AdminLoginException;
import com.pac.excpetion.MemberException;
import com.pac.excpetion.VaccineRegistrationException;
import com.pac.model.CurrentAdminSession;
import com.pac.model.CurrentUserSession;
import com.pac.model.Member;
import com.pac.model.VaccineRegistration;
import com.pac.service.MemberService;
import com.pac.service.VaccinationCenterService;
import com.pac.service.VaccineRegistrationService;

@Service
public class VaccineRegistrationServiceImpl implements VaccineRegistrationService{

	@Autowired
	private AppointmentDao appointmentDao;
	
	@Autowired
	private AdminSessionDao adminDao ;

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private UserSessionDao userSessionDao;
	
	@Autowired
	private VaccinationCenterService vaccinationCenterService;
	
//	@Autowired
//	private VaccineRegistrationService registrationService;
	
	@Autowired
	private VaccineRegistrationDao daoVacRegistration;
	
	

	@Override
	public List<VaccineRegistration> getAllVaccineRegistration() throws VaccineRegistrationException {
		   
		     List<VaccineRegistration>   allRegistration=  daoVacRegistration.findAll();
		     
		       if(allRegistration.size()>=0)
		       {
		    	   throw new VaccineRegistrationException("Registration of  vaccine doesn't exist..");
		       }
		       
		       return allRegistration;
//		return null;
	}
	
	
	@Override
	public VaccineRegistration getVaccineRegistration(Long mobileNumber,String key) throws VaccineRegistrationException,MemberException, AdminLoginException {
		      
		
		CurrentAdminSession currentSessionAdmin = adminDao.findByUniqueUserId(key);
		CurrentUserSession currentSessionUser = userSessionDao.findByUniqueUserId(key);
		
		if(currentSessionAdmin==null && currentSessionUser==null) {
			throw new AdminLoginException("Login first");
		}
        
		Optional<VaccineRegistration> optRegis= daoVacRegistration.findById(mobileNumber);
	    
	    if(optRegis.isPresent())
	    {
	    	  return optRegis.get();
	    }
	    throw new VaccineRegistrationException("Vaccine Registration not found with this Number..");
	    
//	    return null;
	}
	@Override
	public Member getAllMember(Long mobileNumber,String key) throws VaccineRegistrationException,AdminLoginException, MemberException {

		CurrentAdminSession currentSessionAdmin = adminDao.findByUniqueUserId(key);
		CurrentUserSession currentSessionUser = userSessionDao.findByUniqueUserId(key);
		
		if(currentSessionAdmin==null && currentSessionUser==null) {
			throw new AdminLoginException("Login first");
		}
		
		Optional<VaccineRegistration> opt = daoVacRegistration.findById(mobileNumber);
		
		if(opt.isPresent()) {
			VaccineRegistration vg = opt.get();
			Member members = vg.getMembers();
			return members;
		}
		else {
			throw new VaccineRegistrationException("No member");
		}
//	    return null;

	}
	
	@Override
	public VaccineRegistration addVaccineRegistration(VaccineRegistration registration,String key) throws VaccineRegistrationException, MemberException {
		 
		//Login the members first
		
		CurrentUserSession currentSessionUser = userSessionDao.findByUniqueUserId(key);
		
		if( currentSessionUser==null) {
			throw new MemberException("Login first");
		}
        
		
		 VaccineRegistration registraionDone=daoVacRegistration.save(registration);
	    
	     return registraionDone;
//	    return null;

	}
	
	@Override
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration registration, String key) throws VaccineRegistrationException,MemberException {
		 
		CurrentUserSession currentSessionUser = userSessionDao.findByUniqueUserId(key);
		
		if( currentSessionUser==null) {
			throw new MemberException("Login first");
		}
		Optional<VaccineRegistration> optRegis= daoVacRegistration.findById(registration.getMobileNo());
        
        if(!optRegis.isPresent())
        {
        	throw new VaccineRegistrationException("Vaccine registration doesn't exist..");
        }
        
        daoVacRegistration.save(registration);
        return optRegis.get();
//	    return null;
       
	}
	
	@Override
	public boolean deleteVaccineRegistration(VaccineRegistration registration,String key) throws VaccineRegistrationException,MemberException{
		     

		CurrentUserSession currentSessionUser = userSessionDao.findByUniqueUserId(key);
		
		if( currentSessionUser==null) {
			throw new MemberException("Login first");
		}
		
		Optional<VaccineRegistration> optRegisDelete= daoVacRegistration.findById(registration.getMobileNo());
        
        if(!optRegisDelete.isPresent())
        {
      	  throw new VaccineRegistrationException("Vaccine registration can not delete because its not exist..");
      	  
        }
        
        daoVacRegistration.delete(registration);
        
        return true;
    
	}

}
