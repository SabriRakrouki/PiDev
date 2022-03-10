package tn.esprit.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.entities.Domain;
import tn.esprit.entities.Employee;
import tn.esprit.entities.Entreprise;
import tn.esprit.entities.Location;
import tn.esprit.entities.Trip;
import tn.esprit.entities.User;
import tn.esprit.repositories.TripRepository;

@Service
@Slf4j
public class MatchingAlgoImp implements MatchAlgorithm {
	public static int GABAGE = 5;
	public static int AGESCORE = 25;
	public static int LANGUAGESCORE = 50;

	public static int BORNSCORE = 70;
	public static int CITYTRIPSCORE = 100;
	public static int STATESCORE = 150;
	public static int POSTIONSCORE = 50;
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
	public Set<Trip> findTripByuser(Employee user) {

		return tripRepository.findTripByuser(user.getId());
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

	@Override
	public Set<Trip> findTripByDate(Trip trip) {
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
	public boolean checkdomainEntreprise(Entreprise entrepriseToMatch, Entreprise entreprise) {
		// TODO Auto-generated method stub
		for (Domain dom : entrepriseToMatch.getDomains()) {
			for (Domain domainTomatch : entreprise.getDomains()) {
				if (dom.getNameDomain().equals(domainTomatch.getNameDomain())) {
					return true;
				}
			}

		}

		return false;
	}

	@Override
	public boolean checkPostion(Employee employeeToMatch, Employee employee) {
		// TODO Auto-generated method stub
		if (employeeToMatch.getPosition().getPotionName().equals(employee.getPosition())) {
			return true;
		}

		return false;
	}

	@Override
	public boolean bornCheck(User userToMatch, User user) {
		// TODO Auto-generated method stub
		if (userToMatch.getBornePlace().getCountry().equals(user.getBornePlace().getCountry())) {
			return true;
		}

		return false;
	}

	public Set<Employee> getAllTheMatchingPeople(Employee user, Trip tripToMatch) {

		try {
			Set<Trip> trips= this.findTripByDate(tripToMatch);
				
			
			for (Trip trip : trips) {

				if (trip.getTripLocation().getCountry().equals(tripToMatch.getTripLocation().getCountry())) {

					Set<Employee> usersToMatch = new HashSet<Employee>();
			
						usersToMatch = trip.getEmployee();
					

					for (Employee emp : usersToMatch) {
						if (checkdomainEntreprise(user.getEntreprise(), emp.getEntreprise())) {
							score = 0;
							if (checkPostion(emp, user)) {
								score = score + POSTIONSCORE;
							}
							if (ageGap(user.getAge(), emp.getAge())) {
								score = score + AGESCORE;

							}
							if (bornCheck(emp, user)) {
								score = score + BORNSCORE;
							}

							if (checkStates(trip.getTripLocation().getState(),
									tripToMatch.getTripLocation().getState())) {
								score = score + STATESCORE;
								if (checkCity(trip.getTripLocation().getCity(),
										tripToMatch.getTripLocation().getCity())) {
									score = score + CITYTRIPSCORE;
								}
							}

							if (languageCheck(emp, user)) {
								score = score + LANGUAGESCORE;
							}

							log.info(user.getCin());
							mapuser.put(score, user);
						}

					}

				}

			}

			List<Employee> employeesRes = (List<Employee>) mapuser.descendingMap().values();
			for (Employee em : employeesRes) {

				if (!em.equals(user)) {
					userMatched.add(em);
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return userMatched;

	}

	// i need to put this to the test

}
