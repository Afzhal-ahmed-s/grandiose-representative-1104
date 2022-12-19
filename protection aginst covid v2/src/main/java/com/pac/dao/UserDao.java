package com.pac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pac.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	public User findByMobileNo(String mobileNo);
}
