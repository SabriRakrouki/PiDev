package tn.esprit.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.entities.Domain;
import tn.esprit.entities.Entreprise;
import tn.esprit.repositories.DomainRepository;
import tn.esprit.repositories.EmployeeRepository;
import tn.esprit.repositories.EntrepriseRepository;

@Service
public class DomainServiceImp implements IDomainService {
	@Autowired
	DomainRepository domainRepository;
	@Autowired
	EmployeeRepository empRepo;
	@Autowired
	EntrepriseRepository entrepo;

	@Override
	public Domain AddDomain(@RequestBody Domain d) {
		domainRepository.save(d);

		return d;
	}

	@Override
	public Domain UpdateDomain(Domain d,int id) {
		domainRepository.save(d);

		return d;
	}

	@Override
	public Domain DeleteDomain(int id) {
		Domain d=domainRepository.findById(id).get();
		 domainRepository.delete(d);
		return d;
	}

	@Override
	public List<Domain> GetAllDomains() {
		
		return domainRepository.findAll();
	}

	@Override
	public void affecterDomainEntrePrise(Domain newdomain, int idEntre) {
		
		
		Entreprise e=entrepo.getById(idEntre);
		Set<Entreprise> se=new HashSet<Entreprise>();
		Set<Domain> sd=new HashSet<Domain>();
		Domain domain=domainRepository.findBynameDomain(newdomain.getNameDomain());
		if(domain==null) {
			se.add(e);
			sd.add(newdomain);
			
		
			if(e.getDomains().size()==0) {
				newdomain.setEntreprises(se);
				domainRepository.save(newdomain);
			}
			else
				if(e.getDomains().size()!=0)
			{
					e.getDomains().add(newdomain);
				domainRepository.save(newdomain);
				entrepo.save(e);
			}
			
		}
		else {
			
			e.getDomains().add(domain);

			entrepo.save(e);
			
		}
		}
		
	

	@Override
	public Set<Domain> GetAllDomainsparent(int ident) {
		Entreprise e = entrepo.findById(ident).get();
		return e.getDomains();


	}

}
