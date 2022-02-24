package tn.esprit.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity

public class Position implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String potionName;
	@ManyToMany(mappedBy = "positions", cascade = CascadeType.ALL)
	private Set<Employee> employees;

	public Position() {
		super();
	}

	public Position(int id, String potionName, Set<Employee> employees) {
		super();
		this.id = id;
		this.potionName = potionName;
		this.employees = employees;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPotionName() {
		return potionName;
	}

	public void setPotionName(String potionName) {
		this.potionName = potionName;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employees == null) ? 0 : employees.hashCode());
		result = prime * result + id;
		result = prime * result + ((potionName == null) ? 0 : potionName.hashCode());
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
		Position other = (Position) obj;
		if (employees == null) {
			if (other.employees != null)
				return false;
		} else if (!employees.equals(other.employees))
			return false;
		if (id != other.id)
			return false;
		if (potionName == null) {
			if (other.potionName != null)
				return false;
		} else if (!potionName.equals(other.potionName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", potionName=" + potionName + ", employees=" + employees + "]";
	}

}
