package tn.esprit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.entities.Domain;
import tn.esprit.repositories.DomainRepository;

@Service
public class DomainServiceImp implements IDomainService {
	@Autowired
	DomainRepository domainRepository;

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

}
