package tn.esprit.services;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.entities.ERole;
import tn.esprit.entities.Entreprise;
import tn.esprit.entities.Role;
import tn.esprit.repositories.ComplaintRepository;

import tn.esprit.repositories.EmployeeRepository;
import tn.esprit.repositories.EntrepriseRepository;
import tn.esprit.repositories.RoleRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	private final EntrepriseRepository entrepriseRepository;

	private final RoleRepository roleRepository;

	private final EmployeeRepository employeeRepository;

	private final ComplaintRepository complaintRepository;

	private final PasswordEncoder passwordEncoder;

	public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, RoleRepository roleRepository,
			EmployeeRepository employeeRepository, ComplaintRepository complaintRepository) {
		super();
		this.entrepriseRepository = entrepriseRepository;
		this.roleRepository = roleRepository;
		this.employeeRepository = employeeRepository;
		this.complaintRepository = complaintRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public void addEntreprise(Entreprise entreprise) {
		Role userRole = roleRepository.getByName(ERole.ROLE_EMPLOYEE);
		Set<Role> roles = new HashSet<>();
		roles.add(userRole);
		entreprise.setRoles(roles);
		entreprise.setPassword(passwordEncoder.encode(entreprise.getPassword()));
		entreprise.setDateCreation(Calendar.getInstance().getTime());
		entrepriseRepository.save(entreprise);
	}

	@Override
	public List<Entreprise> retrieveAllEntreprise() {
		List<Entreprise> Entreprises = (List<Entreprise>) entrepriseRepository.findAll();
		return Entreprises;
	}

	@Override
	public void deleteEntreprise(int id) {
		entrepriseRepository.deleteById(id);
	}

	@Override
	public Entreprise updateEntreprise(Entreprise entreprise) {
		entreprise.setPassword(passwordEncoder.encode(entreprise.getPassword()));
		return entrepriseRepository.save(entreprise);
	}

	@Override
	public Entreprise FindEntrepriseById(int registre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entreprise FindEntrepriseByemployeeId(int emplid) {
		return entrepriseRepository.findByEmployeesId(emplid);

	}

	@Override
	public List<Entreprise> getAllEntreprise() {
		return entrepriseRepository.findAll();
	}

}
