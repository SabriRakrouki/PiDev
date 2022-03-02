package tn.esprit.services;

import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.entities.Employee;
import tn.esprit.entities.Location;
import tn.esprit.entities.Trip;
import tn.esprit.entities.User;
import tn.esprit.repositories.TripRepository;

public class MatchingAlgoImp implements MatchAlgorithm {
	public static int GABAGE = 5;
	public static int AGESCORE = 10;
	public static int LANGUAGESCORE = 20;
	public static int LOCATIONEMPSCORE = 30;
	@Autowired
	TripRepository tripRepository;
	@Autowired
	Set<User> userMatched;
	@Autowired
	TreeMap<Integer, Employee> mapuser;
	@Autowired
	int score;

	/*
	 * try to find all trips by location to make a collections of trips and get list
	 * of users from them first then will match user with postion of the worker and
	 * domain of the entreprise i will match with : location and city hotel name if
	 * need age and language those something im trying to do we need to add the
	 * score and rank to list how ?? we need sort of collection to put it in the
	 * right way entity score and rank with user in it
	 * 
	 * 
	 * 
	 */
	public Trip findTripByuser(Employee user) {

		return tripRepository.findTripByuser(user);
	}

	public Set<Trip> findAllTripsByLocation(Trip trip) {

		return tripRepository.findTripByLocation(trip.getTripLocation());

	}

	public boolean ageGap(int ageOftheEmp, int age) {
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
					score = 0;
					if (ageGap(user.getAge(), emp.getAge())) {
						score = score + AGESCORE;

					}
					if (locationCheck(emp.getBornePlace(), user.getBornePlace())) {
						score = score + LOCATIONEMPSCORE;
					}
					if (languageCheck(emp, user)) {
						score = score + LANGUAGESCORE;
					}
					if (locationCheck(trip.getTripLocation(), tripToMatch.getTripLocation())) {
						score = score + LOCATIONEMPSCORE;
					}

					mapuser.put(score, user);

				}

			}

		}

		return (Set<Employee>) mapuser.descendingMap().values();

	}

	@Override
	public boolean locationCheck(Location firstLocation, Location secLocation) {
		// TODO Auto-generated method stub
		if (firstLocation.equals(secLocation)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean languageCheck(Employee employee, Employee employeeToMatch) {
		// TODO Auto-generated method stub
		for (String lan : employee.getLanguages()) {
			if (employeeToMatch.getLanguages().contains(lan)) {
				return true;
			}
		}

		return false;
	}

}
