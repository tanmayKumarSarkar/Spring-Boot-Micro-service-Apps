package com.security.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.oauth.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
