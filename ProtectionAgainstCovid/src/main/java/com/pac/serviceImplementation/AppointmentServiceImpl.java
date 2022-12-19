package com.pac.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.AdminSessionDao;
import com.pac.dao.AppointmentDao;
import com.pac.dao.MemberDao;
import com.pac.dao.UserSessionDao;
import com.pac.excpetion.AdminLoginException;
import com.pac.excpetion.AppointmentException;
import com.pac.excpetion.LoginException;
import com.pac.excpetion.MemberException;
import com.pac.excpetion.VaccinationCenterException;
import com.pac.excpetion.VaccineRegistrationException;
import com.pac.model.Appointment;
import com.pac.model.CurrentAdminSession;
import com.pac.model.CurrentUserSession;
import com.pac.service.AppointmentService;
import com.pac.service.MemberService;
import com.pac.service.VaccinationCenterService;
import com.pac.service.VaccineRegistrationService;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	
	@Autowired
	private AppointmentDao appointmentDao;
	
	@Autowired
	private AdminSessionDao adminDao ;
//
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private UserSessionDao userSessionDao;
	
//
	@Autowired
	private VaccinationCenterService vaccinationCenterService;
	
	@Autowired
	private VaccineRegistrationService registrationService;
	
	
	
	@Override
	public List<Appointment> getAllAppointment(String Id) throws AppointmentException, LoginException {
		 //Log in first
		
		CurrentAdminSession currentSessionAdmin = adminDao.findByUniqueUserId(Id);
		CurrentUserSession currentSessionUser = userSessionDao.findByUniqueUserId(Id);
		
		if (currentSessionAdmin != null || currentSessionUser != null) {

			List<Appointment> appointments = appointmentDao.findAll();
			if (appointments.size() > 0)
				return appointments;
			else
				throw new AppointmentException("No appointment found");
		} 
		else throw new LoginException("Your'e not logged in. Log in as an admin (or) user to access this feature.");
		
			
	}

	@Override
	public Appointment getAppointmentByBookingId(Long bookingId,String AdminId) throws AppointmentException,AdminLoginException {
		CurrentAdminSession currentSessionAdmin = adminDao.findByUniqueUserId(AdminId);
		CurrentUserSession currentSessionUser = userSessionDao.findByUniqueUserId(AdminId);
		
		if(currentSessionAdmin==null && currentSessionUser==null) {
			throw new AdminLoginException("Login first");
		}
		
			
		Optional<Appointment> opt =  appointmentDao.findById(bookingId) ;
		if(opt.isPresent())
			return opt.get();
		else
			throw new AppointmentException("Appointment not found by same booking id!");
				
	}
	
	@Override
	public Appointment addAppointment(Appointment app,Integer memId,String key) throws MemberException,AppointmentException, LoginException, VaccineRegistrationException, VaccinationCenterException, AdminLoginException {
		
		// svae other relationships also
		
		CurrentUserSession currentUser = userSessionDao.findByUniqueUserId(key);

		if (currentUser != null) {
			Appointment appointment = appointmentDao.save(app);
			if (appointment != null) {
				return appointment;
			} else {
				throw new AppointmentException("Appointment not Scheduled! Please try after some time!");
			}
		} else
			throw new LoginException("Opps...!!! Login as a user first.");
	}
	
	

	@Override
	public Appointment updateAppointment(Appointment app,String key) throws AppointmentException,MemberException, AdminLoginException {
		
		CurrentUserSession currentUser = userSessionDao.findByUniqueUserId(key);

		if (currentUser != null) {

			Optional<Appointment> appointment = appointmentDao.findById(app.getBookingId());

			if (appointment.isPresent()) {

				appointmentDao.save(app);

				return appointment.get();
			} else {
				throw new AppointmentException("No Appointment found!");
			}
		} else
			throw new AdminLoginException("Opps...!!! Login as a user first.");
	}

	@Override
	public boolean deleteAppointment(Long bookingId,String key) throws AppointmentException,MemberException, AdminLoginException{
		
		CurrentAdminSession currentSessionAdmin = adminDao.findByUniqueUserId(key);
		CurrentUserSession currentSessionUser = userSessionDao.findByUniqueUserId(key);
		
		if(currentSessionAdmin==null && currentSessionUser==null) {
			throw new AdminLoginException("Login first");
		}
		
		
		Appointment delete = appointmentDao.findById(bookingId)
				.orElseThrow(() -> new AppointmentException("Appointment not found!"));
		appointmentDao.delete(delete);
		return true;
	}

	

}
