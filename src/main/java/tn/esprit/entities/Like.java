package tn.esprit.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="likes") 
public class Like implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLikes;

	private LocalDateTime timestamp;
	
	@ManyToOne
	private Post posts;
	
	@OneToOne
	@JoinColumn(name = "id_us_liked", referencedColumnName = "id")
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "notif_id", referencedColumnName = "idNotification")
	private Notification notif;
}
