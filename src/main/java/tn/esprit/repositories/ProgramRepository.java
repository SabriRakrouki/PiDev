package tn.esprit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.Program;
@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {
	
	public List<Program> findBytripId(int idtrip);
}
