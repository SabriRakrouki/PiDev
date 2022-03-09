package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
<<<<<<< HEAD
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
=======
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
>>>>>>> 321358325a039b4e7dc7683557b29e9e82b8189f
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;



@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="user_type")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public  abstract class User implements Serializable{
	public static final String PROPERTY_NAME_ID = "id";
	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	  @NotNull
	private String username;
	  @NotNull
	private String email;
	  @NotBlank
	  @NotNull
	private String password;
	private String phoneNumber;
	private String Photo;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	//@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;

	@ElementCollection
	private Set<String> languages;
	@ManyToOne
	private Location BornePlace;
	
	@OneToMany(cascade=CascadeType.ALL)
	private Set<Post> posts;
	@OneToMany(cascade=CascadeType.ALL)
	private Set<Like> liks;
	
	

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name ="users_roles", joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name ="role_id"))
    private Set<Role> roles = new HashSet<>();
	 @Column(name = "reset_password_token")
	 private String resetPasswordToken;
}
