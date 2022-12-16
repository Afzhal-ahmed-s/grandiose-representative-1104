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

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Vaccine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vaccineId;
	@NotNull(message = "{notnull.mesage}")
	private String vaccineName;
	@NotNull(message = "{notnull.mesage}")
	private String description;;

	@OneToOne(cascade = CascadeType.ALL)
	private Member member;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private VaccineInventory vaccineInventory;
	
	@Embedded
	private VaccineCount vaccineCount;
	
}
