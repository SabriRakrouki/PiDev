package tn.esprit.entities;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;






@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private int id;
	
	   
	private String subject;
    
   private String content;
    @Column(nullable = true)
	private String photo;
    @Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date DatePost;
	@OneToMany(mappedBy="posts",cascade = CascadeType.ALL)
	private Set<Comment> comments;
	
	@OneToMany(mappedBy="posts",cascade = CascadeType.ALL)
	private Set<Like> likes;
	
	 @OneToOne
	 private Topic topic;
	 
	 @JsonIgnore
	 @ManyToOne
	 private User user;

	
	
	  /*@Transient
	    public String getPhotosImagePath() {
	        if (photo == null) return null;
	         
	        return "C:\\uploads" + id + "/" + photo;
	    }
        */
	
	
	
}
