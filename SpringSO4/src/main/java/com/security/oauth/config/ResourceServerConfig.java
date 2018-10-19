package com.security.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	private static final String RESOURCE_ID = "resource_id";
	
    private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
    private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
    private static final String SECURED_PATTERN = "/api/secure/users/**";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.anonymous().disable()
		.authorizeRequests().antMatchers(SECURED_PATTERN).authenticated()
		.and().authorizeRequests().antMatchers(HttpMethod.POST, SECURED_PATTERN).access(SECURED_WRITE_SCOPE)
        .anyRequest().access(SECURED_READ_SCOPE)
        .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
		
		/*.anonymous().disable()
		.authorizeRequests().antMatchers(SECURED_PATTERN).authenticated()
		.and().authorizeRequests().antMatchers(SECURED_PATTERN).hasRole("USER")
		.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());*/
		
			/*.anonymous().disable()
			.authorizeRequests()
			.antMatchers("/api/secure/users/**").authenticated()
			.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());*/
	}
	
}
