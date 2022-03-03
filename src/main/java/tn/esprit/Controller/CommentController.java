package tn.esprit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.Comment;
import tn.esprit.entities.Post;
import tn.esprit.services.ICommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired	
	ICommentService CommentService;
	
	@GetMapping("/getComments")
	@ResponseBody
	public List<Comment> getAllComments(){
		System.out.println(CommentService.getAllComments());
		return CommentService.getAllComments();
				
	}
	
	@GetMapping("/getComment/{comment-id}")
	@ResponseBody
	public Comment getCommentById(@PathVariable("comment-id") int commentId){
		return CommentService.getCommentById(commentId);
				
	}
	 @GetMapping("/count")
	    public long countComments() {
	        return CommentService.count();
	
	 }
	
	/*@PostMapping("/addComment")
	@ResponseBody
	public Comment  AddComment(@RequestBody Comment comment) {
		return CommentService.AddComment(comment); 
	   
	}*/
	
	@PostMapping("/saveComment")
	@ResponseBody
	public void  saveComment(@RequestBody Comment comment) {
		 CommentService.saveComment(comment); 
	   
	}
	
	
	@PutMapping("/updatecomment/{id}")
	public void updateComment(@RequestBody Comment comment,int id){
		CommentService.updateComment(id,comment);
	}
	
	@DeleteMapping("/remove-commment/{comment-id}")
    @ResponseBody
	public void deleteComment(@PathVariable("comment-id") int commentId) {

		CommentService.removeComment(commentId);

	}
	
	//getCommentbypost
	/* @GetMapping("commentBypost/{id_post}")
	    public List<Comment> getcommentBypost(@PathVariable("id_post") int id_post){
	        return CommentService.findByPost(id_post);
	    }
	*/
}


