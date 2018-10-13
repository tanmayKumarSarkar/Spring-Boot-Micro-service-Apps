package com.security.oauth;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import com.security.oauth.entity.AccessLevel;
import com.security.oauth.entity.AppModule;
import com.security.oauth.entity.CustomUserDetails;
import com.security.oauth.entity.Permission;
import com.security.oauth.entity.Project;
import com.security.oauth.entity.User;
import com.security.oauth.repository.UserRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Autowired
	public void authenticationManager (AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {
		if(repo.count() == 0) {
			//createDummyUser(repo);
		}
		builder.userDetailsService(username -> new CustomUserDetails(repo.findByUserSOEId(username)));
	}
	
	public void createDummyUser(UserRepository repo) {
		AccessLevel al1 = new AccessLevel("USER");
		AccessLevel al2 = new AccessLevel("ACTUATOR");
		AccessLevel al3 = new AccessLevel("EDIT");
		
		AppModule am1 = new AppModule("TASK");
		AppModule am2 = new AppModule("LEAVE");
		AppModule am3 = new AppModule("REPORT");
		
		Project p1 = new Project("JASPER");
		Project p2 = new Project("BMX");
		
		Permission per1  = new Permission(p1, am1, al1);
		Permission per2  = new Permission(p1, am1, al2);
		Permission per3  = new Permission(p1, am1, al3);
		Permission per4  = new Permission(p1, am2, al1);
		Permission per5  = new Permission(p1, am2, al2);
		Permission per6  = new Permission(p2, am2, al2);
		Permission per7  = new Permission(p2, am2, al3);
		Permission per8  = new Permission(p2, am3, al2);
		Permission per9  = new Permission(p2, am3, al3);
		
		List<Permission> aList1 = Arrays.asList(per1, per2, per3, per4, per5, per6, per9);
		Set<Permission> permissions1 = new HashSet<>(aList1);
		
		List<Permission> aList2 = Arrays.asList(per1, per4, per5, per6, per7, per8, per9);
		Set<Permission> permissions2 = new HashSet<>(aList2);
		
		//User u1 = repo.save(new User("user1", "password", permissions1));
		//User u2 = repo.save(new User("user2", "password", permissions2));
		repo.save(new User("user", "password", new HashSet<>(
				Arrays.asList(
						new Permission(new Project("JASPER"), new AppModule("LEAVE"), new AccessLevel("USER"))
						))));
	}
	
	/*public void authenticationManager (AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {
		builder.userDetailsService(new UserDetailsService () {
			@Override
			public UserDetails loadUserByUsername (String s) throws UsernameNotFoundException {
				return new CustomUserDetails(repo.findByUserSOEId(s));
			} 
		});
	}*/
}
