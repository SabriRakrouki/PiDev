package tn.esprit.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.User;
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	@Query("SELECT u from User u Where u.userName =:userName")	
	public User getByUserName(@Param("userName")String userName);
}
