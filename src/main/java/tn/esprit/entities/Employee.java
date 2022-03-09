package tn.esprit.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee extends User implements Serializable {
	@Column(unique = true)
	private String cin;
	private String FirstName;
	private String lastName;
	private int Age;
	//@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	//private Set<Position> positions;
	//@OneToOne
	//private Invitation invitation;
	
	@ManyToOne
	@ToString.Exclude
	private Entreprise entreprise;
	
	
}
