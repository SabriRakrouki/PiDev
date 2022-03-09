package tn.esprit.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import tn.esprit.entities.ArchiveComplaints;
import tn.esprit.entities.Complaint;
import tn.esprit.services.IArchiveComplaintsService;
import tn.esprit.services.IComplaintService;
import tn.esprit.services.IEntrepriseService;
import tn.esprit.services.PDFGeneratorService;


@RestController
public class ComplaintController {
	@Autowired	
	IComplaintService complaintService;
	@Autowired	
	IEntrepriseService entrepriseService;
	@Autowired	
	IArchiveComplaintsService archivecomplaintsService;
	//@Autowired	
	//public PDFGeneratorService pdfGeneratorService;
	
	@GetMapping("/getComplaints")
	@ResponseBody
	public List<Complaint> getComplaints(){
		List<Complaint> list=complaintService.getAllComplaint();
		//return ClientServise.retrieveAllClients();
		return list;
	}
	
	@PostMapping("/addComplaint")
	@ResponseBody 
	public Complaint addComplaint(@RequestBody Complaint Complaint) {
		Complaint complaint=complaintService.AddComplaint(Complaint); 
	    return complaint;
	}
	
	@PutMapping("/complaintmodif/{compid}")
	public void updateClient(@RequestBody Complaint complaint,@PathVariable("compid") int compid){
	     //complaintService.UpdateComplaint(complaint);
	     int empid;
	     empid=complaintService.UpdateComplaint(complaint);
	     if(complaintService.FindComplaintById(compid).getState().toString()=="Accepted"){
	     System.out.println(empid);
	     int entrepriseid;
	      entrepriseid=entrepriseService.FindEntrepriseByemployeeId(empid).getId();
	    
	     System.out.println(entrepriseid);
	     
	     complaintService.assignReclamationToEntreprise(compid,entrepriseid);
	     
	     }
	    
	}
	
	
	@DeleteMapping("/remove-complaint/{complaint-id}")
	@ResponseBody
	public void removeClient(@RequestBody ArchiveComplaints archivecomp,@PathVariable("complaint-id") Integer complaintId) {
		Complaint cmp=complaintService.FindComplaintById(complaintId);
		ArchiveComplaints ar=archivecomplaintsService.AddArchiveComplaint(archivecomp);
		int idd= ar.getId();
        ArchiveComplaints archcomp=archivecomplaintsService.FindArchiveComplaintsById(idd);
       
        archcomp.setComplaint(cmp);
        //archcomp.getComplaints().add(cmp);
		complaintService.DeleteComplaint(complaintId); 
		

	}
	
	
	@GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=complaints_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Complaint> listComplaints = complaintService.getAllComplaint();
        PDFGeneratorService exporter = new PDFGeneratorService(listComplaints); 
        exporter.export(response);
         
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public ComplaintController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/pdf/generate")
    public void generatePDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(response);
    }*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
