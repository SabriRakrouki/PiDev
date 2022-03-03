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

	Post updatePost(int idPost, Post Post);

	PostResponse  getAllPostsbyPagination(int pageNo, int pageSize, String sortBy, String sortDir);

	List<Topic> findByTopic(int id_topic);

	List<Comment> getAllComments();

	long count();



}
