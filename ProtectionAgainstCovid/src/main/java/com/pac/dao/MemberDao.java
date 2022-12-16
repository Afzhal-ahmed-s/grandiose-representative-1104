package com.pac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pac.model.Member;

@Repository
public interface MemberDao extends JpaRepository<Member, Integer>{

	@Query("select m from Member m where m.idCard.id=(select i.id from IdCard i where i.aadharCard.aadharNo= ?1)")
	public Member getMemeberByAadharNo(Integer aadharNo);
	
	@Query("select m from Member m where m.idCard.id=(select i.id from IdCard i where i.panCard.panNo= ?1)")
	public Member getMemeberByPanNo(Integer panNo);

}
