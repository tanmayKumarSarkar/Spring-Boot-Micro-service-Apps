package com.springbootapp.angular.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootapp.angular.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Employee findByName(String name);
}
