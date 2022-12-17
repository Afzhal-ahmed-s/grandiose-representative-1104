package com.pac.serviceImplementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.AdminDao;
import com.pac.dao.AdminSessionDao;
import com.pac.dao.AppointmentDao;
import com.pac.dao.MemberDao;
import com.pac.excpetion.AdminLoginException;
import com.pac.excpetion.AppointmentException;
import com.pac.excpetion.MemberException;
import com.pac.excpetion.VaccineRegistrationException;
import com.pac.model.Admin;
import com.pac.model.AdminLogin;
import com.pac.model.Appointment;
import com.pac.model.CurrentAdminSession;
import com.pac.model.Member;
import com.pac.model.VaccineRegistration;
import com.pac.service.AppointmentService;
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
//
//	@Autowired
//	private VaccinationCenterService vaccinationCenterService;
	
	
	
	@Override
	public List<Appointment> getAllAppointment(Integer Id) throws AppointmentException,AdminLoginException {
		 //Log in first
		Optional<CurrentAdminSession> adminSession = adminDao.findById(Id);
		if(!adminSession.isPresent()) {
			throw new AdminLoginException("Login first");
		}
		
			
		List<Appointment> appointments = appointmentDao.findAll();
		if (appointments.size() > 0)
			return appointments;
		else
			throw new AppointmentException("No appointment found");
	}

	@Override
	public Appointment getAppointmentByBookingId(Long bookingId,Integer AdminId) throws AppointmentException,AdminLoginException {
		
		Optional<CurrentAdminSession> adminSession = adminDao.findById(AdminId);
		if(!adminSession.isPresent()) {
			throw new AdminLoginException("Login first");
		}
		
			
		Optional<Appointment> opt =  appointmentDao.findById(bookingId) ;
		if(opt.isPresent())
			return opt.get();
		else
			throw new AppointmentException("Appointment not found by same booking id!");
				
	}
	
	@Override
	public Appointment addAppointment(Appointment app,Integer memId) throws MemberException,AppointmentException {
		
		// svae other relationships also
		
		Optional<Member> member = memberDao.findById(memId);
		
        if(!member.isPresent()) {
        	throw new MemberException();
        }
        Appointment add =  appointmentDao.save(app) ;
		
    	
		add.setVaccinationCenter(app.getVaccinationCenter());
		add.setBookingId(app.getBookingId());
		add.setBookingstats(true);
		add.setDateOfBooking(LocalDate.now());
		add.setMobileNo(app.getMobileNo());
		add.setMember(add.getMember());
//		add.setSlot(app.getSlot());
		
		return add;
	}
	
	

	@Override
	public Appointment updateAppointment(Appointment app,Integer memId) throws AppointmentException,MemberException {
		
		Optional<Member> member = memberDao.findById(memId);
		
        if(!member.isPresent()) {
        	throw new MemberException();
        }
       
		Optional<Appointment> opt= appointmentDao.findById(app.getMobileNo());
        
        if(!opt.isPresent())
        {
        	throw new AppointmentException("Vaccine registration doesn't exist..");
        }
        
        Appointment update =  appointmentDao.save(app);
        
        update.setVaccinationCenter(app.getVaccinationCenter());
        update.setBookingId(app.getBookingId());
        update.setBookingstats(true);
        update.setDateOfBooking(LocalDate.now());
        update.setMobileNo(app.getMobileNo());
        update.setMember(app.getMember());
//		update.setSlot(app.getSlot());
        
		return appointmentDao.save(update);
	}

	@Override
	public boolean deleteAppointment(Long bookingId,Integer memId) throws AppointmentException,MemberException{
		
		Optional<Member> member = memberDao.findById(memId);
		
        if(!member.isPresent()) {
        	throw new MemberException("Login first");
        }
       
		
		Appointment delete = appointmentDao.findById(bookingId)
				.orElseThrow(() -> new AppointmentException("Appointment not found!"));
		appointmentDao.delete(delete);
		return true;
	}

	

}
