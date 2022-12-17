package com.pac.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pac.model.Appointment;


@Repository
public interface AppointmentDao extends JpaRepository<Appointment, Long>{

	
	Optional<Appointment> findByBookingId(Long bookingId);
	
}
