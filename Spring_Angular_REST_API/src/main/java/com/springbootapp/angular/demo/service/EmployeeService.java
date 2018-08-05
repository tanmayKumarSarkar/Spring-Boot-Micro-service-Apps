package com.springbootapp.angular.demo.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootapp.angular.demo.model.Employee;
import com.springbootapp.angular.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;
	
	public Employee save(Employee emp) {
		if(emp.getId() != null && empRepo.existsById(emp.getId())) {
			throw new EntityExistsException("This Entity with ID already exixts");
		}
		return empRepo.save(emp);
	}
	
	public Employee update(Employee emp) {
		if(emp.getId() == null ) {
			throw new EntityNotFoundException("Entity Not Found");
		}
		return empRepo.save(emp);
	}
	
	public List<Employee> findAll() {
		return empRepo.findAll();
	}
	
	public Employee findOne(Integer empid) {
		return empRepo.findById(empid).orElse(null);
	}
	
	public void delete(Integer empid) {
		empRepo.deleteById(empid);
	}
	
	
	
}
