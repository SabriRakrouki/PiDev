package tn.esprit.services;

import java.util.List;


import tn.esprit.entities.Entreprise;

public interface IEntrepriseService  {
	
	public List<Entreprise> getAllEntreprise();

	public Entreprise AddEntreprise(Entreprise entreprise);

	public Entreprise UpdateEntreprise(Entreprise entreprise);

	public void DeleteEntreprise(int registre);

	public Entreprise FindEntrepriseById(int registre);
	public Entreprise FindEntrepriseByemployeeId(int emplid);
	//public void assignReclamationToEntreprise(int com,int entrepid);
	
}
