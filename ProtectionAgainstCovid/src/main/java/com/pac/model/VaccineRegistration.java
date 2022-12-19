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
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VaccineRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer registrationNo;
	
	private Long mobileNo;
	@NotNull(message = "{notnull.mesage}")
	private LocalDate dateOfRegsitration;

	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JsonIgnore
//	private List<Member>members;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Member members;



}
