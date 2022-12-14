package com.pac.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@Entity
@NoArgsConstructor
public class Admin {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;
	@NotNull(message = "{notnull.mesage}")
	@NotEmpty
	@NotEmpty
	private String name;
	@NotNull(message = "{notnull.mesage}")
	@Email(message = "Please enter valid email")
	private String email;
	@NotNull(message = "{notnull.mesage}")
	@NotEmpty
	private String password;


	
}
