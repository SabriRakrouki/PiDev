package tn.esprit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.Comment;
import tn.esprit.entities.Post;
import tn.esprit.entities.Topic;


@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
	
	
	long count();
	@Query(value="select topic from post p JOIN p.topics Topic  where  p.topic-id= ?1",nativeQuery = true)
	List<Topic> findByTopic(int id_topic);

	

}
