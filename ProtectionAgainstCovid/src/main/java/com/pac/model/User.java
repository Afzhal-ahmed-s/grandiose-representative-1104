package com.pac.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Integer userId;
	@NotNull(message = "Name is mandatory")
	private String name;
	@Size(max = 10,min = 10)
	private String mobileNo;
	@NotNull(message = "Password is mandatory")
	private String password;
	@Email(message = "Email is mandatory")
	private String email;
	
//	public User(@NotNull(message = "Name is mandatory") String name, @Size(max = 10, min = 10) String mobileNo,
//			@NotNull(message = "Password is mandatory") String password, @Email String email) {
//		super();
//		this.name = name;
//		this.mobileNo = mobileNo;
//		this.password = password;
//		this.email = email;
//	}
	
	public User(String name,String mobileNo,String password,String email) {
		super();
		this.name = name;
		this.mobileNo = mobileNo;
		this.password = password;
		this.email = email;
	}
	
}
