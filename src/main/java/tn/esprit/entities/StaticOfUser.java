package tn.esprit.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaticOfUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public long NumberOfuser;
	public Date dateDay;
	public Date DayofweekLater;

}
