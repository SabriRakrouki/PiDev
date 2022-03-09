package tn.esprit.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topic implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
	@Column(unique = true)
private String nameTopic;
	@OneToMany(cascade=CascadeType.ALL)
private Set<Post> posts;
}
