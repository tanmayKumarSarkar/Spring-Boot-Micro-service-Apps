package com.security.oauth.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.security.oauth.entity.Project;
import com.security.oauth.service.ProjectService;

@RestController
@RequestMapping("/api/secure/projects")
public class ProjectController {

	@Autowired
    private ProjectService projectService;
	
	@Autowired
    private AuthorizationServerTokenServices tokenServices;

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public List<Project> listProject(OAuth2Authentication authentication){
    	System.out.println(tokenServices.getAccessToken(authentication).getAdditionalInformation().get("permissions"));
        return projectService.findAll();
    }
    
    @RequestMapping(value="/{name}", method = RequestMethod.GET)
    public Project findProject(@PathVariable(value = "name") String name){
        return projectService.findByProjectName(name);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Project create(@RequestBody Project project){
        return projectService.save(project);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Long id){
        projectService.delete(id);
        return "success";
    }
    
    @RequestMapping(value = "/info")
    public ResponseEntity<?> someResource(OAuth2Authentication authentication) {
    	Map<String, Object> additionalInfo = tokenServices.getAccessToken(authentication).getAdditionalInformation();

        Set<String> customInfo =  (Set<String>) additionalInfo.get("permissions");
        return ResponseEntity.ok(customInfo);
    }
    
//    public ResponseEntity<?> someResource(Principal principal) {
//    	return ResponseEntity.ok(principal.toString());
//    }
    
}
