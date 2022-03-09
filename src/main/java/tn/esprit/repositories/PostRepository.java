package tn.esprit.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entities.Post;


@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
	
	
	
	

	

}
