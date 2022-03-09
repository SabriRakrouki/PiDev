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
@Query("select t  from Trip as t,Employee as e where   e.id=:user  ")
public Set<Trip> findTripByuser(@Param("user") int user);

@Query("select t from  Trip as t where t.id!=:id and t.arrivalDate BETWEEN :dateDebut and :dateFin")
public Set<Trip> findTripByDate(@Param("dateDebut") Date dateDebut,@Param("dateFin") Date dateFin ,@Param("id") int id) ;


@Query(value = "select t from trip as t, location as l where t.trip_location_id=l.id and l.id=:locationTrip",nativeQuery = true)
public Set<Trip> findTripByLocation(@Param("locationTrip") Location Location);




}
