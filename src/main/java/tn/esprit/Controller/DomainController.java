package tn.esprit.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.Domain;
import tn.esprit.services.DomainServiceImp;
import tn.esprit.services.IDomainService;


@RestController
@RequestMapping("/domain")
public class DomainController {
	@Autowired
	DomainServiceImp DomainService;
	
	@PostMapping("/addDomain")
	@ResponseBody
	public Domain addDomain(@RequestBody Domain d) {
		
			return DomainService.AddDomain(d);
		
	}
	
	@PutMapping("/updateDomain/{id}")
	@ResponseBody
	public Domain updateDomain( @RequestBody Domain d,@PathVariable("id")int id) {
		
			return DomainService.UpdateDomain(d,id);
		
		
	}
	
	@DeleteMapping("/deletDomain/{id}")
	@ResponseBody
	public Domain deletDomain( @PathVariable("id")  int id) {
		
			return DomainService.DeleteDomain(id);
		
	}
	
	@RequestMapping("/getallDomain")
	@ResponseBody
	public List<Domain> getallDomain( ) {
		
			return DomainService.GetAllDomains();
		
	}
	
	@PutMapping("/afftcterent/{identre}")
	@ResponseBody
	public void afftcterent( @RequestBody Domain d,@PathVariable("identre")int identre) {
			System.out.println("**** "+identre);
			 DomainService.affecterDomainEntrePrise(d, identre);
		
		
	}
	@GetMapping("/retrieveDomainsparEntreprises/{entreprise-id}")
	@ResponseBody
	public Set<Domain>retrieveAllDomainsparEntreprise(@PathVariable("entreprise-id") int entrepriseId) {

		 return DomainService.GetAllDomainsparent(entrepriseId);
	}

}
