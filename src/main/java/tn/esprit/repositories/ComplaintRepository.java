package tn.esprit.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Integer> {

}
