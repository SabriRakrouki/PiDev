package tn.esprit.repositories;

import java.util.Date;
import java.util.Set;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.Location;
import tn.esprit.entities.Trip;
import tn.esprit.entities.User;
@Repository
public interface TripRepository extends JpaRepository<Trip,Integer> {
@Query("select t  from Trip as t,Employee as e where t.id=e.trip.id and   e.id=:user  ")
public Set<Trip> findTripByuser(@Param("user") int user);

@Query("select t from  Trip as t where t.id!=:id and t.arrivalDate BETWEEN :dateDebut and :dateFin ")
public Set<Trip> findTripByDate(@Param("dateDebut") Date dateDebut,@Param("dateFin") Date dateFin ,@Param("id") int id) ;







}
