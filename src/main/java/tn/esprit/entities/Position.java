package tn.esprit.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Position implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
<<<<<<< HEAD
	private int id;
	private String potionName;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Employee> employees;
=======
private int id;
private String potionName;
//@ManyToMany(mappedBy ="positions",cascade = CascadeType.ALL)
//private Set<Employee> employees;
>>>>>>> 305fbbf06391cdc30c63afd05ba1c8f90ec486a6
}
