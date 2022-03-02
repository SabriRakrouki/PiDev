package tn.esprit.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="user_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	private Set<String> languages;
	private Location BornePlace;
	
	@OneToMany(cascade=CascadeType.ALL)
	private Set<Post> posts;
	@OneToMany(cascade=CascadeType.ALL)
	private Set<Like> liks;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name ="users_roles", joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name ="role_id"))
    private Set<Role> roles = new HashSet<>();
	public User(String username, String email, String password) {
	    this.username = username;
	    this.email = email;
	    this.password = password;
	  }
	
	

}
