package com.security.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.security.oauth.entity.Project;

@RepositoryRestResource(path ="projects")
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
