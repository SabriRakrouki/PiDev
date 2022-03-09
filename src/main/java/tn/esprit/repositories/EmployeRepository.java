package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.Complaint;
import tn.esprit.entities.Employee;
import tn.esprit.entities.Entreprise;
@Repository
public interface EmployeRepository extends JpaRepository<Employee,Integer> {
	Employee findById(int id);
}
