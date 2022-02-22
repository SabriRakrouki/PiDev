package tn.esprit.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.entities.Admin;
import tn.esprit.repositories.AdminRepository;
@Service
public class AdminServiceImpl implements IAdminService{
	@Autowired
	AdminRepository adminRepository ;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Override
	public void addAdmin(Admin admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		adminRepository.save(admin);
	}
	@Override
	public List<Admin> retrieveAllAdmin() {
		return adminRepository.findAll();	}
	@Override
	public void deleteAdmin(int id) {
		adminRepository.deleteById(id);
	}
	@Override
	public Admin updateAdmin(Admin admin) {
		return adminRepository.save(admin); 
	}
}
