package tn.esprit.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance
@DiscriminatorColumn(name = "user_type")
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
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
	private long code;
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name ="users_roles", joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name ="role_id"))
    private Set<Role> roles = new HashSet<>();
	public User(String username, String email, String password) {
	    this.username = username;
	    this.email = email;
	    this.password = password;
	  }
	
	

}
