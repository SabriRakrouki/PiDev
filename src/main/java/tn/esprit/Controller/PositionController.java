package tn.esprit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.Domain;
import tn.esprit.entities.Position;
import tn.esprit.services.IPositionService;

@RestController
@RequestMapping("/api/v1/position")
public class PositionController {

	private final IPositionService PositionService;

	public PositionController(IPositionService positionService) {

		PositionService = positionService;
	}

	@PostMapping("/addPosition")
	@ResponseBody
	public Position addPosition(@RequestBody Position p) {
		System.out.println(p.getPotionName());
		return PositionService.AddPosition(p);

	}

	@PutMapping("/updatePosition/{id}")
	@ResponseBody
	public Position updatePosition(@RequestBody Position d) {
		System.out.println(d.getPotionName());
		return PositionService.UpdatePosition(d);

	}

	@DeleteMapping("/deletPosition/{id}")
	@ResponseBody
	public Position deletPosition(@PathVariable("id") int id) {

		return PositionService.DeletePosition(id);

	}

	@RequestMapping("/getallPosition")
	@ResponseBody
	public List<Position> getallPosition() {

		return PositionService.GetAllPosition();

	}

	@PutMapping("/afftctPOs/{idempl}")
	@ResponseBody
	public void afftctPOs(@RequestBody Position p, @PathVariable("idempl") int idempl) {

		PositionService.affecterPositionEMPL(p, idempl);

	}

}
