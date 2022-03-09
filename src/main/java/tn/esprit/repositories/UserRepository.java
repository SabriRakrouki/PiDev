package tn.esprit.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	public User getByUsername(String username); 
	    public User findByEmail(String email); 
	    public User findByResetPasswordToken(String token);
	    
	    
	    
	    
	    
	    
	    @Query("SELECT count(u) FROM User as  u WHERE u.dateCreation BETWEEN cast(:date1 as date)  AND cast(:date2  as date)")
	   
        public int UserStatistic(@Param("date1")String date1,@Param("date2")String date2);
}
