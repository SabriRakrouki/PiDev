package tn.esprit.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.Employee;
import tn.esprit.entities.Trip;
import tn.esprit.services.MatchAlgorithm;
import tn.esprit.services.MatchingAlgoImp;

@RestController
@RequestMapping("/Matching")
public class MatchingController {
	@Autowired 
	MatchAlgorithm matchingAlgoImp;
	
	
	
	@GetMapping("/{trip}/{emp}")
	@ResponseBody
	public Set<Employee> getMatching(@RequestBody Employee employee){
		
		
		
		return matchingAlgoImp.getAllTheMatchingPeople(employee);
		
	}
	
	
	

}
