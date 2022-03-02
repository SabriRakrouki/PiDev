package tn.esprit.services;

import java.util.Set;

import tn.esprit.entities.Employee;
import tn.esprit.entities.Location;
import tn.esprit.entities.Trip;

public interface MatchAlgorithm {

	public Trip findTripByuser(Employee user);
	public boolean ageGap(int ageOftheEmp, int age);
	public boolean locationCheck(Location firstLocation,Location secLocation);
	public boolean languageCheck(Employee employee,Employee employeeToMatch);
	
	
	public Set<Employee> getAllTheMatchingPeople(Employee user, Trip tripToMatch);
	
	
	
	
}
