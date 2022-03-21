package tn.esprit.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SentVerifEmail {

	private final JavaMailSender emailSender;

	public SentVerifEmail(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

	public String sendHtmlEmail(long code, String email) throws MessagingException {

		MimeMessage message = emailSender.createMimeMessage();

		boolean multipart = true;

		MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

		String htmlMsg = "\r\n" + "<html>\n" + "                      <body> \n"
				+ "                         <div align='center'>\n"
				+ "                            <div style='text-align:center;'>\n"
				+ "                          Email Verification Code:    \n" + "                        " + code
				+ "                        \n " + "                           </div>\n" + "   \n"
				+ "                            <br/><br/>\n"
				+ "                              Please verify your email address using the code above to complete the account setup. \n"
				+ "                      </body>\n" + "                   </html>\n";

		message.setContent(htmlMsg, "text/html");

		helper.setTo(email);

		helper.setSubject("Email Verification");

		this.emailSender.send(message);

		return "Email Sent!";
	}
}
