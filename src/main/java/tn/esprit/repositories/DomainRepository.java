package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.Domain;

@Repository
public interface DomainRepository extends JpaRepository<Domain, Integer>{

}
