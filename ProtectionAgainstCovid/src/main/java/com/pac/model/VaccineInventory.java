package com.pac.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineInventory {

	private LocalDate date;
	
	@OneToOne(cascade = CascadeType.ALL)
	private VaccinationCenter vaccinationCenter;
	
	@Embedded
	private VaccineCount vaccineCount;
}
