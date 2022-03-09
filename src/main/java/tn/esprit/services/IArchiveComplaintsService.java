package tn.esprit.services;

import java.util.List;

import tn.esprit.entities.ArchiveComplaints;

public interface IArchiveComplaintsService {
	public ArchiveComplaints AddArchiveComplaint(ArchiveComplaints archivecomplaint);
	public List<ArchiveComplaints> getAllArchiveComplaints();
	public ArchiveComplaints FindArchiveComplaintsById(int id);
}
