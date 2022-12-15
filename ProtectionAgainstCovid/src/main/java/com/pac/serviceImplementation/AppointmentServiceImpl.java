package com.pac.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.pac.dao.AppointmentDao;
import com.pac.excpetion.AppointmentException;
import com.pac.excpetion.VaccineRegistrationException;
import com.pac.model.Appointment;
import com.pac.model.VaccineRegistration;
import com.pac.service.AppointmentService;
import com.pac.service.VaccineRegistrationService;

public class AppointmentServiceImpl implements AppointmentService{
	
	@Autowired
	private AppointmentDao appointmentDao;
	
//	@Autowired
//	private AdminLoginDao adminLoginSessionDao ;
//
//	@Autowired
//	private MemberService memberService;
//
//	@Autowired
//	private VaccinationCenterService vaccinationCenterService;
	
	
	
	@Override
	public List<Appointment> getAllAppointment() throws AppointmentException {
		 //Log in first
			
		List<Appointment> appointments = appointmentDao.findAll();
		if (appointments.size() > 0)
			return appointments;
		else
			throw new AppointmentException("No appointment found");
	}

	@Override
	public Appointment getAppointmentByBookingId(Long bookingId) throws AppointmentException {
		
			
		Optional<Appointment> opt =  appointmentDao.findById(bookingId) ;
		if(opt.isPresent())
			return opt.get();
		else
			throw new AppointmentException("Appointment not found by same booking id!");
				
	}
	
	@Override
	public Appointment addAppointmentTest(Appointment app) {
		
		// svae other relationships also
          
		Appointment add =  appointmentDao.save(app) ;
		
	
//		add.setVaccinationCenter(app.getVaccinationCenter());
//		add.setMember(add.getMember());
//		add.setSlot(app.getSlot());
		
		return add;
	}
	
	

	@Override
	public Appointment updateAppointment(Appointment app) throws AppointmentException {
		
		Optional<Appointment> opt= appointmentDao.findById(app.getMobileNo());
        
        if(!opt.isPresent())
        {
        	throw new AppointmentException("Vaccine registration doesn't exist..");
        }
        
        Appointment update =  appointmentDao.save(app);
        
//      update.setVaccinationCenter(app.getVaccinationCenter());
//		update.setMember(add.getMember());
//		update.setSlot(app.getSlot());
        
		return appointmentDao.save(update);
	}

	@Override
	public boolean deleteAppointment(Long bookingId) throws AppointmentException {
				
		Appointment delete = appointmentDao.findById(bookingId)
				.orElseThrow(() -> new AppointmentException("Appointment not found!"));
		appointmentDao.delete(delete);
		return true;
	}

	

}
