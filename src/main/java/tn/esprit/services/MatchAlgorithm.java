package tn.esprit.services;

import java.util.Set;

import tn.esprit.entities.Employee;
import tn.esprit.entities.Location;
import tn.esprit.entities.Trip;
import tn.esprit.entities.User;

public interface MatchAlgorithm {

	public Trip findTripByuser(Employee user);

	public boolean ageGap(int ageOftheEmp, int age);

	public boolean languageCheck(Employee employee, Employee employeeToMatch);

	public Set<Trip> findTripByDate(Trip trip);

	public boolean checkStates(String StateToCompaire, String State);

	public boolean checkCity(String CityToCompaire, String City);
	
	public boolean BorneCheck(User userToMatch,User user);

	public Set<Employee> getAllTheMatchingPeople(Employee user);

}
