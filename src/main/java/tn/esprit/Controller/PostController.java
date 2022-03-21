package tn.esprit.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import tn.esprit.config.MyConstants;
import tn.esprit.dto.*;
import tn.esprit.entities.Comment;
import tn.esprit.entities.Post;
import tn.esprit.entities.Topic;
import tn.esprit.entities.User;
import tn.esprit.repositories.PostRepository;
import tn.esprit.repositories.UserRepository;
import tn.esprit.services.IPostService;
import tn.esprit.services.LikesService;

import java.io.IOException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

	private final JavaMailSender emailSender;

	private final IPostService IPostService;

	private final PostRepository postRepo;

	private final UserRepository userRepository;

	private final LikesService likesService;

	public PostController(JavaMailSender emailSender, tn.esprit.services.IPostService iPostService,
			PostRepository postRepo, UserRepository userRepository, LikesService likesService) {
		this.emailSender = emailSender;
		IPostService = iPostService;
		this.postRepo = postRepo;
		this.userRepository = userRepository;
		this.likesService = likesService;
	}

	@GetMapping("/getPosts")
	@ResponseBody
	public List<Post> getAllPosts() {

		return IPostService.getAllPosts();

	}

	@PostMapping("/addPost")
	@ResponseBody
	public Post AddPost(@RequestBody Post post) {
		return IPostService.AddPost(post);

	}

	@PutMapping("/updatepost/{id}")
	public void updatePost(@RequestBody Post post, int id) {
		IPostService.updatePost(id, post);
	}

	@DeleteMapping("/remove-post/{post-id}")
	@ResponseBody
	public void deletePost(@PathVariable("post-id") Integer postId) {

		IPostService.removePost(postId);

	}

	@GetMapping("/getpost/{id}")
	@ResponseBody
	public Post getPostById(@PathVariable("id") int postId) {
		return IPostService.getPostById(postId);

	}

	@GetMapping("/getPostsbypages")
	@ResponseBody
	public PostResponse getAllPostsByPageSize(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		return IPostService.getAllPostsbyPagination(pageNo, pageSize, sortBy, sortDir);
	}

	@GetMapping("/commentsList")
	public List<Comment> getAllComments() {
		return IPostService.getAllComments();
	}

	@GetMapping("/count")
	public long countPostss() {
		return IPostService.count();
	}

	// getpostbytopic
	/*
	 * @GetMapping("PostByTopics/{id_topic}") public List<Topic>
	 * getpostbytopic(@PathVariable("id_topic") int id_topic){ return
	 * IPostService.findByTopic(id_topic); }
	 */

	@PostMapping("likePost/{idPost}/{iduser}")

	public ResponseEntity<String> likePost(@PathVariable("idPost") int idPost, @PathVariable("iduser") int iduser) {

		likesService.LikePost(iduser, idPost);

		return ResponseEntity.ok("like");

	}

	@PutMapping("/addpostAndaffecte/{id}")
	@ResponseBody
	public String addPostAndaffecte(@RequestBody Post p, @PathVariable("id") int id) {
		this.IPostService.AjouterEtAffecterPostToTopic(p, id);
		return "Post Added and Affected !!!!!";
	}

	@ResponseBody
	@PutMapping("/like/{idpost}/{idUser}")
	public String sendSimpleEmail(@PathVariable("idpost") int idpost, @PathVariable("idUser") long idUser) {

		// like a post
		// ps.addlike(idpost,idUser);
		// Create a Simple MailMessage.
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(postRepo.getById(idpost).getUser().getEmail());
		message.setSubject("Test Simple Email");
		message.setText("Hello, vouz avez aimez cet poste merci ");

		// Send Message!
		this.emailSender.send(message);

		return "Email Sent!";
	}

}
