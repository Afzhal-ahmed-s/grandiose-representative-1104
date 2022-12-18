package com.pac.model;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vaccine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vaccineId;
	@NotNull(message = "{notnull.mesage}")
	private String vaccineName;
	@NotNull(message = "{notnull.mesage}")
	private String description;;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Member member;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private VaccineInventory vaccineInventory;
	
	//@Embedded
	//private VaccineCount vaccineCount;
	
}