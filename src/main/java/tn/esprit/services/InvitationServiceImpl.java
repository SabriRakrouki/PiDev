package tn.esprit.services;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import tn.esprit.entities.Entreprise;
import tn.esprit.entities.Invitation;
import tn.esprit.entities.State;
import tn.esprit.repositories.InvitationRepository;
import tn.esprit.repositories.UserRepository;

@Service
public class InvitationServiceImpl implements IInvitationService {
	@Autowired
	InvitationRepository invitationRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public Invitation save(Invitation invitation) {
		return invitationRepository.save(invitation);
	}

	@Autowired
	private JavaMailSender mailSender;

	public List<Invitation> ReadDataFromExcel(String excelPath)
			throws EncryptedDocumentException, InvalidFormatException, IOException, MessagingException {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		Entreprise user = (Entreprise) userRepository.findByUsername(username).orElse(null);
		Workbook workbook = WorkbookFactory.create(new File(excelPath));
		// Retrieving the number of sheets in the Workbook
		System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
		System.out.println("Retrieving Sheets using for-each loop");
		for (Sheet sheet : workbook) {
			System.out.println("=> " + sheet.getSheetName());
			for (Row row : sheet) {

				String email = row.getCell(1).getStringCellValue();

				Invitation inv = new Invitation();
				inv.setEmail(email);
				inv.setDate(Calendar.getInstance().getTime());
				inv.setState(State.Waiting);
				inv.setEntreprise(user);

				invitationRepository.save(inv);
				sendEmail(inv);
			}

		}

		return null;
	}

	public void sendEmail(Invitation inv) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("esprittravelup@gmail.com", "TRAVELUP");
		helper.setTo(inv.getEmail());
		Entreprise entreprise = inv.getEntreprise();
		String link = "http://localhost:8091/travelup/signup/employee";
		String subject = "Join the TRAVELUP community";
		String content = "<p>Hello everybody,</p>" + "<p>The " + entreprise.getName() + " company invites you "
				+ "to create an account on the TRAVELUP platform.</p>" + "<p>you find the direct link below:</p>"
				+ "<p><a href=\"" + link + "\">Create your account</a></p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

	@Override
	public List<Invitation> retrieveInvitation(int idEntreprise) {
		// TODO Auto-generated method stub
		return invitationRepository.findByEntrepriseId(idEntreprise);
	}
}
