package com.pac.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pac.model.VaccineRegistration;

public interface VaccineRegistrationDao extends JpaRepository<VaccineRegistration, Long>{

}
