package tn.esprit.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
@ToString
public class Domain implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nameDomain;
	@ManyToMany(mappedBy = "domains", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Entreprise> entreprises;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entreprises == null) ? 0 : entreprises.hashCode());
		result = prime * result + id;
		result = prime * result + ((nameDomain == null) ? 0 : nameDomain.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Domain other = (Domain) obj;
		if (entreprises == null) {
			if (other.entreprises != null)
				return false;
		} else if (!entreprises.equals(other.entreprises))
			return false;
		if (id != other.id)
			return false;
		if (nameDomain == null) {
			if (other.nameDomain != null)
				return false;
		} else if (!nameDomain.equals(other.nameDomain))
			return false;
		return true;
	}

}
