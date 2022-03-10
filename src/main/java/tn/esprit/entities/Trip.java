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
public class Trip implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@ManyToOne
	private Location tripLocation;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date departDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date arrivalDate;

	private String Description;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Program> programs;
	@ManyToOne
	private Entreprise entreprise;

	@OneToMany(cascade = CascadeType.ALL )
	private Set<Employee> employee;

	
	private Float attribution;
	private Float totalattribution;
	private Float compteur;
	private Float note;
	private String rating;
	
	

}
