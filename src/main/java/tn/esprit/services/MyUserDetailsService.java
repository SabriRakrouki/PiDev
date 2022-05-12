package tn.esprit.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.esprit.entities.User;
import tn.esprit.repositories.UserRepository;
import tn.esprit.security.MyUserDetails;
@Service
public class MyUserDetailsService implements UserDetailsService{
@Autowired
	UserRepository userRepository;
	@Override
public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		User user = userRepository.getByUserName(userName);
		if(user==null){
			throw new UsernameNotFoundException("Could not find user : " + userName);

		}
		return new MyUserDetails(user);
	}
}
