package com.pac.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.AppointmentDao;
import com.pac.dao.UserSessionDao;
import com.pac.exception.AppointmentException;
import com.pac.exception.UserException;
import com.pac.model.Appointment;
import com.pac.model.CurrentUserSession;
import com.pac.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	public AppointmentDao appointmentDao;

	@Autowired
	private UserSessionDao userSessionDao;

	@Override
	public List<Appointment> getAllAppoinments() throws AppointmentException {

		List<Appointment> appointments = appointmentDao.findAll();

		if (appointments.size() > 0)
			return appointments;
		else
			throw new AppointmentException("No appointments found");

	}

	@Override
	public Appointment getAppoinment(Integer bookingId, String key) throws AppointmentException, UserException {

		CurrentUserSession currentUser = userSessionDao.findByuuid(key);

		if (currentUser != null) {
			return appointmentDao.findById(bookingId)
					.orElseThrow(() -> new AppointmentException("Appointment not found by booking id :" + bookingId));
		} else
			throw new UserException("Opps...!!! Login as a user first.");
	}

	@Override
	public Appointment addAppoinment(Appointment app, String key) throws AppointmentException, UserException {

		CurrentUserSession currentUser = userSessionDao.findByuuid(key);

		if (currentUser != null) {
			Appointment appointment = appointmentDao.save(app);
			if (appointment != null) {
				return appointment;
			} else {
				throw new AppointmentException("Appointment not Scheduled! Please try after some time!");
			}
		} else
			throw new UserException("Opps...!!! Login as a user first.");
	}

	@Override
	public Appointment updateAppoinment(Appointment app, String key) throws AppointmentException, UserException {

		CurrentUserSession currentUser = userSessionDao.findByuuid(key);

		if (currentUser != null) {

			Optional<Appointment> appointment = appointmentDao.findById(app.getBookingid());

			if (appointment.isPresent()) {

				appointmentDao.save(app);

				return appointment.get();
			} else {
				throw new AppointmentException("No Appointment found!");
			}
		} else
			throw new UserException("Opps...!!! Login as a user first.");
	}

	@Override
	public boolean deleteAppoinment(Appointment app, String key) throws AppointmentException, UserException {

		CurrentUserSession currentUser = userSessionDao.findByuuid(key);

		if (currentUser != null) {
			Appointment appointment = appointmentDao.findById(app.getBookingid())
					.orElseThrow(() -> new AppointmentException("Appointment not found!"));

			appointmentDao.delete(appointment);

			return true;

		} else
			throw new UserException("Opps...!!! Login as a user first.");
	}

}
