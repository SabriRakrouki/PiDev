package tn.esprit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.Entreprise;
import tn.esprit.services.EntrepriseServiceImpl;
import tn.esprit.services.IEntrepriseService;

@RestController
public class EntrepriseController {
	@Autowired
    private EntrepriseServiceImpl entrepriseService;
	//http://localhost:8090/travelup/back/add-entreprise
	@PostMapping("/add-entreprise")
	@ResponseBody
	public void addEntreprise(@RequestBody Entreprise entreprise) {
		entrepriseService.addEntreprise(entreprise);
	}
	//http://localhost:8090/travelup/back/retrieveEntreprises	 
	@GetMapping("/retrieveEntreprises")
	@ResponseBody
	public List<Entreprise>retrieveAllEntreprise() {
	return entrepriseService.retrieveAllEntreprise();
	}
	//http://localhost:8090/travelup/back/remove-entreprise/3
		@DeleteMapping("/remove-entreprise/{entreprise-id}")
		@ResponseBody
		public void removeEntreprise(@PathVariable("entreprise-id") int entrepriseId) {
			entrepriseService.deleteEntreprise(entrepriseId);
		}
		//http://localhost:8090/travelup/back/updateEntreprise/3
		@PutMapping("/updateEntreprise/{id}")
		@ResponseBody
		public Entreprise updateEntreprise(@RequestBody Entreprise entreprise) {
			return entrepriseService.updateEntreprise(entreprise);
		}
}
