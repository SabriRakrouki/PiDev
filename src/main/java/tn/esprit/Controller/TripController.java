package tn.esprit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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

import tn.esprit.entities.Trip;
import tn.esprit.services.ITripService;

@RestController
@RequestMapping("/trip")
public class TripController {
	@Autowired
	protected ITripService iTripService;

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

}
