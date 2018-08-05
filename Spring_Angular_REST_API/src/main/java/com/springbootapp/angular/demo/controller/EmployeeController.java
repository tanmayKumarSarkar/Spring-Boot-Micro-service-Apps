package com.springbootapp.angular.demo.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootapp.angular.demo.model.Employee;
import com.springbootapp.angular.demo.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empSrvc;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return empSrvc.findAll();
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp) throws URISyntaxException {
		try {
			Employee newEmp = empSrvc.save(emp);
			return ResponseEntity.created(new URI("/api/employees/"+newEmp.getId())).body(newEmp);
		} catch (Exception e) {
			return new ResponseEntity<Employee>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/employee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp) throws URISyntaxException {
		
		if(emp.getId() == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		try {
			Employee updtEmp = empSrvc.update(emp);
			return ResponseEntity.created(new URI("/api/employee/"+updtEmp.getId())).body(updtEmp);
		} catch (Exception e) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable Integer id) {
		
		if(id == null) {
			return ResponseEntity.notFound().build();
		}
		empSrvc.delete(id);
		String data = "{\"success\": true}";
		return ResponseEntity.ok().body(data);
	}
	

}
