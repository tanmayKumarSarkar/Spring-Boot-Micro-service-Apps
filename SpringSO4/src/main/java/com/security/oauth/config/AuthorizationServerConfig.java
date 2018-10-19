package com.security.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	static final String CLIENT_ID = "my_spring_security_client";
	static final String CLIENT_SECRET = "my_spring_security_secret";
	//static final String GRANT_TYPE_PASSOWRD = "password";
	static final String [] GRANT_TYPES = {"password", "authorization_code", "refresh_token", "implicit"};
	static final String[] SCOPES = {"read", "write", "trust"};
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;
    
    @Autowired
    private TokenStore tokenStore;
    
    @Autowired
	private AuthenticationManager authenticationManager;
    
    @Autowired
	private PasswordEncoder passwordEncoder;
    
    @Bean
    public TokenEnhancer tokenEnhancer() {
       return new CustomTokenEnhancer();
    }
    
    /*@Bean
    public DefaultAccessTokenConverter accessTokenConverter() {
        return new DefaultAccessTokenConverter();
    }*/

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.inMemory()
			.withClient(CLIENT_ID)
			.secret(passwordEncoder.encode(CLIENT_SECRET))
			.authorizedGrantTypes(GRANT_TYPES)
			.scopes(SCOPES)
			.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
			.refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.tokenStore(tokenStore)
			.tokenEnhancer(tokenEnhancer())
			.authenticationManager(authenticationManager);
	}
    
    
}
