package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

	
}
