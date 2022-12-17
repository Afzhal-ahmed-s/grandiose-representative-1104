package com.pac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pac.excpetion.AdminException;
import com.pac.excpetion.AdminLoginException;
import com.pac.excpetion.MemberException;
import com.pac.excpetion.VaccineRegistrationException;
import com.pac.model.Member;
import com.pac.model.VaccineRegistration;

@Service
public interface VaccineRegistrationService {


	public List<VaccineRegistration> getAllVaccineRegistration() throws VaccineRegistrationException;
	
	public VaccineRegistration getVaccineRegistration(Long mobileNumber,String key) throws VaccineRegistrationException,MemberException,AdminLoginException;

	public Member getAllMember(Long mobileNumber,String key) throws VaccineRegistrationException,AdminLoginException,MemberException,AdminLoginException ;

	public VaccineRegistration addVaccineRegistration(VaccineRegistration registration,String key) throws VaccineRegistrationException,MemberException;
	
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration registration,String key) throws VaccineRegistrationException,MemberException;

	public boolean deleteVaccineRegistration(VaccineRegistration registration,String key) throws VaccineRegistrationException,MemberException;

	
}
