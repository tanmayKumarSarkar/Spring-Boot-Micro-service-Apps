package com.security.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.oauth.entity.Project;

//@RepositoryRestResource(path ="projects")
public interface ProjectRepository extends JpaRepository<Project, Long> {

	Project findByProjectNameIgnoreCaseContaining(String name);
}
