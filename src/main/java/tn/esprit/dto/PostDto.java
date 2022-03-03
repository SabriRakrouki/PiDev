package tn.esprit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tn.esprit.entities.Comment;
import tn.esprit.entities.Like;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
public class PostDto {

    private long id;
 
   
    @NotEmpty
    @Size(min=5, max=20 ,message = "Post title should be at least 5 characters and max 200 characters")
    private String content;
    @NotEmpty
    @Size(min=3, max=10 ,message = "Post title should be at least 5 characters and max 15 characters")
	private String subject;
    
	private String photo;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd")
    @PastOrPresent
    @NotNull
	private Date DatePost;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Comment> comments;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Like> likes;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Date getDatePost() {
		return DatePost;
	}
	public void setDatePost(Date datePost) {
		DatePost = datePost;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public Set<Like> getLikes() {
		return likes;
	}
	public void setLikes(Set<Like> likes) {
		this.likes = likes;
	}

    
    
}
