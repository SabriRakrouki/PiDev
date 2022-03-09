package tn.esprit.services;

import java.util.List;
import java.util.Set;

import tn.esprit.entities.Domain;

public interface IDomainService {
	Domain AddDomain(Domain d);
	Domain UpdateDomain(Domain d,int id);
	Domain DeleteDomain(int id);
	List<Domain> GetAllDomains();
	void affecterDomainEntrePrise(Domain d,int idEntre);
	Set<Domain> GetAllDomainsparent(int ident);


}
