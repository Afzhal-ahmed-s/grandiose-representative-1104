package com.pac.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pac.enums.Slot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookingId;
	
//	@Size(min = 9  , max = 10 , message = "Please Enter valid mobile no ")
//	@NotNull(message = "Mobile number is mandatory")
//	@Pattern(regexp =  "^[7-9][0-9]9$")
//	@NotEmpty(message = "Mobile number is mandatory")
	@Column(unique = true)
	private Long mobileNo;
	
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dateOfBooking;
	
	private Boolean bookingstats;
	
	private Slot slot;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private Member member;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private VaccinationCenter vaccinationCenter;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Member member;

}
