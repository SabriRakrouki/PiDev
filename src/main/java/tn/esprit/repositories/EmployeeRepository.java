package tn.esprit.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.Employee;
import tn.esprit.entities.Entreprise;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
	Optional<Employee> findByUsername(String username);
	Employee findById(int id);
	
}

