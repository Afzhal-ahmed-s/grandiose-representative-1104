package com.pac.service;

import java.util.List;

import com.pac.exception.LoginException;
import com.pac.exception.VaccineRegistrationException;
import com.pac.model.Member;
import com.pac.model.VaccineRegistration;

public interface VaccineRegistrationService {


	public VaccineRegistration getVaccineRegistration(Long moblieno, String key)
			throws VaccineRegistrationException, LoginException;

	public List<Member> getAllMember(Long mobileno, String key)
			throws VaccineRegistrationException, LoginException; /* should use memExcp or VrExcp */

	public VaccineRegistration addVaccineRegistration(VaccineRegistration reg, String key)
			throws VaccineRegistrationException, LoginException;

	public VaccineRegistration updateVaccineRegistration(VaccineRegistration reg, String key)
			throws VaccineRegistrationException, LoginException;

	public boolean deleteVaccineRegistration(Integer reg, String key)
			throws VaccineRegistrationException, LoginException;

	
	


}
