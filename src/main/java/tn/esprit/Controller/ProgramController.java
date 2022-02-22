package tn.esprit.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.Program;
import tn.esprit.services.IProgramSerivce;

@RestController
@RequestMapping("/program")
public class ProgramController {

	protected IProgramSerivce iProgramSerivce;
	
	@GetMapping( value = "/getAll",consumes = "application/json")
	@ResponseBody
	public ResponseEntity<List<Program>> getAll(){
		return ResponseEntity.ok(iProgramSerivce.getAllProgram());
	}
	@PostMapping("/addProgram")
	@ResponseBody
	public ResponseEntity<String> CreateProgram(@RequestBody Program program){
		iProgramSerivce.AddProgram(program);
		return ResponseEntity.status(201).body("Program created");
	}
	
	@PutMapping("/updateProgram")
	@ResponseBody
	public ResponseEntity<String> updateProgram(@RequestBody Program program){
		iProgramSerivce.UpdateProgram( program);
		return ResponseEntity.ok("Program updated");
	}
	@DeleteMapping("/deleteProgram")
	@ResponseBody
	public ResponseEntity<String> deleteProgram(@RequestBody Program program){
		iProgramSerivce.DeleteProgram( program);
		return ResponseEntity.ok("Program Deleted");
	}
	
}
