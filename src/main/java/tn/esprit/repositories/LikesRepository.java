package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.*;

@Repository
public interface LikesRepository extends JpaRepository<Like, Long>{

}
