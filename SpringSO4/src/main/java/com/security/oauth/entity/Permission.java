package com.security.oauth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Permission {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long permission_Id;
	
	@OneToOne
	private Project project;
	
	@OneToOne
	private AppModule appModule;
	
	@OneToOne
	private AccessLevel accessLevel;
	
	public Permission() {}

	public Permission(Project project, AppModule appModule, AccessLevel accessLevel) {
		super();
		this.project = project;
		this.appModule = appModule;
		this.accessLevel = accessLevel;
	}

	public Long getPermission_Id() {
		return permission_Id;
	}

	public void setPermission_Id(Long permission_Id) {
		this.permission_Id = permission_Id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public AppModule getAppModule() {
		return appModule;
	}

	public void setAppModule(AppModule appModule) {
		this.appModule = appModule;
	}

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	@Override
	public String toString() {
		return "Permission [permission_Id=" + permission_Id + ", project=" + project + ", appModule=" + appModule
				+ ", accessLevel=" + accessLevel + ", getPermission_Id()=" + getPermission_Id() + ", getProject()="
				+ getProject() + ", getAppModule()=" + getAppModule() + ", getAccessLevel()=" + getAccessLevel()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
}
