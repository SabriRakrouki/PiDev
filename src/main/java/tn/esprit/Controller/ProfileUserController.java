package tn.esprit.Controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.User;
import tn.esprit.repositories.UserRepository;
import tn.esprit.services.ProfilServiceINT;

@RestController
@RequestMapping("/Profile")
public class ProfileUserController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProfilServiceINT uservice;	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	public static String password;

	@PutMapping("/changePassword/{Password}/{newPassword}/{username}")
    public Boolean changePswordverif1(@PathVariable("newPassword") String newPassword,
            @PathVariable("username") String username, @PathVariable("Password") String password) {

		User u = userRepository.findByUsername(username).get();
        System.out.println("**IN CONTROLLER**");
        System.out.println("" + u.toString());
        System.out.println("**IN CONTROLLER**");

        Boolean test2 = null;

        test2 = uservice.VerifPassword(newPassword, username, password);
        System.out.println("test2" + test2);
        if (test2) {
            this.password=newPassword;
            System.out.println("password changing...");
            return true;
        } else {
            return false;
        }

    }

    @PutMapping("/userCode/{code}/{id}")
    public void changepas2(@PathVariable("code") long code,@PathVariable("id") int id){
    	User u = userRepository.findById(id).get();
        Boolean test = uservice.verifaccount(code, u.getId());


        if (test) {
            u.setPassword(passwordEncoder.encode( this.password));
            userRepository.save(u);
            System.out.println("password Changed successfully...");

    }
      
    }
    
    @GetMapping("/stat/{idENT}")
    public HashMap<String, Integer> stat(@PathVariable("idENT") int identreprise){
     
    	 return uservice.Statestique(identreprise);
    	
    }
}
