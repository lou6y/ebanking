package tn.esprit.spring.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table( name = "Contrat")
public class Contrat {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	 @Column( name = "idContrat")
	private int NumContrat; // Clee primaire
	
	@Temporal(TemporalType.DATE)
	private Date date_debut ;
	
	@Temporal(TemporalType.DATE)
	private Date date_fin ;
	
	private float primeCommerciale ;
	private float primePure ;
	//@Column(columnDefinition = "integer default 0")

	private String status ; // 
	
	private int acceptation ; // contat accepte par le client ou pas encore

	private int Duration;

	
	

}
