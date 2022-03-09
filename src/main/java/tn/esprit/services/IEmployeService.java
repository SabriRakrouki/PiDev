package tn.esprit.services;

import java.util.List;

import tn.esprit.entities.Complaint;
import tn.esprit.entities.Employee;

public interface IEmployeService {
	public List<Employee> getAllEmploye();
	public Employee FindEmployeeById(int id);
}
