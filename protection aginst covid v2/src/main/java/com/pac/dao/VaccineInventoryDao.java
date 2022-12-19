package com.pac.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pac.model.VaccineInventory;

@Repository
public interface VaccineInventoryDao extends JpaRepository<VaccineInventory, Integer> {

	public List<VaccineInventory> findByDate(LocalDate date);
	
	@Query("select vaccineInventory from Vaccine v where v.vaccineId=?1")
	public VaccineInventory getVaccineInventoryByVaccine(Integer vaccineId);
	

}
