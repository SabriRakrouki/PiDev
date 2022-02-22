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

import tn.esprit.entities.Admin;
import tn.esprit.services.IAdminService;
@RestController
public class AdminController {
	@Autowired
    private IAdminService adminService;
	//http://localhost:8090/travelup/back/add-admin
	@PostMapping("/add-admin")
	@ResponseBody
	public void addAdmin(@RequestBody Admin admin) {
		adminService.addAdmin(admin);
	}
	//http://localhost:8090/travelup/back/retrieveAdmins	 
	@GetMapping("/retrieveAdmins")
	@ResponseBody
	public List<Admin>retrieveAllAdmin() {
	return adminService.retrieveAllAdmin();
	}
	//http://localhost:8090/travelup/back/remove-admin/3
		@DeleteMapping("/remove-admin/{admin-id}")
		@ResponseBody
		public void removeAdmin(@PathVariable("admin-id") int adminId) {
			adminService.deleteAdmin(adminId);
		}
		//http://localhost:8090/travelup/back/updateAdmin/3
		@PutMapping("/updateAdmin/{id}")
		@ResponseBody
		public Admin updateAdmin(@RequestBody Admin admin) {
			return adminService.updateAdmin(admin);
		}
}
