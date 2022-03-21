package tn.esprit.Controller;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.security.auth.login.CredentialNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.entities.User;
import tn.esprit.payload.MessageResponse;
import tn.esprit.repositories.UserRepository;
import tn.esprit.security.UserDetailsServiceImpl;
import tn.esprit.security.Utility;

@RestController
@RequestMapping("/api/v1/password")
public class ForgotPasswordController {

	private final JavaMailSender mailSender;
	private final PasswordEncoder passwordEncoder;

	private final UserDetailsServiceImpl userDetailService;

	private final UserRepository userRepository;

	public ForgotPasswordController(JavaMailSender mailSender, UserDetailsServiceImpl userDetailService,
			UserRepository userRepository) {

		this.mailSender = mailSender;
		this.passwordEncoder = new BCryptPasswordEncoder();
		this.userDetailService = userDetailService;
		this.userRepository = userRepository;
	}

	@PostMapping("/user/resetPassword")
	public ResponseEntity<?> resetPassword(HttpServletRequest request, @RequestParam("username") String username)
			throws CredentialNotFoundException, UnsupportedEncodingException, MessagingException {
		User user = userRepository.getByUsername(username);
		if (user == null) {
			return ResponseEntity.ok(new MessageResponse("Error: user innexsitant!"));
		}
		String token = UUID.randomUUID().toString();
		userDetailService.updateResetPasswordToken(token, user.getEmail());
		String resetPasswordLink = Utility.getSiteURL(request) + "/password/reset_password?token=" + token;
		sendEmail(user.getEmail(), resetPasswordLink);
		return ResponseEntity
				.ok(new MessageResponse("We have sent a reset password link to your email. Please check !"));
	}

	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("esprittravelup@gmail.com", "TRAVELUP");
		helper.setTo(recipientEmail);

		String subject = "Here's the link to reset your password";

		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + link
				+ "\">Change my password</a></p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

	@PutMapping("/reset_password")
	public ResponseEntity<?> processResetPassword(HttpServletRequest request,
			@RequestParam("password") String userPassword) {
		String token = request.getParameter("token");
		User user = userDetailService.getByResetPasswordToken(token);
		if (user == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: user innexsitant!"));

		} else {
			user.setPassword(passwordEncoder.encode(userPassword));
			user.setResetPasswordToken(null);
			userRepository.save(user);
			return ResponseEntity.ok(new MessageResponse("We have reset password. Please check !"));
		}

	}
}