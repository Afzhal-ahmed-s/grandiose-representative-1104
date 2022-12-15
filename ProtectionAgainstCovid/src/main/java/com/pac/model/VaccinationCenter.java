package com.pac.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class VaccinationCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer code;
	@NotNull(message = "{notnull.mesage}")
	private String centerName;
	@NotNull(message = "{notnull.mesage}")
	private String address;
	@NotNull(message = "{notnull.mesage}")
	private String city;
	@NotNull(message = "{notnull.mesage}")
	private String state;
	@NotNull(message = "{notnull.mesage}")
	private String pincode;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Appointment> appointments;
	
	@OneToOne(cascade = CascadeType.ALL)
	private VaccineInventory vaccineInventory;
}
