package tn.esprit.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import tn.esprit.entities.Employee;
import tn.esprit.entities.Location;
import tn.esprit.entities.Trip;

public interface LocationService {
	public List<Location> getCountry() throws  Exception ;
	public Location GetCountybyTag(String tag) throws Exception;
	public List<Location> getCityByCountry(String tag) throws Exception;
	public List<Location> getStatesbyCountry(String Ctag ) throws Exception;
	public List<Location> GetcitiesbyStateAndCountry(String Ctag,String Stag ) throws Exception;
	public Location addLocationToTrip(int location,int trip);
	public Location addLocationToEmployee(int location,int employee);
	public Location addLocation(Location location);
	public List<Location> getAllLocations();
	public Location updateLocation(Location location);
	public void deleteLocation(int id);
	
	
	
	
}
