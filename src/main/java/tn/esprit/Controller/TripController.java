package tn.esprit.Controller;

import java.io.IOException;
import java.util.List;

/*import java.net.http.HttpClient;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;*/
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import tn.esprit.entities.Employee;
import tn.esprit.entities.Location;
import tn.esprit.entities.Trip;
import tn.esprit.repositories.EmployeeRepository;
import tn.esprit.services.ITripService;
import tn.esprit.services.LocationService;

@RestController
@RequestMapping("/trip")
public class TripController {
	@Autowired
	protected ITripService iTripService;
	@Autowired
	LocationService locationService;
	@Autowired
	EmployeeRepository employeeRepository ;
	
	@PostMapping("/addTrip")
	@ResponseBody
	public ResponseEntity<String> addTrip(Trip trip) {

		iTripService.AddTrip(trip);
		return ResponseEntity.ok("Trip created");

	}

	@PutMapping("/updateTrip/{idTrip}")
	@ResponseBody
	public Trip updateAdmin(@RequestBody Trip trip) {
		iTripService.UpdateTrip(trip);

		return trip;
	}

	@GetMapping("/getAll")
	@ResponseBody
	public ResponseEntity<List<Trip>> getAllTrip() {

		return ResponseEntity.status(200).body(iTripService.getAllTrip());
	}

	@DeleteMapping("/deleteTrip/{idTrip}")
	@ResponseBody

	public ResponseEntity<String> DeleteTrip(@PathVariable("idTrip") int idTrip) {
		iTripService.DeleteTrip(idTrip);
		return ResponseEntity.status(200).body("Trip Deleted");

	}
	@PostMapping("/addUser/{idTrip}")
	@ResponseBody
	public Trip addUser(@PathVariable("idTrip") int idtrip ,@RequestBody Employee employee) {
		

		return iTripService.AddUserToTrip(employee,idtrip );
	}

	
	@GetMapping("/countries")
	@ResponseBody
	public List<Location> getCountry() throws Exception {
		
		
	return locationService.getCountry();

	}
	@GetMapping("/country/{tag}")
	@ResponseBody
	public Location getCountrybyTag(@PathVariable("tag") String tag) throws Exception{
		return locationService.GetCountybyTag(tag);
	}
	@GetMapping("/country/{tag}/cities")
	@ResponseBody
	public List<Location> getCitybyCountry(@PathVariable("tag") String tag) throws Exception{
		return locationService.getCityByCountry(tag);
	}
	@GetMapping("/country/{tag}/states")
	@ResponseBody
	public List<Location> getStatebyCountry(@PathVariable("tag") String tag) throws Exception{
		return locationService.getStatesbyCountry(tag);
	}
	@GetMapping("/country/{Ctag}/states/{Stag}")
	@ResponseBody
	public List<Location> getStatebyCountry(@PathVariable("Ctag") String Ctag,@PathVariable("Stag") String Stag) throws Exception{
		return locationService.GetcitiesbyStateAndCountry(Ctag, Stag);
	}

}
