package tn.esprit.services;


import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.entities.*;

import tn.esprit.dto.PostDto;
import tn.esprit.dto.PostResponse;
import tn.esprit.entities.Post;
import tn.esprit.repositories.CommentRepository;
import tn.esprit.repositories.LikesRepository;
import tn.esprit.repositories.PostRepository;
import tn.esprit.repositories.TopicRepository;
import tn.esprit.repositories.UserRepository;



@Service
public class PostServiceImpl implements IPostService {
	
    @Autowired
    private PostRepository PostRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private LikesRepository LikeRepository ;
    
    @Autowired
    TopicRepository tr;

    @Autowired
    UserRepository userRepo;
    

	public PostServiceImpl(PostRepository postRepository) {
		super();
		PostRepository = postRepository;
	}
	
	@Override
    public List<Post> getAllPosts() {
    
        return PostRepository.findAll();
    }
	@Override
    public PostResponse getAllPostsbyPagination(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort orderBy = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, orderBy);
        Page<Post> pages = PostRepository.findAll(pageable);
        List<Post> pagesListEntity = pages.getContent();
        List<PostDto> posts = pagesListEntity.stream()
                .map(pageEntity -> mapToDto(pageEntity))
                .collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(posts);
        postResponse.setPageNo(pages.getNumber());
        postResponse.setPageSize(pages.getSize());
        postResponse.setTotalElements(pages.getTotalElements());
        postResponse.setLast(pages.isLast());
        return postResponse;
    }
	
	
	 private PostDto mapToDto(Post post) {
	        PostDto postDto = new PostDto();
	        
	        postDto.setContent(post.getContent());
	        postDto.setSubject(post.getSubject());
	        postDto.setDatePost(post.getDatePost());
	        return postDto;
	    }

	    private Post mapToEntity(PostDto PostDto) {
	        Post post = new Post();
	        post.setContent(post.getContent());
	        post.setSubject(post.getSubject());
	        post.setDatePost(post.getDatePost());
	        return post;
	    }
    @Override
	public Post getPostById(int id) {
	    return PostRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "entity not found"
                )
        );
    }

    
 
    
   /*@Override
	public List<Topic> findByTopic(int id_topic) {
		
		return PostRepository.findByTopic(id_topic);
	}
    */
    @Override
    public Post AddPost(Post Post) {
    
    	Post.setDatePost(Calendar.getInstance().getTime());
    	
    	return PostRepository.save(Post);
		
	}


    @Override
    public List<Comment> getAllComments() {
    	
        return commentRepository.findAll();
    }
    @Override
    public void removePost(int id) {
    	PostRepository.deleteById(id);
    }
    @Override 
    public Post updatePost(int idPost,Post Post) {
    	    Post OldPost =PostRepository.getById(idPost);
    		OldPost = Post;
    		PostRepository.save(OldPost);
    		return Post;
    	}
    

    @Override
    public long count() {
        return PostRepository.count();
    }

    @Override
	public Post retrieveBestPosts() {
		 int idpost = LikeRepository.getPostsWithMostLikes();
		return PostRepository.findById(idpost).get();
		
	}
    
    @Override
    @Transactional
    public String addlike(int idPost , int idUser)
    {
        Post p = PostRepository.findById(idPost).get();
        User u = userRepo.findById(idUser).get();
        Like l = new Like();
        l.setPosts(p);
        return "okkkk!!";
    }

	@Override
	public void AjouterEtAffecterPostToTopic(Post p, int id) {

	   
	        Topic topic = (Topic)this.tr.findById(id).get();
	        p.setTopic(topic);
	         PostRepository.save(p);
	     //   return "post added !";

	    }

	}
    



