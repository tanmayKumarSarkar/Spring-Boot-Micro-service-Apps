package com.security.oauth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.security.oauth.entity.User;
import com.security.oauth.service.UserService;

@RestController
@RequestMapping("/api/secure/users")
public class UserController {

	@Autowired
    private UserService userService;

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public List<User> listUser(OAuth2Authentication auth){
        return userService.findAll(auth);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public User create(@RequestBody User user, OAuth2Authentication auth){
        return userService.save(user, auth);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Long id, OAuth2Authentication auth){
        userService.delete(id, auth);
        return "success";
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public User update(@PathVariable(value = "id") Long id, @RequestBody User user, OAuth2Authentication auth){
        return userService.update(id, user, auth);
    }
    
}
