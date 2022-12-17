package com.pac.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pac.excpetion.VaccineInventoryException;
import com.pac.model.VaccineInventory;

public interface VaccineInventoryRepo extends JpaRepository<VaccineInventory, Integer>{
	
	public List<VaccineInventory> findBydate(LocalDate date);
	
	public VaccineInventory  findByCode(Integer code);
}
