package com.pac.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VaccineCount {

	
	private Integer quantity;
	private Double price;
	
//	@OneToMany
//	private Vaccine vaccine;
//	
//	private VaccineInventory vaccineInventory;
}
