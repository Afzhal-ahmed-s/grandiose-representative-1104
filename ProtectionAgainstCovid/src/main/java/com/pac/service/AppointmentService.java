package com.pac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pac.excpetion.AdminLoginException;
import com.pac.excpetion.AppointmentException;
import com.pac.excpetion.MemberException;
import com.pac.excpetion.VaccineRegistrationException;
import com.pac.model.Appointment;


@Service
public interface AppointmentService {

	public List<Appointment> getAllAppointment(Integer Id) throws AppointmentException,AdminLoginException;

	public Appointment getAppointmentByBookingId(Long bookingId,Integer AdminId)throws AppointmentException,AdminLoginException;
	
	public Appointment addAppointmentTest(Appointment app,Integer memId) throws MemberException,AppointmentException;

	public Appointment updateAppointment(Appointment app,Integer memId)throws AppointmentException,MemberException;

	public boolean deleteAppointment(Long bookingId,Integer memId)throws AppointmentException,MemberException;
	
	
	
}
