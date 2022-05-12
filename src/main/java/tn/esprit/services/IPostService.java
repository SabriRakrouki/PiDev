package tn.esprit.services;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.dto.PostResponse;
import tn.esprit.entities.Comment;
import tn.esprit.entities.Like;
import tn.esprit.entities.Post;
import tn.esprit.entities.Topic;

public interface IPostService {

	Post getPostById(int id);

	List<Post> getAllPosts();

	Post AddPost(Post Post);

	void removePost(int id);

	

	PostResponse  getAllPostsbyPagination(int pageNo, int pageSize, String sortBy, String sortDir);

	List<Comment> getAllComments();

	long count();

	Post retrieveBestPosts();

	

	String addlike(int idPost, int idUser);

	void AjouterEtAffecterPostToTopic(Post p, int id);

	Post updatePost(Post post);



}
