package tn.esprit.services;

import java.util.List;
import java.util.Properties;

import javax.transaction.Transactional;

//import org.mapstruct.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.entities.Complaint; 
import tn.esprit.entities.Entreprise;
import tn.esprit.repositories.ComplaintRepository;
import tn.esprit.repositories.EntrepriseRepository;




@Service
@Transactional
public class ComplaintServiceImpl implements IComplaintService {
	@Autowired 
	ComplaintRepository complaintRepository;
	@Autowired
	EntrepriseRepository entrepriseRepository;
	
	
	
	/*public ComplaintServiceImpl(ComplaintRepository complaintRepository) {
		// TODO Auto-generated constructor stub
		this.complaintRepository = complaintRepository;
	}*/
	
	
	@Override
	public List<Complaint> getAllComplaint() {
		return complaintRepository.findAll();
	}

	@Override
	public Complaint AddComplaint(Complaint complaint) {
		complaintRepository.save(complaint);
		return complaint;
		
	}

	

	@Override
	public void DeleteComplaint(int id) {
		//complaintRepository.delete(complaintRepository.getById(id));
		complaintRepository.deleteById(id);
	}

	@Override
	public Complaint FindComplaintById(int id) {
		//return complaintRepository.getById(id);
Complaint c = complaintRepository.findById(id).get();
		
		return c;
	}


	@Override
	public int UpdateComplaint(Complaint complaint) {
		complaintRepository.save(complaint);
		return complaint.getEmployeId();
	}

	@Override
	@Transactional
	public void assignReclamationToEntreprise(int com, int entrepid) {
		
		Entreprise entrp=entrepriseRepository.getById(entrepid);
		Complaint comp=complaintRepository.getById(com);
		comp.setEntreprise(entrp);
					
	}

	
	
	
		

}
