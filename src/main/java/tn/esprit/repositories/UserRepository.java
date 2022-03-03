package tn.esprit.repositories;

import org.springframework.stereotype.Repository;

import tn.esprit.entities.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	public User findByEmail(String email);
}
