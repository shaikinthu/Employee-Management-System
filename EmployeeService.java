package com.example.jpa.service;

import java.util.List;
import java.util.Optional;

import com.example.jpa.entity.Employee;

public interface EmployeeService {
	
	List<Employee> findAll();
	public Optional<Employee> findById(int id);
	public Employee save(Employee employee);
	public void deleteById(int id);

	

}
