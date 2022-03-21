package tn.esprit.services;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.entities.Role;
import tn.esprit.entities.Admin;
import tn.esprit.entities.ERole;
import tn.esprit.repositories.AdminRepository;
import tn.esprit.repositories.RoleRepository;

@Service
public class AdminServiceImpl implements IAdminService {

	private final AdminRepository adminRepository;

	private final RoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;

	public AdminServiceImpl(AdminRepository adminRepository, RoleRepository roleRepository) {
		this.adminRepository = adminRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public void addAdmin(Admin admin) {
		Role userRole = roleRepository.getByName(ERole.ROLE_ADMIN);
		Set<Role> roles = new HashSet<>();
		roles.add(userRole);
		admin.setRoles(roles);
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		admin.setDateCreation(Calendar.getInstance().getTime());
		adminRepository.save(admin);
	}

	@Override
	public List<Admin> retrieveAllAdmin() {
		return adminRepository.findAll();
	}

	@Override
	public void deleteAdmin(int id) {
		adminRepository.deleteById(id);
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		return adminRepository.save(admin);
	}
}
