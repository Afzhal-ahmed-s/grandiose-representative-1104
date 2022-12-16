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


	public VaccineRegistration getVaccineRegistration(Long mobileNumber,Integer memberId) throws VaccineRegistrationException,MemberException;

	public Member getAllMember(Long mobileNumber,Integer adminId) throws VaccineRegistrationException,AdminLoginException ;

	public VaccineRegistration addVaccineRegistration(VaccineRegistration registration,Integer memberId) throws VaccineRegistrationException,MemberException;
	
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration registration,Integer memberId) throws VaccineRegistrationException,MemberException;

	public boolean deleteVaccineRegistration(VaccineRegistration registration,Integer memberId) throws VaccineRegistrationException,MemberException;

	
}
