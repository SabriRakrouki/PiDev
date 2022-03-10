package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
