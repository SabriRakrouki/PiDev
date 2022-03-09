package tn.esprit.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import tn.esprit.entities.Employee;
import tn.esprit.services.IEmployeeService;
import tn.esprit.services.UserPDFExporter;

@RestController
public class EmployeeController {
	@Autowired
    private IEmployeeService employeeService;
	
	 @GetMapping("/users/export/pdf")
	    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	         
	        List< Employee> listUsers = employeeService.retrieveAllEmployee();
	       
	        
	        System.out.println(listUsers.toString());
	         
	        UserPDFExporter exporter = new UserPDFExporter(employeeService.retrieveAllEmployee());
	        exporter.export(response);
	         
	    }
	
	//http://localhost:8090/travelup/back/add-employee
	@PostMapping("/add-employee")
	@ResponseBody
	public void addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
	}
	//http://localhost:8090/travelup/back/retrieveEmployees	 
	@GetMapping("/retrieveEmployees")
	@ResponseBody
	public List<Employee>retrieveAllEmployee() {
	return employeeService.retrieveAllEmployee();
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
