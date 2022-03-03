package tn.esprit.services;

import org.springframework.http.ResponseEntity;

import tn.esprit.entities.*;
public interface LikesService {
	public  ResponseEntity LikePost(User user,  int id);
	public ResponseEntity RemoveLikePost(User user,  int id);
}
