package com.pac.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CurrentAdminSession {

	@Id
//	@Column(unique = true)
	private Integer adminId;
	private String uniqueUserId;
	private LocalDateTime loginDateAndTime;
	
	public CurrentAdminSession(String uniqueUserId, LocalDateTime loginDateAndTime) {
		super();
		this.uniqueUserId = uniqueUserId;
		this.loginDateAndTime = loginDateAndTime;
	}

	
	
	
	
	
}
