package com.pac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pac.model.CurrentAdminSession;

@Repository
public interface AdminSessionDao extends JpaRepository<CurrentAdminSession, Integer> {
	public CurrentAdminSession findByuuid(String uuid);
}
