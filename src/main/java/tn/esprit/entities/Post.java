package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private int id;
	private String subject;
	private String content;
	private String photo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date DatePost;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Comment> comments;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Like> likes;
}
