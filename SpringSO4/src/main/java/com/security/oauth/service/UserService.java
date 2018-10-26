package com.security.oauth.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Service;

import com.security.oauth.entity.Permission;
import com.security.oauth.entity.Project;
import com.security.oauth.entity.User;
import com.security.oauth.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
    private AuthorizationServerTokenServices tokenServices;

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
			auths.add(new SimpleGrantedAuthority(
				permission.getProject().getProjectName().toUpperCase() + "_" +
				permission.getAppModule().getAppModuleName().toUpperCase() + "_" + 
				permission.getAccessLevel().getAccessName().toUpperCase())
			);
		}
		return auths;
	}

	@PreAuthorize("hasAnyRole('USER_VIEW', 'USER_ADMIN')")
	public List<User> findAll(OAuth2Authentication auth) {
		//List<User> list = new ArrayList<>();
		//userRepo.findAll().iterator().forEachRemaining(list::add);
		return userRepo.findAll();
	}
	
	@PreAuthorize("hasAnyRole('USER_VIEW')")
	public Set<User> findAllFiltered(OAuth2Authentication auth) {
		Set<String> projects = getProjectPermissionsByRole(auth, Set.of("USER_VIEW"));
		if(projects.contains("ALL")) {
			return Set.copyOf(userRepo.findAll());
		}
		else return userRepo.findByProjectsProjectNameIn(projects);
	}
	
	@PreAuthorize("hasAnyRole('USER_EDIT')")
	public User findUserByUserName(String userSOEId, OAuth2Authentication auth) {
		return userRepo.findByUserSOEId(userSOEId);
	}

	public void delete(long id, OAuth2Authentication auth) {
		userRepo.deleteById(id);
	}

	public User save(User user, OAuth2Authentication auth) {
        return userRepo.save(user);
    }

	public User update(Long id, User user, OAuth2Authentication auth) {
		return userRepo.save(user);
	}

	@SuppressWarnings("unchecked")
	public Set<String> getProjectPermissionsByRole(OAuth2Authentication auth, Set<String> roles){
		 Set<String> permissions = (Set<String>) tokenServices.getAccessToken(auth).getAdditionalInformation().get("permissions");
		 Set<String> projects = roles.stream()
                 .filter(permissions::contains)
                 .collect(Collectors.toSet());
		 return projects;
	}
}
