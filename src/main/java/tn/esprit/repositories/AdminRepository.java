package tn.esprit.repositories;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entities.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer>{
	
}
