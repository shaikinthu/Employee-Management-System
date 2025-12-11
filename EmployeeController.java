package com.example.jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.entity.Employee;
import com.example.jpa.service.EmployeeService;



@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/list")
	public String listEmployee(Model model) {
		List<Employee> emps=employeeService.findAll();
		model.addAttribute("employees",emps);
		return "list-emps";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Employee employee=new Employee();
		theModel.addAttribute("employee",employee);
		return "employee-form";
	}
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
	employeeService.save(employee);
	return "redirect:/list";
	}
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,Model theModel) {
		Optional<Employee> theEmployee=employeeService.findById(theId);
		theModel.addAttribute("employee",theEmployee);
		return "employee-form";
	}
	
    @GetMapping("/delete")
     public String delete(@RequestParam("employeeId")int theId) {
    	employeeService.deleteById(theId);
    	return "redirect:/list";
    }
	

}
