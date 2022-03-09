package tn.esprit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.Complaint;
import tn.esprit.entities.Employee;
import tn.esprit.services.IComplaintService;
import tn.esprit.services.IEmployeService;

@RestController
public class EmployeControlleur {
	@Autowired	
	IEmployeService employeService;
	@GetMapping("/getEmployes")
	@ResponseBody
	public List<Employee> getEmployes(){
		List<Employee> list=employeService.getAllEmploye();
		//return ClientServise.retrieveAllClients();
		return list;
	}
}
