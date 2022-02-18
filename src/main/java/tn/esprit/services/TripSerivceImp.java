package tn.esprit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entities.Trip;
import tn.esprit.repositories.TripRepository;

@Service
public class TripSerivceImp implements ITripService {
	@Autowired
	protected TripRepository tripRepository;

	public TripSerivceImp(TripRepository tripRepository) {
		// TODO Auto-generated constructor stub
		this.tripRepository = tripRepository;
	}

	public List<Trip> getAllTrip() {
		return tripRepository.findAll();
	}

	public Trip AddTrip(Trip trip) {
		tripRepository.save(trip);
		return trip;
	}

	public Trip UpdateTrip(int idTrip, Trip trip) {
		Trip OldTrip = tripRepository.getById(idTrip);
		OldTrip = trip;
		tripRepository.save(OldTrip);
		return trip;
	}

	public void DeleteTrip(int id) {
		tripRepository.delete(tripRepository.getById(id));
	}

	public Trip FindTripById(int id) {
		return tripRepository.getById(id);
	}

}
