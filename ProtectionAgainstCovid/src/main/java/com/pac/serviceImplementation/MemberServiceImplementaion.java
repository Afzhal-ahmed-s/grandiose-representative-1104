package com.pac.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pac.dao.AdminSessionDao;
import com.pac.dao.MemberDao;
import com.pac.dao.UserSessionDao;
import com.pac.excpetion.LoginException;
import com.pac.excpetion.MemberException;
import com.pac.model.CurrentAdminSession;
import com.pac.model.CurrentUserSession;
import com.pac.model.Member;
import com.pac.service.MemberService;

@Service
public class MemberServiceImplementaion implements MemberService{

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private AdminSessionDao adminSessionDao;

	@Autowired
	private UserSessionDao userSessionDao;
	
	@Override
	public List<Member> getAllMembers() throws MemberException {
		List<Member> memberList = memberDao.findAll();

		if (memberList.isEmpty()) {

			throw new MemberException("Memebers do not exist in record.");

		} else {

			return memberList;

		}
	}

	@Override
	public Member getMemberById(Integer memberId, String key) throws MemberException, LoginException {
		CurrentAdminSession currentSessionAdmin = adminSessionDao.findByUniqueUserId(key);

		CurrentUserSession currentSessionUser = userSessionDao.findByUniqueUserId(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {

			Optional<Member> optional = memberDao.findById(memberId);

			if (optional.isPresent()) {

				Member member = optional.get();

				return member;

			} 
			else throw new MemberException("Member not found with Id : " + memberId + ". Please enter proper memberId.");

		} 
		else throw new LoginException("Your'e not logged in. Log in as an admin (or) user to access this feature.");
		
	}

	@Override
	public Member getMemberByAadharNo(String aadharNo, String key) throws MemberException, LoginException {
		CurrentAdminSession currentSessionAdmin = adminSessionDao.findByUniqueUserId(key);

		CurrentUserSession currentSessionUser = userSessionDao.findByUniqueUserId(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {

			Member member = memberDao.getMemeberByAadharNo(aadharNo);

			if (member != null) {

				return member;

			} 
			else throw new MemberException("Member not found with aadhar number : " + aadharNo + ". Please enter proper aadhar number.");

		} 
		else throw new LoginException("Your'e not logged in. Log in as an admin (or) user to access this feature.");
		
	}

	@Override
	public Member getMemberByPanNo(String panNo, String key) throws MemberException, LoginException {
		CurrentAdminSession currentSessionAdmin = adminSessionDao.findByUniqueUserId(key);

		CurrentUserSession currentSessionUser = userSessionDao.findByUniqueUserId(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {

			Member member = memberDao.getMemeberByPanNo(panNo);

			if (member != null) {

				return member;

			} 
			else throw new MemberException("Member not found with PAN number : " + panNo + ". Please enter proper PAN number.");

		} 
		else throw new LoginException("Your'e not logged in. Log in as an admin (or) user to access this feature.");
	}

	@Override
	public Member addMember(Member member, String key) throws MemberException, LoginException {
		

		CurrentAdminSession currentSessionAdmin = adminSessionDao.findByUniqueUserId(key);

		CurrentUserSession currentSessionUser = userSessionDao.findByUniqueUserId(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {

			if (member != null) {
				return memberDao.save(member);
			} 
			else throw new MemberException("Member not found with card ID : " + member.getIdCard().getId() + ". Please enter proper card ID.");

		} else throw new LoginException("Your'e not logged in. Log in as an admin (or) user to access this feature.");
		
	}

	@Override
	public Member updateMember(Member member, String key) throws MemberException, LoginException {
		CurrentAdminSession currentSessionAdmin = adminSessionDao.findByUniqueUserId(key);

		CurrentUserSession currentSessionUser = userSessionDao.findByUniqueUserId(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {
			Optional<Member> existingMember = memberDao.findById(member.getMemeberId());

			if (existingMember.isPresent()) {

				Member certifiedMember= existingMember.get();

				certifiedMember.setDoseOneDate(member.getDoseOneDate());
				certifiedMember.setDoseOneStatus(member.getDoseOneStatus());
				certifiedMember.setDoseTwoDate(member.getDoseTwoDate());
				certifiedMember.setDoseTwoStatus(member.getDoseTwoStatus());

				return memberDao.save(certifiedMember);

		}
			else throw new MemberException("Member not found with card ID : " + member.getIdCard().getId() + ". Please enter proper card ID.");

		}
		else throw new LoginException("Your'e not logged in. Log in as an admin (or) user to access this feature.");
	}

	@Override
	public Boolean deleteMember(Member member, String key) throws MemberException, LoginException {
		CurrentAdminSession currentSessionAdmin = adminSessionDao.findByUniqueUserId(key);

		CurrentUserSession currentSessionUser = userSessionDao.findByUniqueUserId(key);

		if (currentSessionAdmin != null || currentSessionUser != null) {

			Optional<Member> optional = memberDao.findById(member.getMemeberId());

			if (optional.isPresent()) {

				Member currentMember = optional.get();

				if (currentMember.getDoseOneStatus() == true) {

					if (currentMember.getDoseTwoStatus() == true) {

						memberDao.delete(currentMember);

						return true;

					} 
					else throw new MemberException("Member must have to complete dose 2.");
					
					}
				else throw new MemberException("Member must have to complete dose 1 and 2 later.");

				} 
			else throw new MemberException("Member not found with card ID : " + member.getIdCard().getId() + ". Please enter proper card ID.");

			}  
		else throw new LoginException("Your'e not logged in. Log in as an admin (or) user to access this feature.");
		
	}

}
