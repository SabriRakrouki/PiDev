package tn.esprit.services;

import java.util.List;

import tn.esprit.entities.Admin;

public interface IAdminService {
	public void addAdmin(Admin admin);
	public List<Admin> retrieveAllAdmin();
	public void deleteAdmin(int id);
	public Admin updateAdmin(Admin admin);
}
