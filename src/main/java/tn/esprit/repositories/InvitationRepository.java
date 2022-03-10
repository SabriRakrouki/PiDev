package tn.esprit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entities.Invitation;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Integer> {
    public Invitation findByEmail(String email);
    public List<Invitation> findByEntrepriseId(int idEntreprise);
}
