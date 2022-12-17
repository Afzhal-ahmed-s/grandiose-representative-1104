package com.pac.serviceImplementation;

import java.util.List;
import java.util.Optional;

import javax.management.BadAttributeValueExpException;

import org.springframework.beans.factory.annotation.Autowired;

import com.pac.dao.AdminSessionDao;
import com.pac.dao.MemberDao;
import com.pac.dao.VaccineRegistrationDao;
import com.pac.excpetion.AdminLoginException;
import com.pac.excpetion.MemberException;
import com.pac.excpetion.VaccineRegistrationException;
import com.pac.model.CurrentAdminSession;
import com.pac.model.Member;
import com.pac.model.VaccineRegistration;
import com.pac.service.VaccineRegistrationService;


public class VaccineRegistrationServiceImpl implements VaccineRegistrationService{

	@Autowired
	private VaccineRegistrationDao daoVacRegistration;
	
	@Autowired
	private AdminSessionDao adminDao ;
//
	@Autowired
	private MemberDao memberDao;
	

	@Override
	public List<VaccineRegistration> getAllVaccineRegistration() throws VaccineRegistrationException {
		   
		     List<VaccineRegistration>   allRegistration=  daoVacRegistration.findAll();
		     
		       if(allRegistration.size()>=0)
		       {
		    	   throw new VaccineRegistrationException("Registration of  vaccine doesn't exist..");
		       }
		       
		       return allRegistration;
	}
	
	
	@Override
	public VaccineRegistration getVaccineRegistration(Long mobileNumber,Integer memberId) throws VaccineRegistrationException,MemberException {
		      
		
		Optional<Member> member = memberDao.findById(memberId);
		
        if(!member.isPresent()) {
        	throw new MemberException("Member not found");
        }
        
		Optional<VaccineRegistration> optRegis= daoVacRegistration.findById(mobileNumber);
	    
	    if(optRegis.isPresent())
	    {
	    	  return optRegis.get();
	    }
	    throw new VaccineRegistrationException("Vaccine Registration not found with this Number..");
		    
	}
	@Override
	public Member getAllMember(Long mobileNumber,Integer adminId) throws VaccineRegistrationException,AdminLoginException {

		Optional<CurrentAdminSession> adminSession = adminDao.findById(adminId);
		if(!adminSession.isPresent()) {
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
		   
	}
	
	@Override
	public VaccineRegistration addVaccineRegistration(VaccineRegistration registration,Integer memberId) throws VaccineRegistrationException, MemberException {
		 
		//Login the members first
		
		Optional<Member> member = memberDao.findById(memberId);
		
        if(!member.isPresent()) {
        	throw new MemberException("Member not found");
        }
        
		
		 VaccineRegistration registraionDone=daoVacRegistration.save(registration);
	    
	     return registraionDone;
		   
	}
	
	@Override
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration registration, Integer memberId) throws VaccineRegistrationException,MemberException {
		 
		Optional<Member> member = memberDao.findById(memberId);
		
        if(!member.isPresent()) {
        	throw new MemberException("Member not found");
        }

		Optional<VaccineRegistration> optRegis= daoVacRegistration.findById(registration.getMobileNo());
        
        if(!optRegis.isPresent())
        {
        	throw new VaccineRegistrationException("Vaccine registration doesn't exist..");
        }
        
        daoVacRegistration.save(registration);
        return optRegis.get();
		           
	}
	
	@Override
	public boolean deleteVaccineRegistration(VaccineRegistration registration,Integer memberId) throws VaccineRegistrationException,MemberException{
		     

		Optional<Member> member = memberDao.findById(memberId);
		
        if(!member.isPresent()) {
        	throw new MemberException("Member not found");
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
