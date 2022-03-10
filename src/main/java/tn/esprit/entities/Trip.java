package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

public class Trip implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER )
	private Location tripLocation;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date departDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date arrivalDate;

	private String Description;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "trip",fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Program> programs;
	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JsonIgnore
	private Entreprise entreprise;
	
	@OneToMany(cascade = CascadeType.ALL ,mappedBy ="trip" ,fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Employee> employee;

	
	private Float attribution;
	private Float totalattribution;
	private Float compteur;
	private Float note;
	private String rating;
	
	

}
