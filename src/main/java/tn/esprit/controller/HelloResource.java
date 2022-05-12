package tn.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import tn.esprit.entities.User;
import tn.esprit.services.UserService;
@RestController
public class HelloResource {
	@Autowired
	UserService UserService;
	@RequestMapping("/admin")
	public RedirectView viewAdminPage(Model model) {
		List<User> listUser = UserService.listAll();
		model.addAttribute("listUser",listUser);
		 RedirectView rv = new RedirectView();
	        rv.setUrl("index");
		return rv;
	}

}
