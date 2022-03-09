package tn.esprit.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tn.esprit.entities.*;
import tn.esprit.repositories.*;


@Service
public class LikeServiceImp implements LikesService {

	@Autowired
	PostRepository postRepository;
	@Autowired
	AdminRepository userRepository;
	@Autowired
	NotificationRepository notificationRepository;
	@Autowired
	LikesRepository likesRepository;



    @Override
    public Like AddLike(Post post,User user) {
    	Like like = new Like();
    	like.setPosts(post);
    	like.setUser(user);
    	like.setIsliked(true);
    	likesRepository.save(like);
		return like;
	}

    
    @Override
    public void removeLike(long id) {
    	likesRepository.deleteById( id);
    }
  
   @Override 
   public Like updateLike(long idLike,Like like) {
   	    Like Oldlike =likesRepository.getById(idLike);
   	    Oldlike = like;
   		likesRepository.save(Oldlike);
   		return like;
   	}
   
    @Override
    public long count() {
        return likesRepository.count();
    }
	
	@Override
	public void LikePost(int iduser,int idpost) {
		
		Post post=postRepository.findById(idpost).orElse(null);
    	Admin user=userRepository.findById(iduser).orElse(null);
    	
		
		
		Set<Like>likes= post.getLikes();
		for (Like lk:likes) {
			if(user.getId()==lk.getUser().getId()&&post.getId()==lk.getPosts().getId())
			{
			 removeLike(lk.getIdLikes());
			}else {
				
				AddLike(post,user);
			}
			
		}
		
		
	}
	@Override
	public Post retrievePostWithMostlikes() {
		 int idpost= likesRepository.getPostsWithMostLikes();
		return postRepository.findById(idpost).get();
		
	}


	@Override
	public Like AddLike(Like like) {
		return likesRepository.save(like);
	}


	

	

}
