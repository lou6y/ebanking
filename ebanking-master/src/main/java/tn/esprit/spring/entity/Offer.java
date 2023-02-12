package tn.esprit.spring.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "Offer")
public class Offer {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column( name = "idOffer")
	private Long idOffre;
	private String offerName; 
	private String description;
    private String picture;
    private String characteristic;
    private String advantage; 
    
	
	@Temporal(TemporalType.DATE)
	private Date creationDate ;
	@Temporal(TemporalType.DATE)
	private Date endDate ;
	
	@ManyToMany(mappedBy="offers", cascade = CascadeType.ALL)
	private Set<Campaign> compaigns;
	
}
