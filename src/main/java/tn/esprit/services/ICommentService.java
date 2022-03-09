package tn.esprit.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import tn.esprit.entities.Comment;
import tn.esprit.entities.Post;
import tn.esprit.entities.User;

public interface ICommentService {

	List<Comment> getAllComments();

	void removeComment(int id);

	Comment getCommentById(int id);

	Comment AddComment(Comment comment);

	Comment updateComment(int idComment, Comment comment);

	long count();

	void saveComment(Comment comment);

	void addCommentBypost(Comment e, int idpost);

	List retrieveCooment(int id);

	Comment AjouterEtAffecterCommentToPost(Comment f, int id);

	
	//List<Comment> findByPost(int id_post);

	

	

	

	   
	
}
