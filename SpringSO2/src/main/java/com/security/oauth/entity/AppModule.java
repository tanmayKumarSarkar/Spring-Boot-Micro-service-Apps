package com.security.oauth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AppModule {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long appModuleId;
	
	private String appModuleName;
	
	public AppModule() {}

	public AppModule(String appModuleName) {
		super();
		this.appModuleName = appModuleName;
	}

	public Long getAppModuleId() {
		return appModuleId;
	}

	public void setAppModuleId(Long appModuleId) {
		this.appModuleId = appModuleId;
	}

	public String getAppModuleName() {
		return appModuleName;
	}

	public void setAppModuleName(String appModuleName) {
		this.appModuleName = appModuleName;
	}
	
	
}
