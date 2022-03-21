package tn.esprit.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.entities.Employee;
import tn.esprit.entities.Entreprise;
import tn.esprit.entities.Position;
import tn.esprit.entities.User;
import tn.esprit.repositories.EmployeeRepository;
import tn.esprit.repositories.EntrepriseRepository;
import tn.esprit.repositories.UserRepository;

@Service
public class ProfilService implements ProfilServiceINT {

	private final UserRepository userRepository;

	private final EmployeeRepository employeRepository;

	private final EntrepriseRepository entrepriseRepository;

	private final SentVerifEmail sender;

	private final PasswordEncoder passwordEncoder;

	public ProfilService(UserRepository userRepository, EmployeeRepository employeRepository,
			EntrepriseRepository entrepriseRepository, SentVerifEmail sender) {
		super();
		this.userRepository = userRepository;
		this.employeRepository = employeRepository;
		this.entrepriseRepository = entrepriseRepository;
		this.sender = sender;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public void MdpOublier(String Username) {

		User u = userRepository.findByUsername(Username).get();
		System.out.println("#########################################");
		System.out.println("User" + u.toString());
		System.out.println("User email" + u.getEmail());
		System.out.println("#########################################");

		long x = 1234567L;
		long y = 23456789L;
		Random r = new Random();
		long number = x + ((long) (r.nextDouble() * (y - x)));
		u.setCode(number);

		try {
			sender.sendHtmlEmail(number, u.getEmail());
			System.out.println("email sent!");
		} catch (MessagingException e) {

			e.printStackTrace();
		}
		userRepository.save(u);

	}

	@Override
	public Boolean verifaccount(long code, int id) {
		User us = userRepository.getById(id);
		if (us.getCode() == code) {
			us.setCode(0);
			userRepository.save(us);
			return true;

		} else
			return false;
	}

	@Override
	public Boolean VerifPassword(String newPassword, String username, String password) {
		if (newPassword.compareTo(password) == 0) {
			System.out.println("Sending Verif Email...");
			MdpOublier(username);
			return true;

		} else {
			return false;

		}
	}

	@Override
	public HashMap<String, Integer> Statestique(int IdEntreprise) {
		Entreprise entreprise = entrepriseRepository.getById(IdEntreprise);
		List<Employee> Lemp = new ArrayList<Employee>();
		List<Position> Lp = new ArrayList<Position>();
		List<Integer> Stat = new ArrayList<Integer>();
		HashMap<String, Integer> PosnameSize = new HashMap<>();

		Lemp.addAll(entreprise.getEmployees());
		for (Employee employee : Lemp) {

			if (PosnameSize.containsKey(employee.getPosition().getPotionName())) {
				int i = PosnameSize.get(employee.getPosition().getPotionName());
				i++;
				PosnameSize.put(employee.getPosition().getPotionName(), i);
			} else {
				PosnameSize.put(employee.getPosition().getPotionName(), 1);
			}

			for (Map.Entry mapentry : PosnameSize.entrySet()) {
				System.out.println("clé: " + mapentry.getKey() + " | valeur: " + mapentry.getValue());
			}

		}
		return PosnameSize;

	}

}
