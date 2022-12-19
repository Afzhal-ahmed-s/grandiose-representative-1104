package com.pac.service;

import java.util.List;

import com.pac.exception.AppointmentException;
import com.pac.exception.UserException;
import com.pac.model.Appointment;

public interface AppointmentService {

	public List<Appointment> getAllAppoinments() throws AppointmentException;

	public Appointment getAppoinment(Integer bookingId, String key) throws AppointmentException, UserException;

	public Appointment addAppoinment(Appointment app, String key) throws AppointmentException, UserException;

	public Appointment updateAppoinment(Appointment app, String key) throws AppointmentException, UserException;

	public boolean deleteAppoinment(Appointment app, String key) throws AppointmentException, UserException;

}
