package com.security.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.oauth.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Company findByName(String name);
}
