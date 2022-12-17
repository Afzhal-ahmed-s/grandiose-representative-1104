package com.pac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pac.excpetion.LoginException;
import com.pac.excpetion.MemberException;
import com.pac.model.Member;
import com.pac.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	//can update the functionality later
	@GetMapping("/getAllMembers")
	public ResponseEntity<List<Member>> getAllMembers() throws MemberException{
		
		List<Member> listOfAllMembers = memberService.getAllMembers();
		return new ResponseEntity<List<Member>>(listOfAllMembers, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getMemberById/{memberId}")
	public ResponseEntity<Member> getMemberById(@PathVariable("memberId") Integer memberId , @RequestParam(required = false) String key) 
			throws MemberException, LoginException {

		Member member = memberService.getMemberById(memberId, key);

		return new ResponseEntity<Member>(member, HttpStatus.OK);

	}
	
	@GetMapping(value = "/getMemberbyAadhar/{aadharNo}")
	public ResponseEntity<Member> getMemberByAadharNo(@PathVariable("aadharNo") String aadharNo,
			@RequestParam(required = false) String key) throws MemberException, LoginException {

		Member member = memberService.getMemberByAadharNo(aadharNo, key);

		return new ResponseEntity<Member>(member, HttpStatus.OK);

	}
	
	@GetMapping(value = "/getMemberByPanNo/{panNo}")
	public ResponseEntity<Member> getMemberByPanNo(@PathVariable("panNo") String panNo, @RequestParam(required = false) String key) 
			throws MemberException, LoginException {

		Member member = memberService.getMemberByPanNo(panNo, key);

		return new ResponseEntity<Member>(member, HttpStatus.OK);

	}

	@PostMapping(value = "/addMember")
	public ResponseEntity<Member> addMember(@RequestBody Member member, @RequestParam(required = false) String key)
			throws MemberException, LoginException {

		Member addedMember = memberService.addMember(member, key);

		return new ResponseEntity<Member>(addedMember, HttpStatus.OK);

	}

	@PutMapping(value = "/updateMember")
	public ResponseEntity<Member> updateMember(@RequestBody Member member, @RequestParam(required = false) String key)
			throws MemberException, LoginException {

		Member updatedMember = memberService.updateMember(member, key);

		return new ResponseEntity<Member>(updatedMember, HttpStatus.OK);

	}

	@DeleteMapping(value = "/deleteMember")
	public ResponseEntity<Boolean> deleteMember(@RequestBody Member member, @RequestParam(required = false) String key)
			throws MemberException, LoginException {

		Boolean ans = memberService.deleteMember(member, key);

		return new ResponseEntity<Boolean>(ans, HttpStatus.OK);

	}
	
	
	
	
	
	
}
