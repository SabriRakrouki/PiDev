package tn.esprit.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.Employee;
import tn.esprit.entities.Location;
import tn.esprit.entities.Trip;
import tn.esprit.repositories.EmployeeRepository;
import tn.esprit.repositories.TripRepository;
import tn.esprit.services.MatchAlgorithm;
import tn.esprit.services.MatchingAlgoImp;

@RestController
@RequestMapping("/Matching")
public class MatchingController {
	@Autowired
	MatchAlgorithm matchingAlgoImp;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	TripRepository tripRepository;

	@GetMapping("/{idtrip}/{emp}")
	@ResponseBody
	public Set<Employee> getMatching(@PathVariable("emp") int iduser, @PathVariable("idtrip") int idtrip) {

		Employee employee = employeeRepository.findById(iduser);
		Trip trip = tripRepository.findById(idtrip).orElse(null);

		return matchingAlgoImp.getAllTheMatchingPeople(employee, trip);

	}

}
