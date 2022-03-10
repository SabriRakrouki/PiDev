package tn.esprit.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@DiscriminatorValue("Employe")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Employee")

public class Employee extends User implements Serializable {
	/**
	 * 
	 */
	@Column(unique = true)
	private String cin;
	private String FirstName;
	private String lastName;
	private int Age;


	@ManyToOne(cascade = CascadeType.ALL)
	private Position position;
	@OneToOne
	private Invitation invitation;

	@ManyToOne
	@ToString.Exclude
	Entreprise entreprise;

	
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Trip trip;
	
}
