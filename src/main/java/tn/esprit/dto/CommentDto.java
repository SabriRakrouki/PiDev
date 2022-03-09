package tn.esprit.dto;


import lombok.Getter;
import lombok.Setter;
import tn.esprit.entities.Post;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Setter
@Getter
public class CommentDto {
	
	/*private int id;
	
	
    @NotEmpty
    @Size(min = 10, message = "Message body should not be null or empty and it should be minimum of 10 characters")
    private String content;
    
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd")
    @PastOrPresent
    @NotNull
	private Date dateComment;
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getDateComment() {
		return dateComment;
	}


	public void setDateComment(Date dateComment) {
		this.dateComment = dateComment;
	}


    
    
    */
    
}
