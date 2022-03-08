package tn.esprit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import tn.esprit.entities.Employee;
import tn.esprit.entities.Location;
import tn.esprit.entities.Trip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.json.JSONObject;

@Service
@Slf4j

public class LocationServiceImp implements LocationService {

	@Value("${APIKEY}")
	private String secretKey;
	List<Location> locations;

	public LocationServiceImp(List<Location> locations) {
		// TODO Auto-generated constructor stub
		this.locations = locations;
	}

	@Override
	public List<Location> getCountry() throws Exception {
		// TODO Auto-generated method stub

		String url = "https://api.countrystatecity.in/v1/countries";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		// add request header
		con.setRequestProperty("X-CSCAPI-KEY", secretKey);
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {

			response.append(inputLine);

		}
		in.close();

		String res = response.toString().replace("[", "{");

		res = res.replace(",", ",\n");
		String[] restab = res.split("}");

		for (int i = 1; i < restab.length - 1; i++) {
			restab[i] = restab[i].replaceFirst(",", "");

			String test = restab[i] + "}";
			test = test.replace("\n", "");

			JSONObject jsonObject = new JSONObject(test);
			Location location = new Location();
			location.setCountry(jsonObject.getString("name"));
			location.setCountryTag(jsonObject.getString("iso2"));
			locations.add(location);

			// log.info(test);
		}

		return locations;

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

	@Override
	public Location addLocationToTrip(Location location, Trip trip) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location addLocationToEmployee(Location location, Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

}
