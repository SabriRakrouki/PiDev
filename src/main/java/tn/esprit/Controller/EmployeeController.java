package tn.esprit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.Employee;
import tn.esprit.services.IEmployeeService;

@RestController
public class EmployeeController {
	@Autowired
    private IEmployeeService employeeService;
	//http://localhost:8090/travelup/back/add-employee
	@PostMapping("/signup/employee")
	@ResponseBody
	public void addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
	}
	//http://localhost:8090/travelup/back/retrieveEmployees	 
	@GetMapping("/employee/retrieveEmployees")
	@ResponseBody
	public List<Employee>retrieveAllEmployee() {
		List <Employee> employees = employeeService.retrieveAllEmployee();
	return employees;
	}
	//http://localhost:8090/travelup/back/remove-employee/3
		@DeleteMapping("/remove-employee/{employee-id}")
		@ResponseBody
		public void removeEmployee(@PathVariable("employee-id") int employeeId) {
			employeeService.deleteEmployee(employeeId);
		}
		//http://localhost:8090/travelup/back/updateEmployee/3
		@PutMapping("/updateEmployee/{id}")
		@ResponseBody
		public Employee updateEmployee(@RequestBody Employee employee) {
			return employeeService.updateEmployee(employee);
		}
}
