package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.Position;
import tn.esprit.entities.Program;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer>{

}
