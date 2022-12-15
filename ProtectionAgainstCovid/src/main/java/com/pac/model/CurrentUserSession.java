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
public class CurrentUserSession {

	@Id
	//@Column(unique = true)
	private Integer userId;
	
	private String uniqueUserId;
	private LocalDateTime localDateTime;
	
	public CurrentUserSession(String uniqueUserId, LocalDateTime localDateTime) {
		super();
		this.uniqueUserId = uniqueUserId;
		this.localDateTime = localDateTime;
	}
	
	
}
