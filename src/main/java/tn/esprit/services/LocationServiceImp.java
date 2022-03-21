package tn.esprit.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.entities.Employee;
import tn.esprit.entities.Location;
import tn.esprit.entities.Trip;
import tn.esprit.repositories.EmployeeRepository;
import tn.esprit.repositories.LocationRepository;
import tn.esprit.repositories.TripRepository;

@Service
@Slf4j

public class LocationServiceImp implements LocationService {

	@Value("${APIKEY}")
	private String secretKey;
	private final List<Location> locations;

	private final LocationRepository locationRepository;

	private final EmployeeRepository employeeRepository;

	private final TripRepository tripRepository;

	public LocationServiceImp( List<Location> locations, LocationRepository locationRepository,
			EmployeeRepository employeeRepository, TripRepository tripRepository) {

		this.locations = locations;
		this.locationRepository = locationRepository;
		this.employeeRepository = employeeRepository;
		this.tripRepository = tripRepository;
	}

	@Override
	public List<Location> getCountry() throws Exception {
		// TODO Auto-generated method stub

		String url = "https://api.countrystatecity.in/v1/countries";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		con.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
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
	public List<Location> getCityByCountry(String tag) throws Exception {
		// TODO Auto-generated method stub
		locations.removeAll(locations);
		String url = "https://api.countrystatecity.in/v1/countries/" + tag + "/cities";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		con.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
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
			location.setCity(jsonObject.getString("name"));
			location.setCountryTag(tag);
			locations.add(location);

			// log.info(test);
		}

		return locations;
	}

	@Override
	public Location GetCountybyTag(String tag) throws Exception {
		// TODO Auto-generated method stub
		String url = "https://api.countrystatecity.in/v1/countries/" + tag;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		con.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
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
		log.info(response.toString());

		res = res.replace(",", ",\n");
		String[] restab = res.split("}");

		JSONObject jsonObject = new JSONObject(response.toString());
		Location location = new Location();
		location.setCountry(jsonObject.getString("name"));
		location.setCountryTag(tag);
		return location;
	}

	@Override
	public List<Location> getStatesbyCountry(String Ctag) throws Exception {
		// TODO Auto-generated method stub

		locations.removeAll(locations);
		String url = "https://api.countrystatecity.in/v1/countries/" + Ctag + "/states";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		con.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
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

		log.info(response.toString());

		String res = response.toString().replace("[", "{");

		res = res.replace(",", ",\n");
		String[] restab = res.split("}");
		for (int i = 1; i < restab.length - 1; i++) {
			restab[i] = restab[i].replaceFirst(",", "");

			String test = restab[i] + "}";
			test = test.replace("\n", "");

			JSONObject jsonObject = new JSONObject(test);
			Location location = new Location();
			location.setState(jsonObject.getString("name"));
			location.setCountryTag(Ctag);
			location.setStateTage(jsonObject.getString("iso2"));
			locations.add(location);

			// log.info(test);
		}

		return locations;
	}

	@Override
	public List<Location> GetcitiesbyStateAndCountry(String Ctag, String Stag) throws Exception {
		// TODO Auto-generated method stub
		locations.removeAll(locations);
		String url = "https://api.countrystatecity.in/v1/countries/" + Ctag + "/states/" + Stag + "/cities";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		con.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
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
			location.setCity(jsonObject.getString("name"));
			location.setCountryTag(Ctag);
			location.setCountryTag(Stag);
			locations.add(location);

			// log.info(test);
		}

		return locations;

	}

	@Override
	public Location addLocation(Location location) {
		// TODO Auto-generated method stub
		return locationRepository.save(location);
	}

	@Override
	public List<Location> getAllLocations() {
		// TODO Auto-generated method stub
		return locationRepository.findAll();
	}

	@Override
	public Location updateLocation(Location location) {
		// TODO Auto-generated method stub
		return locationRepository.save(location);
	}

	@Override
	public void deleteLocation(int id) {
		// TODO Auto-generated method stub
		locationRepository.deleteById(id);
	}

	@Override
	public Location addLocationToTrip(int location, int trip) {
		// TODO Auto-generated method stub
		Location loc = locationRepository.findById(location).get();

		Trip trips = tripRepository.findById(trip).get();
		trips.setTripLocation(loc);
		tripRepository.save(trips);
		return loc;
	}

	@Override
	public Location addLocationToEmployee(int location, int employee) {
		// TODO Auto-generated method stub
		Employee emp = employeeRepository.findById(employee).get();
		Location loc = locationRepository.findById(location).get();
		emp.setBornePlace(loc);
		employeeRepository.save(emp);
		return loc;
	}

}
