package com.security.oauth.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.oauth.entity.Project;
import com.security.oauth.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserSOEId(String userSOEId);
	Set<User> findByProjectsProjectNameIn(Set<String> projects);
}
