package com.security.oauth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
  
   public boolean hasProjectAccess(int parameter) {
       return parameter == 1;
   }
   
   public boolean hasAnyRoleEndsWith (UserDetails user, String rolePrefix) {
	   return true;
   }
   
 }