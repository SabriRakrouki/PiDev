package tn.esprit.services;

import java.util.HashMap;
import java.util.List;

public interface ProfilServiceINT {
	public void MdpOublier(String Username);
    public Boolean verifaccount(long code,int id);
    public Boolean VerifPassword(String newPassword,String username,String password);
    public HashMap<String, Integer> Statestique(int IdEntreprise);
}
