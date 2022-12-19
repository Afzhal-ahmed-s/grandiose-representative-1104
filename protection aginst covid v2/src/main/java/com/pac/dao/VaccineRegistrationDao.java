package com.pac.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pac.model.VaccineRegistration;

@Repository
public interface VaccineRegistrationDao extends JpaRepository<VaccineRegistration, Integer> {

	public VaccineRegistration findByMobileno(Long mobileno);

}
