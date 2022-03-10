package tn.esprit.services;
import java.util.List;

import tn.esprit.entities.Invitation;
public interface IInvitationService {
	public Invitation save (Invitation invitation);
	public List<Invitation> retrieveInvitation(int idEntreprise);
}
