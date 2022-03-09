package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.ArchiveComplaints;

@Repository
public interface ArchiveComplaintRepository extends JpaRepository<ArchiveComplaints,Integer> {

}
