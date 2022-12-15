package com.pac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pac.excpetion.AppointmentException;
import com.pac.excpetion.MemberException;
import com.pac.excpetion.VaccineRegistrationException;
import com.pac.model.Appointment;


@Service
public interface AppointmentService {

	public List<Appointment> getAllAppointment() throws AppointmentException;

	public Appointment getAppointmentByBookingId(Long bookingId)throws AppointmentException;
	
	public Appointment addAppointmentTest(Appointment app) ;

	public Appointment updateAppointment(Appointment app)throws AppointmentException;;

	public boolean deleteAppointment(Long bookingId)throws AppointmentException;
	
	
	
}
