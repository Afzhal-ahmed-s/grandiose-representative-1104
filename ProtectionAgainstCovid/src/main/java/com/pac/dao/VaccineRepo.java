package com.pac.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pac.model.Vaccine;

public interface VaccineRepo extends JpaRepository<Vaccine,Integer>{
	public List<Vaccine> findByVaccineName(String vaccineName);
	
	@Query("select v from Vaccine v where v.vaccineName=?1")
	public List<Vaccine> findVaccineByName(String vaccineName, String key);
}
