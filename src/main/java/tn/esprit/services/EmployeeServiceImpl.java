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
import tn.esprit.entities.Employee;
import tn.esprit.entities.Entreprise;
import tn.esprit.entities.Invitation;
import tn.esprit.entities.Role;
import tn.esprit.entities.State;
import tn.esprit.repositories.EmployeeRepository;
import tn.esprit.repositories.EntrepriseRepository;
import tn.esprit.repositories.InvitationRepository;
import tn.esprit.repositories.RoleRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	private final EmployeeRepository employeeRepository;

	private final EntrepriseRepository entrepriseRepository;

	private final InvitationRepository invitationRepository;

	private final RoleRepository roleRepository;
	
	private final PasswordEncoder passwordEncoder;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, EntrepriseRepository entrepriseRepository,
			InvitationRepository invitationRepository, RoleRepository roleRepository) {
		this.employeeRepository = employeeRepository;
		this.entrepriseRepository = entrepriseRepository;
		this.invitationRepository = invitationRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public void addEmployee(Employee employee) {
		Role userRole = roleRepository.getByName(ERole.ROLE_EMPLOYEE);
		Set<Role> roles = new HashSet<>();
		roles.add(userRole);
		employee.setRoles(roles);
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		Invitation invitation = invitationRepository.findByEmail(employee.getEmail());
		invitation.setState(State.Accepted);
		Entreprise entreprise = invitation.getEntreprise();
		employee.setEntreprise(entreprise);
		employee.setDateCreation(Calendar.getInstance().getTime());
		employeeRepository.save(employee);
	}

	@Override
	public List<Employee> retrieveAllEmployee() {
		List<Employee> Employees = employeeRepository.findAll();
		return Employees;
	}

	@Override
	public void deleteEmployee(int id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		return employeeRepository.save(employee);
	}

}
