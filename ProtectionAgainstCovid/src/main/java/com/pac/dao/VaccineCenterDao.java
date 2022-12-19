package com.pac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pac.model.VaccinationCenter;

public interface VaccineCenterDao extends JpaRepository<VaccinationCenter, Integer>{

	@Query("select c from VaccinationCenter c where c.centername=?1")
	public VaccinationCenter findVaccinationCenterByName(String centername, String key);

	@Query("select c from VaccinationCenter c where c.code = ?1")
	public VaccinationCenter getVaccineCenterById(Integer vcId);
	
}
