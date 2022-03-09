package tn.esprit.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.Complaint;
import tn.esprit.entities.Trip;
import tn.esprit.services.ITripService;
import tn.esprit.services.PDFGeneratorService;

@RestController
@RequestMapping("/trip")
public class TripController {
	@Autowired
	protected ITripService iTripService;
	@Autowired
    private JavaMailSender mailSender;
	

	@RequestMapping("/addTrip")
	@ResponseBody
	public ResponseEntity<String> addTrip( Trip trip) {
		
			iTripService.AddTrip(trip);
			return ResponseEntity.ok("Trip created");
		
		
	}

	@DeleteMapping("/deleteTrip/{idTrip}")
	@ResponseBody
	
	public ResponseEntity<String>  DeleteTrip(@PathVariable("idTrip") int idTrip) {
		iTripService.DeleteTrip(idTrip);
		return ResponseEntity.status(200).body("Trip Deleted");
		
		
	}
	
	
	
	@GetMapping("/getvoyages")
	@ResponseBody
	public List<Trip> getTrips(){
		List<Trip> list=iTripService.getAllTrip();
		//return ClientServise.retrieveAllClients();
		return list;
	}
	
	
	
	@GetMapping("/getrating/{idrating}")
	@ResponseBody
	public String getrating(@PathVariable("idrating") int idrating){		
		String rating=iTripService.FindTripById(idrating).getRating();
		return rating;
	}
	
	
	public void sendEmail(String recipientEmail)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();              
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setFrom("esprittravelup@gmail.com", "TRAVELUP");
        helper.setTo(recipientEmail);
         
        String subject = "NOUVEAU RATING";
         
        String content = "Bonjour,un voyage a été noté par un utilisateur!!!";
         
        helper.setSubject(subject);
         
        helper.setText(content, true);
         
        mailSender.send(message);
    }


    
	@PutMapping("/notervoyage/{voyageid}")
	public void NoterVoyage(@PathVariable("voyageid") int voyageid,@RequestParam(value="attribution") Float attribution) throws UnsupportedEncodingException, MessagingException{
		 Trip tripp=iTripService.FindTripById(voyageid);
		 iTripService.NoterVoyage(tripp,attribution);
		 System.out.println(tripp.getAttribution());
		 System.out.println(tripp.getCompteur());
		 System.out.println(tripp.getTotalattribution());
		 System.out.println(tripp.getNote()); 
		 sendEmail("safa.chaouali.750@gmail.com");
		 //@EventListener(ApplicationReadyEvent.class)
		 /*senderService.sendSimpleMessage("safa.chaouali.750@gmail.com", "rating","Bonjouur!!! un voyage a été noté par un utilisateur");*/
		 
			 
	    
	}
	
}
