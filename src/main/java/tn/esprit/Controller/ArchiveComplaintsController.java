package tn.esprit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.ArchiveComplaints;
import tn.esprit.entities.Complaint;
import tn.esprit.services.IArchiveComplaintsService;

@RestController
@RequestMapping("api/v1/archive")
public class ArchiveComplaintsController {

	private final IArchiveComplaintsService archivecomplaintsService;

	public ArchiveComplaintsController(IArchiveComplaintsService archivecomplaintsService) {
		super();
		this.archivecomplaintsService = archivecomplaintsService;
	}

	@GetMapping("/getarchive")
	@ResponseBody
	public List<ArchiveComplaints> getArchiveComplaints() {
		List<ArchiveComplaints> list = archivecomplaintsService.getAllArchiveComplaints();
		// return ClientServise.retrieveAllClients();
		return list;
	}

	@PostMapping("/addarchive")
	@ResponseBody
	public ArchiveComplaints addArchive(@RequestBody ArchiveComplaints archivecomp) {
		ArchiveComplaints arch = archivecomplaintsService.AddArchiveComplaint(archivecomp);
		return arch;
	}

}
