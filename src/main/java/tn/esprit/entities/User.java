package tn.esprit.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public  abstract class User implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String username;
	private String email;
	private String password;
	private String phoneNumber;
	private String Photo;
	private Role role;
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Post> posts= new HashSet<>();
	
	/*
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Comment> comments;
	*/
	
}
