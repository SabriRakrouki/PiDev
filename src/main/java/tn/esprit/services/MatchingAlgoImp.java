package tn.esprit.services;

import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.entities.Employee;
import tn.esprit.entities.Location;
import tn.esprit.entities.Trip;
import tn.esprit.entities.User;
import tn.esprit.repositories.TripRepository;

@Service
@Slf4j
public class MatchingAlgoImp implements MatchAlgorithm {
	public static int GABAGE = 5;
	public static int AGESCORE = 10;
	public static int LANGUAGESCORE = 20;
	public static int LOCATIONEMPSCORE = 30;
	@Autowired
	TripRepository tripRepository;

	Set<Employee> userMatched;

	TreeMap<Integer, Employee> mapuser;

	int score;

	public MatchingAlgoImp(TreeMap<Integer, Employee> mapuser, Set<Employee> userMatched) {
		// TODO Auto-generated constructor stub
		this.mapuser = mapuser;

		this.userMatched = userMatched;
	}

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

	public boolean ageGap(int ageOftheEmp, int age) {
		if (age - GABAGE < ageOftheEmp) {
			return true;
		} else if (age + GABAGE > ageOftheEmp) {
			return true;
		} else {
			return false;
		}

	}

	public Set<Employee> getAllTheMatchingPeople(Employee user) {
		userMatched = null;
		Trip tripToMatch = findTripByuser(user);

		Set<Trip> trips = this.findTripByDate(tripToMatch);
		for (Trip trip : trips) {

			if (trip.getTripLocation().getCountry().equals(tripToMatch.getTripLocation().getCountry())) {

				Set<Employee> usersToMatch = trip.getEmployee();

				for (Employee emp : usersToMatch) {

					score = 0;
					if (ageGap(user.getAge(), emp.getAge())) {
						score = score + AGESCORE;

					}

					if (languageCheck(emp, user)) {
						score = score + LANGUAGESCORE;
					}

					log.info(user.getCin());
					mapuser.put(score, user);

				}

			}

		}
		for (Employee em : mapuser.descendingMap().values()) {
			userMatched.add(em);

		}
		return userMatched;

	}

	@Override
	public Set<Trip> findTripByDate(Trip trip) {
		// TODO Auto-generated method stub
		return tripRepository.findTripByDate(trip.getArrivalDate(), trip.getDepartDate(), trip.getId());
	}

	@Override
	public boolean checkStates(String StateToCompaire, String State) {

		// TODO Auto-generated method stub
		if (StateToCompaire.equals(State)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean checkCity(String CityToCompaire, String City) {
		// TODO Auto-generated method stub
		if (CityToCompaire.equals(City)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean languageCheck(Employee employee, Employee employeeToMatch) {
		// TODO Auto-generated method stub
		for (String lang : employee.getLanguages()) {
			for (String langToMatch : employeeToMatch.getLanguages()) {
				if (lang.equals(langToMatch)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean BorneCheck(User userToMatch, User user) {
		// TODO Auto-generated method stub
		if (userToMatch.getBornePlace().getCountry().equals(user.getBornePlace().getCountry())) {
			return true;
		}

		return false;
	}

	// i need to put this to the test

}
