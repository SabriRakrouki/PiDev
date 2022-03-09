package tn.esprit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entities.Complaint;
import tn.esprit.entities.Employee;
import tn.esprit.repositories.ComplaintRepository;
import tn.esprit.repositories.EmployeRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {
	@Autowired
	private EmployeRepository employeRepository;
	@Override
	public List<Employee> getAllEmploye() {
		return employeRepository.findAll();
		
	}
	@Override
	public Employee FindEmployeeById(int id) {
Employee e=employeRepository.findById(id);
		return e;
	}
	

}
