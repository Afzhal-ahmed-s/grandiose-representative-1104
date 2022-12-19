package com.pac.service;

import java.util.List;

import com.pac.exception.LoginException;
import com.pac.exception.MemberException;
import com.pac.model.Member;

public interface MemberService {

	public List<Member> getAllMembers() throws MemberException;

	public Member getMemberById(Integer memberId, String key) throws MemberException, LoginException;

	public Member getMemberByAadharNo(Long aadharNo, String key) throws MemberException, LoginException;

	public Member getMemberByPanNo(String panNo, String key) throws MemberException, LoginException;

	public Member addMember(Member member, String key) throws MemberException, LoginException;

	public Member updateMember(Member member, String key) throws MemberException, LoginException;

	public Boolean deleteMember(Member member, String key) throws MemberException, LoginException;

}
