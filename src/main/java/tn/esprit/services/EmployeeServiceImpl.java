package tn.esprit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.entities.Employee;
import tn.esprit.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService{
	@Autowired
	EmployeeRepository employeeRepository ;
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Override
	public void addEmployee(Employee employee) {
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		employeeRepository.save(employee);
	}

	@Override
	public List<Employee> retrieveAllEmployee() {
		List<Employee> Employees = employeeRepository.findAll();
		return Employees ;
	}

	@Override
	public void deleteEmployee(int id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee); 
	}

}
