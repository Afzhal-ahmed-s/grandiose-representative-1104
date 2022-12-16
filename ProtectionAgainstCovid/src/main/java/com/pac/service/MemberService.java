package com.pac.service;

import java.util.List;

import com.pac.excpetion.LoginException;
import com.pac.excpetion.MemberException;
import com.pac.model.Member;

public interface MemberService {

	
	public List<Member> getAllMembers() throws MemberException;

	public Member getMemberById(Integer memberId, String key) throws MemberException, LoginException;

	public Member getMemberByAadharNo(String aadharNo, String key) throws MemberException, LoginException;

	public Member getMemberByPanNo(String panNo, String key) throws MemberException, LoginException;

	public Member addMember(Member member, String key) throws MemberException, LoginException;

	public Member updateMember(Member member, String key) throws MemberException, LoginException;

	//can also pass memeberId as parameter rather than member
	public Boolean deleteMember(Member member, String key) throws MemberException, LoginException;
}
