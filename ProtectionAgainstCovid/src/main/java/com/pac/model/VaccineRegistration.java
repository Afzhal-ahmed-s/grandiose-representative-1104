package com.pac.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Getter
public class VaccineRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long mobileNo;
	@NotNull(message = "{notnull.mesage}")
	private LocalDate dateOfRegsitration;

	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JsonIgnore
//	private List<Member>members;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Member members;


	public Long getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}


	public LocalDate getDateOfRegsitration() {
		return dateOfRegsitration;
	}


	public void setDateOfRegsitration(LocalDate dateOfRegsitration) {
		this.dateOfRegsitration = dateOfRegsitration;
	}


	public Member getMembers() {
		return members;
	}


	public void setMembers(Member members) {
		this.members = members;
	}
	
	
}
