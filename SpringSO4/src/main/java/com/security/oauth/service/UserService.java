package com.security.oauth.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.oauth.entity.Permission;
import com.security.oauth.entity.Project;
import com.security.oauth.entity.User;
import com.security.oauth.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUserSOEId(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserSOEId(), user.getPassword(), getAuthority(user));
	}
	
//	private List getAuthority(User user) { 
//		return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
//	}
	
	private Set<GrantedAuthority> getAuthority(User user) {
		Set<GrantedAuthority> auths = new HashSet<>();
		for(Permission permission: user.getPermissions()) {
			auths.add(new SimpleGrantedAuthority("ROLE_"+
				//permission.getProject().getProjectName().toUpperCase() + "_" +
				permission.getAppModule().getAppModuleName().toUpperCase() + "_" + 
				permission.getAccessLevel().getAccessName().toUpperCase())
			);
		}
		System.out.println(auths);
		return auths;
	}
	
	public Set<String> getProject(String userName) {
		User user = userRepo.findByUserSOEId(userName);
		Set<String> projects = new HashSet<>();
		for(Project project: user.getProjects()) {
			projects.add(project.getProjectName());
		}
		return projects;
	}
	
	public Set<GrantedAuthority> getDeailedAuthority(String userName) {
		User user = userRepo.findByUserSOEId(userName);
		Set<GrantedAuthority> auths = new HashSet<>();
		for(Permission permission: user.getPermissions()) {
			auths.add(new SimpleGrantedAuthority("ROLE_"+
				permission.getProject().getProjectName().toUpperCase() + "_" +
				permission.getAppModule().getAppModuleName().toUpperCase() + "_" + 
				permission.getAccessLevel().getAccessName().toUpperCase())
			);
		}
		return auths;
	}

	@PreAuthorize("hasRole('PROJECT_VIEW')")
	public List<User> findAll() {
		//List<User> list = new ArrayList<>();
		//userRepo.findAll().iterator().forEachRemaining(list::add);
		return userRepo.findAll();
	}

	public void delete(long id) {
		userRepo.deleteById(id);
	}

	public User save(User user) {
        return userRepo.save(user);
    }

}
