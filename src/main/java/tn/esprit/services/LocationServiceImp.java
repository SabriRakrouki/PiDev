package tn.esprit.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import java.net.URLEncoder;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import tn.esprit.entities.Location;

public class LocationServiceImp implements LocationService{

	 @Value("${APIKEY}")
	    private String secretKey;
	
	
	
	@Override
	public List<String> getCountry() throws UnirestException {
		// TODO Auto-generated method stub
		HttpResponse <JsonNode> response = Unirest.get("https://api.countrystatecity.in/v1/countries")
			    
			      .header("X-CSCAPI-KEY", secretKey)
			      .asJson();
			    System.out.println(response.getStatus());
			      System.out.println(response.getHeaders().get("Content-Type"));
			      
		return (List<String>) response;
	}

	@Override
	public List<String> getCityByCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getStatesbyCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> GetcitiesbyStateAndCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location addLocation(Location location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Location> getAllLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location updateLocation(Location location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteLocation(int id) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
