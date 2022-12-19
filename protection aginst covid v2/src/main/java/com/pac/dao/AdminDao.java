package com.pac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pac.model.Admin;
import com.pac.model.User;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer>{
	public Admin findByMobileNo(String mobileNo);
}
