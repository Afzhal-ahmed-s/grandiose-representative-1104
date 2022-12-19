package com.pac.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pac.excpetion.AdminLoginException;
import com.pac.excpetion.AppointmentException;
import com.pac.excpetion.LoginException;
import com.pac.excpetion.MemberException;
import com.pac.excpetion.VaccinationCenterException;
import com.pac.excpetion.VaccineRegistrationException;
import com.pac.model.Appointment;
import com.pac.service.AppointmentService;
import com.pac.service.MemberService;
import com.pac.serviceImplementation.AdminServiceImplementation;

@RestController
@RequestMapping("/Appointment")
public class AppiontmentController {
	
	@Autowired
	AdminServiceImplementation adminDao ;
	
	@Autowired
	AppointmentService appointmentService ;
	
	
    @Autowired
	private MemberService memberservice;

	@GetMapping("/allAppointments/{key}")
	public ResponseEntity<List<Appointment>> getAllAppointments(@PathVariable("key") String key) throws AppointmentException, AdminLoginException, LoginException {
		
		List<Appointment> app = appointmentService.getAllAppointment(key);
		return new ResponseEntity<>(app,HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/addAppointment/{memberId}/{key}")
	public ResponseEntity<Appointment> addAppointmentByMemberId(@Valid @RequestBody Appointment ap ,@PathVariable("memberId") Integer memberId,@PathVariable("key") String key ) throws AppointmentException, MemberException, LoginException, VaccineRegistrationException, VaccinationCenterException, AdminLoginException {
		
		Appointment app = appointmentService.addAppointment(ap, memberId,key);
		return new ResponseEntity<>(app,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAppointment/{bookingId}/{key}")
	public ResponseEntity<Appointment> getAppointment(@PathVariable("bookingId") long bookingId ,@PathVariable("key") String key) throws AppointmentException, AdminLoginException {
		
		Appointment app =  appointmentService.getAppointmentByBookingId(bookingId, key) ;
		
		return new ResponseEntity<>(app,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateAppointment/{key}")
	public ResponseEntity<Appointment> updateAppointment(@Valid @RequestBody Appointment app ,@PathVariable("key") String key ) throws AppointmentException, MemberException, AdminLoginException{
		
		Appointment app2 = appointmentService.updateAppointment(app, key) ;
		
		return new ResponseEntity<>(app2,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteAppointment/{bookingId}/{key}")
	public ResponseEntity<String> deleteAppointment(@PathVariable("bookingId") Long bookingId,@PathVariable("key")String key)throws AppointmentException, MemberException, AdminLoginException{
		
		boolean ans = appointmentService.deleteAppointment(bookingId, key) ;
		
		 return new ResponseEntity<String>("Appointment Deleted succesfully", HttpStatus.GONE) ;
	}
	
}
