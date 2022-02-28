package tn.esprit.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.Trip;
import tn.esprit.entities.User;
@Repository
public interface TripRepository extends JpaRepository<Trip,Integer> {
@Query("select *  from trip where employee=:user")
public Trip findTripByuser(@Param("user") User user);


@Query("select * from trip where location=:locationTrip and city=:CityTrip")
public Set<Trip> findTripByLocation(@Param("locationTrip") String Location,@Param("CityTrip") String city);




}
