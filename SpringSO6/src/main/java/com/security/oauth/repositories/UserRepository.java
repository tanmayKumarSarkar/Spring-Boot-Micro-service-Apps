package com.security.oauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.oauth.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String usermame);
}
