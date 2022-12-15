package com.pac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pac.excpetion.VaccineRegistrationException;
import com.pac.model.Member;
import com.pac.model.VaccineRegistration;

@Service
public interface VaccineRegistrationService {


	public VaccineRegistration getVaccineRegistration(Long mobileNumber) throws VaccineRegistrationException;

	public Member getAllMember(Long mobileNumber) throws VaccineRegistrationException ;

	public VaccineRegistration addVaccineRegistration(VaccineRegistration registration) throws VaccineRegistrationException;
	
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration registration) throws VaccineRegistrationException;

	public boolean deleteVaccineRegistration(VaccineRegistration registration) throws VaccineRegistrationException;

	
}
