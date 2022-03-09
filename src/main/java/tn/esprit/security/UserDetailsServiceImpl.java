package tn.esprit.security;


import javax.security.auth.login.CredentialNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.entities.User;
import tn.esprit.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return UserDetailsImpl.build(user);
	}
	 public void updateResetPasswordToken(String token, String email) throws CredentialNotFoundException {
	        User user = userRepository.findByEmail(email);
	        if (user != null) {
	        	user.setResetPasswordToken(token);
	        	userRepository.save(user);
	        } else {
	            throw new CredentialNotFoundException("Could not find any customer with the email " + email);
	        }
	    }
	     
	    public User getByResetPasswordToken(String token) {
	        return userRepository.findByResetPasswordToken(token);
	    }
	     
	    public void updatePassword(User user, String newPassword) {
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String encodedPassword = passwordEncoder.encode(newPassword);
	        user.setPassword(encodedPassword);
	         
	        user.setResetPasswordToken(null);
	        userRepository.save(user);
	    }
	   
}
