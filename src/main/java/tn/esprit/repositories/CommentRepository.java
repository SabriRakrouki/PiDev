package tn.esprit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.Comment;
import tn.esprit.entities.Post;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	long count();
    
	//@Query("select post from comment c JOIN c.posts Post  where  c.post-id= ?1")
	//List<Comment> findByPost(int id_post);

}
