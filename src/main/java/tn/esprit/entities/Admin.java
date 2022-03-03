package tn.esprit.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
@DiscriminatorValue("Admin")
public class Admin extends User  implements Serializable{
	@Column(unique = true)
	private String cin;
	private String firstName;
	private String lastName;
}
