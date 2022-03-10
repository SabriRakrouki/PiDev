package tn.esprit.entities;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Location implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String country;

	private String state;

	private String city;

	private String CountryTag;

	private String StateTage;

	@OneToMany(mappedBy = "BornePlace",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	@JsonIgnore
	private Set<User> user;
	@OneToMany(mappedBy = "tripLocation",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST )
	@JsonIgnore
	private Set<Trip> trips;
}
