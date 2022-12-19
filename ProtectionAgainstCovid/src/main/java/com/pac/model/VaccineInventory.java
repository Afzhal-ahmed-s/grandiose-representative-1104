package com.pac.model;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VaccineInventory {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer inventoryId;
	private LocalDate date;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "vaccineInventory")
	private List<Vaccine> vaccine;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "vaccineInventory")
	private List<VaccinationCenter >vaccinationCenter;
	
	@Embedded
	private VaccineCount vaccineCount;
}