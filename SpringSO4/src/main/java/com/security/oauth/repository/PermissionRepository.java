package com.security.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.security.oauth.entity.Permission;

@RepositoryRestResource(path ="permissions")
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
