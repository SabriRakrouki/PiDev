package tn.esprit.Controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.spring.web.json.Json;
import tn.esprit.entities.Trip;
import tn.esprit.services.ITripService;

@RestController
@RequestMapping("/trip")
public class TripController {
	@Autowired
	protected ITripService iTripService;

	@RequestMapping("/addTrip")
	@ResponseBody
	public ResponseEntity<String> addTrip( @RequestBody Trip trip) {
		
			iTripService.AddTrip(trip);
			return ResponseEntity.ok("Trip created");
		
		
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<String>updateTrip(@RequestBody Trip trip){
	
		
		iTripService.UpdateTrip(trip);
	return ResponseEntity.ok("Trip Updated");
	}
	
	@GetMapping( value = "/getAll",consumes = "application/json")
	@ResponseBody
	public ResponseEntity<List<Trip>> getAllTrip(){
		
		
		return ResponseEntity.status(200).body(iTripService.getAllTrip());
	}
	

	@DeleteMapping("/deleteTrip/{idTrip}")
	@ResponseBody
	
	public ResponseEntity<String>  DeleteTrip(@PathVariable("idTrip") int idTrip) {
		iTripService.DeleteTrip(idTrip);
		return ResponseEntity.status(200).body("Trip Deleted");
		
		
	}

}
