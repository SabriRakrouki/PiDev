package tn.esprit.services;

import java.text.ParseException;
import java.util.Date;

import tn.esprit.entities.StaticOfUser;

public interface IuserService {

	public Date weekDate(Date date);
	
	public int userAddinWeek() throws ParseException;
	
	public StaticOfUser addUserStatic() throws ParseException;
	
	
	
}
