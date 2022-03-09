package tn.esprit.services;

import java.util.List;


import tn.esprit.entities.Entreprise;

public interface IEntrepriseService {
	public void addEntreprise(Entreprise entreprise);
	public List<Entreprise> retrieveAllEntreprise();
	public void deleteEntreprise(int id);
	public Entreprise updateEntreprise(Entreprise entreprise);
	public Entreprise FindEntrepriseById(int registre);
	public Entreprise FindEntrepriseByemployeeId(int emplid);
	public List<Entreprise> getAllEntreprise();


	
	
}
