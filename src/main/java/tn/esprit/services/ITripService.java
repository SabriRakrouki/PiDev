package tn.esprit.services;

import java.util.List;
import java.util.Set;

import tn.esprit.entities.Employee;
import tn.esprit.entities.Trip;
import tn.esprit.entities.User;

public interface ITripService {
	public List<Trip> getAllTrip();

	public Trip AddTrip(Trip trip);

	public Trip UpdateTrip(Trip trip);

	public void DeleteTrip(int id);

	public Trip FindTripById(int id);
	
	public Trip AddUserToTrip(Employee employee, int idtrip);
	
}
