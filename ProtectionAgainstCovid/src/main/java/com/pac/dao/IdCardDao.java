package com.pac.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pac.model.IdCard;

@Repository
public interface IdCardDao extends JpaRepository<IdCard, Integer>{

	@Query("select id from IdCard id where id.aadharCard.aadharNo =?1")
	public IdCard getIdCardByAadharNo(String aadharNo);
	
	@Query("select id from IdCard id where id.panCard.panNo =?1")
	public IdCard getIdCardByPanNo(String panNo);
	
	
	
}
