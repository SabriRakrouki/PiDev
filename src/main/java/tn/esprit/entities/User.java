package tn.esprit.entities;

import java.io.Serializable;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
=======
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
>>>>>>> 38039ba862bad4b3db5b2310e1b3b647fb80672e
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
<<<<<<< HEAD
import javax.persistence.OneToMany;
=======
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import org.hibernate.annotations.GenericGenerator;
>>>>>>> 38039ba862bad4b3db5b2310e1b3b647fb80672e

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<<<<<<< HEAD
import lombok.ToString;






@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="user_type")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public  abstract class User implements Serializable{
	public static final String PROPERTY_NAME_ID = "id";
	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String username;
	private String email;
	  @NotBlank
	private String password;
	private String phoneNumber;
	private String Photo;
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name ="users_roles", joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name ="role_id"))
    private Set<Role> roles = new HashSet<>();
	public User(String username, String email, String password) {
	    this.username = username;
	    this.email = email;
	    this.password = password;
	  }
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Post> posts= new HashSet<>();
	
	/*
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Comment> comments;
	*/
	
}
