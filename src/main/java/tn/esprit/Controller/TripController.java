package tn.esprit.Controller;

import java.io.IOException;
import java.util.List;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

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
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import tn.esprit.entities.Employee;
import tn.esprit.entities.Entreprise;
import tn.esprit.entities.Location;
import tn.esprit.entities.Complaint;
import tn.esprit.entities.Trip;
import tn.esprit.entities.User;
import tn.esprit.repositories.EmployeeRepository;
import tn.esprit.repositories.EntrepriseRepository;
import tn.esprit.services.ITripService;
import tn.esprit.services.LocationService;
import tn.esprit.services.PDFGeneratorService;

@RestController
@RequestMapping("/trip")

public class TripController {
	@Autowired
	protected ITripService iTripService;
	@Autowired
	LocationService locationService;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	EntrepriseRepository userRepository;
	private JavaMailSender mailSender;

	@PostMapping("/addTrip")

	@ResponseBody
	public ResponseEntity<String> addTrip(@RequestBody Trip trip) {
		String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            } else {
             username = principal.toString();
            }
        Entreprise us= userRepository.findByUsername(username).orElse(null);
        
        trip.setEntreprise( us);
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
	public List<Trip> getAllTrip() {

		return iTripService.getAllTrip();
	}

	@DeleteMapping("/deleteTrip/{idTrip}")
	@ResponseBody

	public ResponseEntity<String> DeleteTrip(@PathVariable("idTrip") int idTrip) {
		iTripService.DeleteTrip(idTrip);
		return ResponseEntity.status(200).body("Trip Deleted");

	}

	@PostMapping("/addUser/{idTrip}/{iduser}")
	@ResponseBody
	public Trip addUser(@PathVariable("idTrip") int idtrip, @PathVariable("iduser") int iduser) {

		return iTripService.AddUserToTrip(iduser, idtrip);
	}
	
	@PostMapping("/addLocation/{idLoc}/{idTrip}")
	@ResponseBody
	public Location addLocationToTrip(@PathVariable("idLoc")int loc,@PathVariable("idTrip") int tip) {
		return locationService.addLocationToTrip(loc, tip);
	}
	

	@GetMapping("/countries")
	@ResponseBody
	public List<Location> getCountry() throws Exception {

		return locationService.getCountry();

	}

	@GetMapping("/country/{tag}")
	@ResponseBody
	public Location getCountrybyTag(@PathVariable("tag") String tag) throws Exception {
		return locationService.GetCountybyTag(tag);
	}

	@GetMapping("/country/{tag}/cities")
	@ResponseBody
	public List<Location> getCitybyCountry(@PathVariable("tag") String tag) throws Exception {
		return locationService.getCityByCountry(tag);
	}

	@GetMapping("/country/{tag}/states")
	@ResponseBody
	public List<Location> getStatebyCountry(@PathVariable("tag") String tag) throws Exception {
		return locationService.getStatesbyCountry(tag);
	}

	@GetMapping("/country/{Ctag}/states/{Stag}")
	@ResponseBody
	public List<Location> getStatebyCountry(@PathVariable("Ctag") String Ctag, @PathVariable("Stag") String Stag)
			throws Exception {
		return locationService.GetcitiesbyStateAndCountry(Ctag, Stag);
	}

	@GetMapping("/getvoyages")
	@ResponseBody
	public List<Trip> getTrips() {
		List<Trip> list = iTripService.getAllTrip();
		// return ClientServise.retrieveAllClients();
		return list;
	}

	@GetMapping("/getrating/{idrating}")
	@ResponseBody
	public String getrating(@PathVariable("idrating") int idrating) {
		String rating = iTripService.FindTripById(idrating).getRating();
		return rating;
	}

	public void sendEmail(String recipientEmail) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("esprittravelup@gmail.com", "TRAVELUP");
		helper.setTo(recipientEmail);

		String subject = "NOUVEAU RATING";

		String content = "Bonjour,un voyage a été noté par un utilisateur!!!";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

	@PutMapping("/notervoyage/{voyageid}")
	public void NoterVoyage(@PathVariable("voyageid") int voyageid,
			@RequestParam(value = "attribution") Float attribution)
			throws UnsupportedEncodingException, MessagingException {
		Trip tripp = iTripService.FindTripById(voyageid);
		iTripService.NoterVoyage(tripp, attribution);
		System.out.println(tripp.getAttribution());
		System.out.println(tripp.getCompteur());
		System.out.println(tripp.getTotalattribution());
		System.out.println(tripp.getNote());
		sendEmail(tripp.getEntreprise().getEmail());
		// @EventListener(ApplicationReadyEvent.class)
		/*
		 * senderService.sendSimpleMessage("safa.chaouali.750@gmail.com",
		 * "rating","Bonjouur!!! un voyage a été noté par un utilisateur");
		 */

	}

}
