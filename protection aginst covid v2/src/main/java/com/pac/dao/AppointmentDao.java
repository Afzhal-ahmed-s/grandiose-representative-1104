package com.pac.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pac.model.Appointment;

@Repository
public interface AppointmentDao extends JpaRepository<Appointment, Integer> {
	
//	@Query("select u from Appointment  u where  u.booking_id=:n")
//    public Appointment findByBooking_id(@Param("n") Long id);

}
