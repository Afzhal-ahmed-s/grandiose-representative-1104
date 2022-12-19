package com.pac.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pac.model.Vaccine;

@Repository
public interface VaccineDao extends JpaRepository<Vaccine, Integer> {

	@Query("select v from Vaccine v where v.vaccineName=?1")
	public List<Vaccine> findVaccineByName(String vaccineName, String key);
}
