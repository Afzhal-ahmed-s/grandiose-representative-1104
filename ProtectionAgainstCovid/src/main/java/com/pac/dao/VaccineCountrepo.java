package com.pac.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pac.model.VaccineCount;

public interface VaccineCountrepo extends JpaRepository<VaccineCount, Integer> {

}