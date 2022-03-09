package tn.esprit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entities.ArchiveComplaints;
import tn.esprit.repositories.ArchiveComplaintRepository;

@Service
public class ArchiveComplaintsServiceImpl implements IArchiveComplaintsService {
	@Autowired
	ArchiveComplaintRepository archivecomplaintsRepository;
	
	
	@Override
	public ArchiveComplaints AddArchiveComplaint(ArchiveComplaints archivecomplaint) {
		archivecomplaintsRepository.save(archivecomplaint);
		return archivecomplaint;
		
	}
	
	@Override
	public List<ArchiveComplaints> getAllArchiveComplaints() {
		return archivecomplaintsRepository.findAll();
	}

	@Override
	public ArchiveComplaints FindArchiveComplaintsById(int id) {
ArchiveComplaints c = archivecomplaintsRepository.findById(id).get();
		
		return c;
	}

}
