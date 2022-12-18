package com.pac.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineInventory {

	@Id
	private Integer inventoryId;
	private LocalDate date;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "vaccineInventory")
	private List<Vaccine> vaccine;
	
	@OneToOne(cascade = CascadeType.ALL)
	private VaccinationCenter vaccinationCenter;
	
	@ManyToOne
	private VaccineCount vaccineCount;
}