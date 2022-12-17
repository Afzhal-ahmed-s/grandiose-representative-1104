package com.pac.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pac.model.Vaccine;

public interface VaccineRepo extends JpaRepository<Vaccine,Integer>{
	public List<Vaccine> findByVaccineName(String vaccineName);
}
