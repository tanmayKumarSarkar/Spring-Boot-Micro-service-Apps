package com.security.oauth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.security.oauth.entity.Project;
import com.security.oauth.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepo;
	
	// AND @securityService.hasProjectAccess(1)
	
	@PreAuthorize("hasAnyRole('PROJECT_VIEW', 'PROJECT_ADMIN')")
	public List<Project> findAll() {
		return projectRepo.findAll();
	}
	
	@PreAuthorize("hasRole('PROJECT_EDIT')")
	public Project findByProjectName(String name) {
		return projectRepo.findByProjectNameIgnoreCaseContaining(name);
	}

	@PreAuthorize("hasRole('EDIT')")
	public Project save(Project project) {
		return projectRepo.save(project);
	}

	@PreAuthorize("hasRole('USER')")
	public void delete(Long id) {
		projectRepo.deleteById(id);	
	}

}
