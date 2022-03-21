package tn.esprit.Controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.*;
import tn.esprit.entities.Admin;
import tn.esprit.entities.ERole;
import tn.esprit.entities.Role;
import tn.esprit.entities.StaticOfUser;
import tn.esprit.entities.User;
import tn.esprit.payload.MessageResponse;
import tn.esprit.repositories.RoleRepository;
import tn.esprit.repositories.UserRepository;
import tn.esprit.services.IAdminService;
import tn.esprit.services.IuserService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	private final IAdminService adminService;

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final PasswordEncoder encoder;

	private final IuserService staticService;

	public AdminController(IAdminService adminService, UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder encoder, IuserService staticService) {
		super();
		this.adminService = adminService;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
		this.staticService = staticService;
	}

	// http://localhost:8090/travelup/back/add-admin
	@PostMapping("/add-admin")
	public ResponseEntity<?> registerUser(@Valid @RequestBody Admin admin) {
		if (userRepository.existsByUsername(admin.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(admin.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		adminService.addAdmin(admin);
		return ResponseEntity.ok(new MessageResponse("Admin registered successfully!"));
	}

	// http://localhost:8090/travelup/back/retrieveAdmins
	@GetMapping("/admin/retrieveAdmins")
	@ResponseBody
	public List<Admin> retrieveAllAdmin() {
		return adminService.retrieveAllAdmin();
	}

	// http://localhost:8090/travelup/back/remove-admin/3
	@DeleteMapping("/remove-admin/{admin-id}")
	@ResponseBody
	public void removeAdmin(@PathVariable("admin-id") int adminId) {
		adminService.deleteAdmin(adminId);
	}

	// http://localhost:8090/travelup/back/updateAdmin/3
	@PutMapping("/updateAdmin/{id}")
	@ResponseBody
	public Admin updateAdmin(@RequestBody Admin admin) {
		return adminService.updateAdmin(admin);
	}

	@PostMapping("/static")
	public StaticOfUser staticOfUser() throws ParseException {
		return staticService.addUserStatic();
	}
}
