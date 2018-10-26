package com.security.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.security.oauth.entity.AccessLevel;

@RepositoryRestResource(path ="access")
public interface AccessLevelRepository extends JpaRepository<AccessLevel, Long> {

}
