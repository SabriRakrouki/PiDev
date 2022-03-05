package tn.esprit.repositories;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.Location;
import tn.esprit.entities.Trip;
import tn.esprit.entities.User;
@Repository
public interface TripRepository extends JpaRepository<Trip,Integer> {
@Query(value="select t.*  from trip as t,trip_employee as e where  t.id =e.trip_id and e.employee_id=:user",nativeQuery = true)
public Trip findTripByuser(@Param("user") User user);

@Query(value="select t.* from  trip as t where t.id!=:id and t.date BETWEEN :dateDebut and :dateFin",nativeQuery = true)
public Set<Trip> findTripByDate(@Param("") Date dateDebut,@Param("") Date dateFin ,@Param("") int id);


@Query(value = "select t.* from trip as t, location as l where t.trip_location_id=l.id and l.id=:locationTrip",nativeQuery = true)
public Set<Trip> findTripByLocation(@Param("locationTrip") Location Location);




}
