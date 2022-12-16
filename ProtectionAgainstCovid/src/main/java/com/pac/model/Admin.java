package com.pac.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@Entity
@NoArgsConstructor
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;
	@NotNull(message = "{notnull.mesage}")
	@NotEmpty
	private String name;
	@NotNull(message = "{notnull.mesage}")
	@Email(message = "Please enter valid email")
	private String email;
	@NotNull(message = "{notnull.mesage}")
	@NotEmpty
	private String password;
	@Size(max = 10,min = 10)
	private String mobileNo;
	
	public Admin(@NotNull(message = "{notnull.mesage}") @NotEmpty @NotEmpty String name,
			@NotNull(message = "{notnull.mesage}") @Email(message = "Please enter valid email") String email,
			@NotNull(message = "{notnull.mesage}") @NotEmpty String password,
			@Size(max = 10, min = 10) String mobileNo) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileNo = mobileNo;
	}

//	public Admin(@NotNull(message = "{notnull.mesage}") @NotEmpty @NotEmpty String name,
//			@NotNull(message = "{notnull.mesage}") @Email(message = "Please enter valid email") String email,
//			@NotNull(message = "{notnull.mesage}") @NotEmpty String password,
//			@Size(max = 10, min = 10) String mobileNo) {
//		super();
//		this.name = name;
//		this.email = email;
//		this.password = password;
//		this.mobileNo = mobileNo;
//	}

//public Admin(String name, String email, String password, String mobileNo) {
//	super();
//	this.name = name;
//	this.email = email;
//	this.password = password;
//	this.mobileNo = mobileNo;
//}

	
}
