package tn.esprit.services;

import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.entities.Employee;
import tn.esprit.entities.Trip;
import tn.esprit.entities.User;
import tn.esprit.repositories.TripRepository;

public class MatchingAlgoImp {
	public static int GABAGE = 5;
	public static int AGESCORE=10;
	public static int LANGUAGESCORE=20;
	public static int LOCATIONEMPSCORE=30;
	@Autowired
	TripRepository tripRepository;
	@Autowired
	Set<User> userMatched;
	@Autowired
	TreeMap<Integer,Employee> mapuser;
	@Autowired
	int score;

	/*
	 * try to find all trips by location to make a collections of trips and get list
	 * of users from them first then will match user with postion of the worker and
	 * domain of the entreprise i will match with : location and city hotel name if
	 * need age and language those something im trying to do
	 * we need to add the score and rank to list how ??
	 * we need sort of collection to put it in the right way
	 * entity score and rank with user in it
	 * 
	 * 
	 * 
	 */
	public Trip findTripByuser(User user) {

		return tripRepository.findTripByuser(user);
	}

	public Set<Trip> findAllTripsByLocation(Trip trip) {

		return tripRepository.findTripByLocation(trip.getLocation(), trip.getCity());

	}

	public boolean AgeGap(int ageOftheEmp, int age) {
		if (age - GABAGE < ageOftheEmp) {
			return true;
		} else if (age + GABAGE > ageOftheEmp) {
			return true;
		} else {
			return false;
		}

	}

	public Set<Employee> getAllTheMatchingPeople(Employee user, Trip tripToMatch) {
		

		Set<Trip> trips = this.findAllTripsByLocation(findTripByuser(user));
		for (Trip trip : trips) {
			if ((trip.getArrivalDate().compareTo(tripToMatch.getArrivalDate()) > 0)
					&& (trip.getArrivalDate().before(tripToMatch.getArrivalDate()))) {
				Set<Employee> usersToMatch = trip.getEmployee();

				for (Employee emp : usersToMatch) {
					score=0;
					if (AgeGap(user.getAge(), emp.getAge())) {
						score=score+AGESCORE;		
							
					}
					
					
					
					
				mapuser.put(score, user);

				}
				
				

			}
			
			
			

		}
		
		
		
		
		
		return (Set<Employee>)mapuser.descendingMap().values();

	}

}
