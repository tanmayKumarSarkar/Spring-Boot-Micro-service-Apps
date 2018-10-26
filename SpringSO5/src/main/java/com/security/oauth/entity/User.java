package com.security.oauth.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long userId;
	
	private String userFirstName;
	
	private String userLastName;
	
	private String userSOEId;
	
	@JsonIgnore
	private String password;
	
	private String userMail;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Permission> permissions;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Project> projects;
	
	public User() {}

	public User(String userSOEId, String password, Set<Permission> permissions, Set<Project> projects) {
		super();
		this.userSOEId = userSOEId;
		this.password = password;
		this.permissions = permissions;
		this.projects = projects;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserSOEId() {
		return userSOEId;
	}

	public void setUserSOEId(String userSOEId) {
		this.userSOEId = userSOEId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", userSOEId=" + userSOEId + ", password=" + password + ", userMail=" + userMail + ", permissions="
				+ permissions + ", getUserId()=" + getUserId() + ", getUserFirstName()=" + getUserFirstName()
				+ ", getUserLastName()=" + getUserLastName() + ", getUserSOEId()=" + getUserSOEId() + ", getPassword()="
				+ getPassword() + ", getUserMail()=" + getUserMail() + ", getPermissions()=" + getPermissions()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
		
	

}
