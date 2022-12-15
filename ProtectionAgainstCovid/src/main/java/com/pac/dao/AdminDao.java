package com.pac.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pac.model.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer>{

	public Admin findByMobileNo(String mobileNo);
	
	public List<Admin> findByEmail(String email);

	public List<Admin> findByName(String name);

	
}
