package tn.esprit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entities.User;
import tn.esprit.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository UserRepository;
	
	public List<User> listAll(){
		return (List<User>) UserRepository.findAll();
	}
}
