package tn.esprit.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import tn.esprit.entities.Employee;

import tn.esprit.entities.User;
import tn.esprit.repositories.UserRepository;
import tn.esprit.services.IEmployeeService;


import tn.esprit.entities.Location;

import tn.esprit.services.LocationService;
import tn.esprit.services.UserPDFExporter;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/Employee")
public class EmployeeController {


	private final IEmployeeService employeeService;

	private final LocationService locationService;

	public EmployeeController(IEmployeeService employeeService, LocationService locationService) {
		super();
		this.employeeService = employeeService;
		this.locationService = locationService;
	}

	@GetMapping("/users/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<Employee> listUsers = employeeService.retrieveAllEmployee();

		System.out.println(listUsers.toString());

		UserPDFExporter exporter = new UserPDFExporter(employeeService.retrieveAllEmployee());
		exporter.export(response);

	}

	@PostMapping("/signup/employee")
	@ResponseBody
	public void addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
	}

	// http://localhost:8090/travelup/back/retrieveEmployees
	@GetMapping("/employee/retrieveEmployees")
	@ResponseBody
	public List<Employee> retrieveAllEmployee() {
		List<Employee> employees = employeeService.retrieveAllEmployee();
		return employees;
	}


	// http://localhost:8090/travelup/back/remove-employee/3
	@DeleteMapping("/remove-employee/{employee-id}")
	@ResponseBody
	public void removeEmployee(@PathVariable("employee-id") int employeeId) {
		employeeService.deleteEmployee(employeeId);
	}

	// http://localhost:8090/travelup/back/updateEmployee/3
	@PutMapping("/updateEmployee/{id}")
	@ResponseBody
	public Employee updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}

	@PostMapping("/addBornLocation/{idEmp}/{idLoc}")
	@ResponseBody
	public Location addBornLocation(@PathVariable("idEmp") int emp, @PathVariable("idLoc") int loc) {
		return locationService.addLocationToEmployee(loc, emp);
	}
}