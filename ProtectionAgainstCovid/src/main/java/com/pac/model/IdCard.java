package com.pac.model;

import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class IdCard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; 
	@NotNull(message = "{notnull.mesage}")
	private String name;
	@NotNull(message = "{notnull.mesage}")
	private LocalDate dob;
	@NotNull(message = "{notnull.mesage}")
	private String gender;
	@NotNull(message = "{notnull.mesage}")
	private String city;
	@NotNull(message = "{notnull.mesage}")
	private String address;
	@NotNull(message = "{notnull.mesage}")
	private String state;
	@NotNull(message = "{notnull.mesage}")
	private String pincode;

	@Embedded
	private AadharCard aadharCard;
	
	@Embedded
	private PanCard panCard;

	
}
