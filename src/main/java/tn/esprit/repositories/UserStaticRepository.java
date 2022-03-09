package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.StaticOfUser;

@Repository
public interface UserStaticRepository extends JpaRepository<StaticOfUser,Integer> {

}
