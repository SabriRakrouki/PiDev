package tn.esprit.services;

import java.util.List;

import org.springframework.mail.javamail.JavaMailSender;

import tn.esprit.entities.Complaint;


public interface IComplaintService {
	public List<Complaint> getAllComplaint();

	public Complaint AddComplaint(Complaint complaint);

	public int UpdateComplaint(Complaint complaint);

	public void DeleteComplaint(int id);

	public Complaint FindComplaintById(int id);
	public void assignReclamationToEntreprise(int com,int entrepid);
	
}
