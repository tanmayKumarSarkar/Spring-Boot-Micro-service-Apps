package com.security.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.security.oauth.entity.AppModule;

@RepositoryRestResource(path ="modules")
public interface AppModuleRepository extends JpaRepository<AppModule, Long> {

}
