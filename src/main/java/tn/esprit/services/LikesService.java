package tn.esprit.services;


import tn.esprit.entities.*;
public interface LikesService {

	

	void removeLike(long id);

	long count();

	void LikePost(int iduser, int idpost);

	Like updateLike(long idLike, Like like);

	Like AddLike(Post post, User user);

	Post retrievePostWithMostlikes();

	Like AddLike(Like like);
	
}
