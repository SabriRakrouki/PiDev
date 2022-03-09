package tn.esprit.services;

import java.util.List;

import tn.esprit.entities.Trip;

public interface ITripService {
	public List<Trip> getAllTrip();

	public Trip AddTrip(Trip trip);

	public Trip UpdateTrip(Trip trip);

	public void DeleteTrip(int id);

	public Trip FindTripById(int id);
}
