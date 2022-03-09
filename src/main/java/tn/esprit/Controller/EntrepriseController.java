package tn.esprit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.entities.Entreprise;
import tn.esprit.services.IEntrepriseService;

@RestController
public class EntrepriseController {
	@Autowired		
	IEntrepriseService entrepriseService;
	@GetMapping("/getEntreprises")
	@ResponseBody
	public List<Entreprise> getEntreprises(){
		List<Entreprise> list=entrepriseService.getAllEntreprise();
		//return ClientServise.retrieveAllClients();
		return list;
	}
}
