package tn.esprit.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.Entreprise;
@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise,Integer>{
	Optional<Entreprise> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
}


