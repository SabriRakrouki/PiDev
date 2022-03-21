package tn.esprit.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import tn.esprit.dto.*;
import tn.esprit.entities.Comment;
import tn.esprit.entities.Employee;
import tn.esprit.entities.Like;
import tn.esprit.entities.Post;
import tn.esprit.entities.Topic;
import tn.esprit.entities.User;
import tn.esprit.repositories.PostRepository;
import tn.esprit.repositories.UserRepository;
import tn.esprit.services.IPostService;
import tn.esprit.services.LikesService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/api/v1/like")
public class LikeController {

	private final LikesService likeService;

	private final PostRepository postRepo;

	private final UserRepository userrepository;

	public LikeController(LikesService likeService, PostRepository postRepo, UserRepository userrepository) {
		this.likeService = likeService;
		this.postRepo = postRepo;
		this.userrepository = userrepository;
	}

	@PostMapping("/addLike/{idPost}")

	public Like AddLike(@PathParam("idPost") Integer idpost) {
		Like like = new Like();
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		User us = userrepository.findByUsername(username).get();
		Post post = postRepo.findById(idpost).get();
		like.setUser(us);
		like.setPost(post);
		return likeService.AddLike(like);

	}

	@GetMapping("/count")
	public long countLikes() {
		return likeService.count();
	}

	@PostMapping("/likeAndDislikes/{iduser}")
	public void LikePost(@PathParam("iduser") int user, @RequestBody int post) {

		likeService.LikePost(user, post);

	}
}
