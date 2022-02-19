package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.Program;

@Repository
public interface CommentRepository extends JpaRepository<Program, Integer>{

}
