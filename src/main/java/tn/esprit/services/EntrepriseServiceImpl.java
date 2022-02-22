package tn.esprit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.entities.Entreprise;
import tn.esprit.repositories.EntrepriseRepository;
@Service
public class EntrepriseServiceImpl  implements IEntrepriseService{
	@Autowired
	EntrepriseRepository entrepriseRepository ;
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Override
	public void addEntreprise(Entreprise entreprise) {
		entreprise.setPassword(passwordEncoder.encode(entreprise.getPassword()));
		entrepriseRepository.save(entreprise);
	}

	@Override
	public List<Entreprise> retrieveAllEntreprise() {
		List<Entreprise> Entreprises = (List<Entreprise>)entrepriseRepository.findAll();
		return Entreprises ;
	}

	@Override
	public void deleteEntreprise(int id) {
		entrepriseRepository.deleteById(id);
	}

	@Override
	public Entreprise updateEntreprise(Entreprise entreprise) {
		return entrepriseRepository.save(entreprise);
	}

}
