package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
public class Domain  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String nameDomain; 
@ManyToMany(mappedBy ="domains",cascade = CascadeType.ALL)
private Set<Entreprise> entreprises;
}
