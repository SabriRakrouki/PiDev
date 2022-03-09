package tn.esprit.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entities.Complaint;
import tn.esprit.entities.Entreprise;
import tn.esprit.repositories.ComplaintRepository;
import tn.esprit.repositories.EmployeRepository;
import tn.esprit.repositories.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Autowired
	EmployeRepository employeeRepository;
	@Autowired
	ComplaintRepository complaintRepository;
	
	
	@Override
	public List<Entreprise> getAllEntreprise() {
		return entrepriseRepository.findAll();
	}

	@Override
	public Entreprise AddEntreprise(Entreprise entreprise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entreprise UpdateEntreprise(Entreprise entreprise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void DeleteEntreprise(int registre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entreprise FindEntrepriseById(int registre) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public Entreprise FindEntrepriseByemployeeId(int emplid) {
		return entrepriseRepository.findByEmployeesId(emplid);	
		
	}

	//@Override
	//@Transactional
	/*public void assignReclamationToEntreprise(int com,int entrepid) {
		Entreprise entrp=entrepriseRepository.getById(entrepid);
		Complaint comp=complaintRepository.getById(com);
					entrp.getComplaints().add(comp);
					complaintRepository.save(comp);
					entrepriseRepository.save(entrp);
					
					
					Complaint cmp=complaintRepository.getById(com);
					List<Entreprise> entreprises=entrepriseRepository.findAll();
					for (Entreprise en : entreprises){
					
							if (en.getId()==entrepid){
								en.getComplaints().add(cmp);
								complaintRepository.save(cmp);
								
							}
						
					}					
					
	}*/
	
	
	
	

	
}
