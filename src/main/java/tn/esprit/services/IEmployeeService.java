package tn.esprit.services;

import java.util.List;

import tn.esprit.entities.Employee;

public interface IEmployeeService {
	public void addEmployee(Employee employee);
	public List<Employee> retrieveAllEmployee();
	public void deleteEmployee(int id);
	public Employee updateEmployee(Employee employee);
}
