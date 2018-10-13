package com.security.oauth.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	private String username;
	private String password;
	private  Collection<? extends GrantedAuthority> authorities;
	
	public CustomUserDetails (User user) {
		this.username = user.getUserSOEId();
		this.password = user.getPassword();
		
		Set<GrantedAuthority> auths = new HashSet<>();
		for(Permission permission: user.getPermissions()) {
			auths.add(new SimpleGrantedAuthority(permission.getAccessLevel().getAccessName().toUpperCase()));
		}
		this.authorities = auths;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
