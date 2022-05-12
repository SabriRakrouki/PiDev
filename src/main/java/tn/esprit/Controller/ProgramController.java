package tn.esprit.Controller;

import java.util.List;

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

import tn.esprit.entities.Program;
import tn.esprit.entities.Trip;
import tn.esprit.services.IProgramSerivce;

@RestController
@RequestMapping("/api/v1/program")
public class ProgramController {

	private final IProgramSerivce iProgramSerivce;

	public ProgramController(IProgramSerivce iProgramSerivce) {
		this.iProgramSerivce = iProgramSerivce;
	}

	@GetMapping(value = "/getAll", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<List<Program>> getAll() {
		return ResponseEntity.ok(iProgramSerivce.getAllProgram());
	}

	@PostMapping("/addProgram")
	@ResponseBody
	public ResponseEntity<String> CreateProgram(@RequestBody Program program) {
		iProgramSerivce.AddProgram(program);
		return ResponseEntity.status(201).body("Program created");
	}

	@PutMapping("/updateProgram")
	@ResponseBody
	public ResponseEntity<String> updateProgram(@RequestBody Program program) {
		iProgramSerivce.UpdateProgram(program);
		return ResponseEntity.ok("Program updated");
	}

	@DeleteMapping("/deleteProgram")
	@ResponseBody
	public ResponseEntity<String> deleteProgram(@RequestBody Program program) {
		iProgramSerivce.DeleteProgram(program);
		return ResponseEntity.ok("Program Deleted");
	}
	@GetMapping("/getprogrambytrip/{idTrip}")
	@ResponseBody
	public List<Program> getTripByid(@PathVariable("idTrip") int idTrip ) {
		return iProgramSerivce.getAllProgramTrip(idTrip);
	}
	@DeleteMapping("/deleteProgramTrip/{idTrip}")
	@ResponseBody
	public ResponseEntity<String> DeleteProgramByTrip(@PathVariable("idTrip") int idTrip) {
		iProgramSerivce.DeleteProgramByTrip(idTrip);
		return ResponseEntity.ok("Program Deleted");
	}
	@PostMapping("/addProgramTrip/{idTrip}")
	@ResponseBody
	public ResponseEntity<String> CreateProgram(@RequestBody Program program,@PathVariable("idTrip") int idTrip) {
		iProgramSerivce.AddProgramByTrip(program,idTrip);
		return ResponseEntity.status(201).body("Program created");
	}
	@PutMapping("/updateProgramTrip/{idTrip}")
	@ResponseBody
	public ResponseEntity<String> updateProgram(@RequestBody Program program,@PathVariable("idTrip") int idTrip) {
		iProgramSerivce.UpdateProgramByTrip(program,idTrip);
		return ResponseEntity.ok("Program updated");
	}
	@DeleteMapping("/DeleteProgramById/{idProgram}")
	@ResponseBody
	public ResponseEntity<String> DeleteProgramById(@PathVariable("idProgram") int idProgram) {
		iProgramSerivce.DeleteProgramById(idProgram);
		return ResponseEntity.ok("Program Deleted");
	}

}
