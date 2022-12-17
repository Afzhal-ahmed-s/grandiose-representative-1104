package com.pac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pac.excpetion.AdminLoginException;
import com.pac.excpetion.AppointmentException;
import com.pac.excpetion.LoginException;
import com.pac.excpetion.MemberException;
import com.pac.excpetion.VaccinationCenterException;
import com.pac.excpetion.VaccineRegistrationException;
import com.pac.model.Appointment;


@Service
public interface AppointmentService {

	public List<Appointment> getAllAppointment(String Id) throws AppointmentException,LoginException;

	public Appointment getAppointmentByBookingId(Long bookingId,String key)throws AppointmentException,AdminLoginException;
	
	public Appointment addAppointment(Appointment app,Integer id,String key) throws MemberException,AppointmentException,LoginException,VaccineRegistrationException,VaccinationCenterException,AdminLoginException;

	public Appointment updateAppointment(Appointment app,String key)throws AppointmentException,MemberException,AdminLoginException;

	public boolean deleteAppointment(Long bookingId,String key)throws AppointmentException,MemberException,AdminLoginException;
	
	
	
}
