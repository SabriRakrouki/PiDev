package tn.esprit.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tn.esprit.entities.*;
import tn.esprit.repositories.*;

@Service
public class LikeServiceImp implements LikesService {

	@Autowired
	PostRepository postRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	NotificationRepository notificationRepository;
	@Autowired
	LikesRepository likesRepository;

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public ResponseEntity LikePost(User user, int id) {
		Notification notification = new Notification();
		Like like = new Like();
		notification.setLike(like);
		List<Post> posts = postRepository.findAll();
		List<Like> likes = likesRepository.findAll();
		Post post = postRepository.getById(id);

		for (Post p : posts) {
			for (Like l : likes) {
				if ((l.getPosts().equals(post))) {
					if (l.getUser().equals(user))

						return new ResponseEntity<String>("User already liked this post!", HttpStatus.OK);
				}
			}
			if (p.equals(p.getId())) {
				like.setPosts(p);
				like.setPosts(p);
				like.setTimestamp(LocalDateTime.now());
				like.setNotif(notification);
				like.setUser(user);

				likesRepository.save(like);

				notification.setUser(p.getUser());
				notificationRepository.save(notification);

				userRepository.save(user);
				return new ResponseEntity<String>("Post was liked successfully!", HttpStatus.OK);
			}

		}

		return new ResponseEntity<String>("Post was not liked!", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity RemoveLikePost(User user, int id) {
		List<Like> likes = likesRepository.findAll();
		Post post = postRepository.getById(id);

			for (Like l : likes) {
				if ((l.getPosts().equals(post))) {
					if (l.getUser().equals(user))
						likesRepository.delete(l);
						return new ResponseEntity<String>("User removed like from this post!", HttpStatus.OK);
				}}
			
				return new ResponseEntity<String>("User cannot remove like from this post!", HttpStatus.OK);
			

	}

}
