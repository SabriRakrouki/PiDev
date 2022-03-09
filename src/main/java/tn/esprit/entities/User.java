package tn.esprit.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Inheritance(strategy =InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")

@Getter
@Setter
@AllArgsConstructor

@RequiredArgsConstructor

@ToString

public abstract class User implements Serializable {
	public static final String PROPERTY_NAME_ID = "id";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;

	@ElementCollection
	private Set<String> languages;
	@ManyToOne
	private Location BornePlace;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Post> posts;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	@Column(name = "reset_password_token")
	private String resetPasswordToken;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Comment> comments;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Like> likes;

}
