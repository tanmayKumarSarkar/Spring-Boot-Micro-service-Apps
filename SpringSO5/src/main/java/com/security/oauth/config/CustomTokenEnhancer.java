package com.security.oauth.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.security.oauth.service.UserService;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Autowired
	UserService userService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		User user = (User) authentication.getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<>();
        //return project set with access
        additionalInfo.put("permissions", userService.getDeailedAuthority(user.getUsername()));
        additionalInfo.put("authorities",user.getAuthorities());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
	}

}
