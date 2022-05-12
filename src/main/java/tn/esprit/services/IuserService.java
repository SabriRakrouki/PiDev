package tn.esprit.services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import tn.esprit.entities.StaticOfUser;
import tn.esprit.entities.User;

public interface IuserService {

	public Date weekDate(Date date);
	
	public int userAddinWeek() throws ParseException;
	
	public StaticOfUser addUserStatic() throws ParseException;
	
	public List<User> getAllUsers();
	
}
