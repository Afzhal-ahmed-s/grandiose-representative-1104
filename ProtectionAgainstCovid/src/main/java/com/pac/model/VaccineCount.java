package com.pac.model;


import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class VaccineCount{

	private Integer vaccinecountid;

	private Integer quantity;
	private Double price;
	
//	@OneToMany
//	private Vaccine vaccine;
//	
//	private VaccineInventory vaccineInventory;
}