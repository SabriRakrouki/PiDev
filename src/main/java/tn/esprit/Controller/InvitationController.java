package tn.esprit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.Invitation;
import tn.esprit.entities.User;
import tn.esprit.repositories.UserRepository;
import tn.esprit.services.InvitationServiceImpl;

@RestController
@RequestMapping("/api/v1/invitation")
public class InvitationController {
	
	private final InvitationServiceImpl invitationServiceImpl;
	
	private final UserRepository userRepository;

	public InvitationController(InvitationServiceImpl invitationServiceImpl, UserRepository userRepository) {
		
		this.invitationServiceImpl = invitationServiceImpl;
		this.userRepository = userRepository;
	}

	@PostMapping("/upload")
	public void upload(@RequestParam("path") String path) throws Exception {
		invitationServiceImpl.ReadDataFromExcel(path);
	}

	@GetMapping("/retrieveInvitation")

	public List<Invitation> retrieveInvitation() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		User us = userRepository.findByUsername(username).orElse(null);
		int idEntreprise = us.getId();
		return invitationServiceImpl.retrieveInvitation(idEntreprise);
	}
}
