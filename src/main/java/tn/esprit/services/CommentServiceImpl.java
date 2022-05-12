package tn.esprit.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.logging.log4j.Logger;
import org.hibernate.engine.transaction.jta.platform.internal.WeblogicJtaPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import tn.esprit.entities.*;
import tn.esprit.repositories.CommentRepository;

import tn.esprit.repositories.PostRepository;
import tn.esprit.repositories.UserRepository;

@Service
public class CommentServiceImpl implements ICommentService {

	private final CommentRepository CommentRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	

	public CommentServiceImpl(tn.esprit.repositories.CommentRepository commentRepository, PostRepository postRepository,
			UserRepository userRepository) {

		CommentRepository = commentRepository;
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	
	}

	@Override
	public List<Comment> getAllComments() {

		return CommentRepository.findAll();
	}

	@Override
	public Comment getCommentById(int id) {
		return CommentRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
	}

	@Override
	public Comment AddComment(Comment comment) {

		comment.setContent(comment.getContent());
		comment.setDateComment(Calendar.getInstance().getTime());
		comment.setPosts(comment.getPosts());
		comment.setUser(comment.getUser());

		return CommentRepository.save(comment);

	}

	@Override
	public void saveComment(Comment comment) {

		boolean verif = false;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("./src/main/resources/dict_mots"));
			String line = reader.readLine();
			while (line != null && verif == false) {
				if (comment.getContent().contains(line)) {
					verif = true;
					System.out.println("You are using bad words");

				} else {
					comment.setContent(comment.getContent());
					comment.setDateComment(Calendar.getInstance().getTime());

					CommentRepository.save(comment);
					;
				}
				// read next line
				line = reader.readLine();
			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeComment(int id) {
		CommentRepository.deleteById(id);
	}

	@Override
	public Comment updateComment( Comment comment) {
		CommentRepository.save(comment);
		return comment;
	}

	// getcommentbypost
	/*
	 * @Override public List<Comment> findByPost(int id_post) {
	 * 
	 * return CommentRepository.findByPost(id_post); }
	 */
	@Override
	public long count() {
		return this.CommentRepository.count();
	}

	@Override
	public void addCommentBypost(Comment e, int idpost) {

		Post p = postRepository.findById(idpost).orElse(null);
		e.setPosts(p);
		CommentRepository.save(e);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public List retrieveCooment(int id) {

		return (List) CommentRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"

				));
	}

	public Comment AjouterEtAffecterCommentToPost(Comment c, int id_post) {
		Post p = (Post) this.postRepository.findById(id_post).get();
		c.setPosts(p);
		return (Comment) this.CommentRepository.save(c);
	}

}
