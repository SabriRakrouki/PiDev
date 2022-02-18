package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.Trip;
@Repository
public interface TripRepository extends JpaRepository<Trip,Integer> {


}
