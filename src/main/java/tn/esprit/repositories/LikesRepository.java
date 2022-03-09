package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.*;

@Repository
public interface LikesRepository extends JpaRepository<Like, Long>{

/*	@Query(value ="SELECT posts_id_post FROM like GROUP BY posts_id_post ORDER BY COUNT(like.id) DESC LIMIT 1", nativeQuery = true)
	public int getPostsWithMostLikes();*/

}
