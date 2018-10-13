package com.security.oauth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccessLevel {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long accessLevelId;
	
	private String accessName;
	
	public AccessLevel() {}

	public AccessLevel(String accessName) {
		super();
		this.accessName = accessName;
	}

	public Long getAccessLevelId() {
		return accessLevelId;
	}

	public void setAccessLevelId(Long accessLevelId) {
		this.accessLevelId = accessLevelId;
	}

	public String getAccessName() {
		return accessName;
	}

	public void setAccessName(String accessName) {
		this.accessName = accessName;
	}
	
	
}
