package tn.esprit.services;

import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;

import tn.esprit.entities.Location;

public interface LocationService {
	public List<String> getCountry() throws UnirestException;
	public List<String> getCityByCountry();
	public List<String> getStatesbyCountry();
	public List<String> GetcitiesbyStateAndCountry();
	
	public Location addLocation(Location location);
	public List<Location> getAllLocations();
	public Location updateLocation(Location location);
	public void deleteLocation(int id);
	
	
	
	
}
