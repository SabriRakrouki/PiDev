package tn.esprit.services;

import java.util.Set;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import tn.esprit.entities.Employee;
import tn.esprit.entities.Entreprise;
import tn.esprit.entities.Location;
import tn.esprit.entities.Trip;
import tn.esprit.entities.User;

public interface MatchAlgorithm {

	public Set<Trip> findTripByuser(Employee user);

	public boolean ageGap(int ageOftheEmp, int age);

	public boolean languageCheck(Employee employee, Employee employeeToMatch);

	public Set<Trip> findTripByDate(Trip trip) throws NotFoundException;

	public boolean checkStates(String StateToCompaire, String State);

	public boolean checkCity(String CityToCompaire, String City);

	public boolean bornCheck(User userToMatch, User user);

	public boolean checkdomainEntreprise(Entreprise entrepriseToMatch, Entreprise entreprise);

	public boolean checkPostion(Employee employeeToMatch, Employee employee);

	public Set<Employee> getAllTheMatchingPeople(Employee user,Trip trip) ;

}
