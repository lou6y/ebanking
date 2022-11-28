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

@Entity
@Table( name = "Contrat")
public class Contrat {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	 @Column( name = "idContrat")
	private long NumContrat; // Clee primaire
	
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

	

	public long getNumContrat() {
		return NumContrat;
	}

	public void setNumContrat(long numContrat) {
		NumContrat = numContrat;
	}

	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public float getPrimeCommerciale() {
		return primeCommerciale;
	}

	public void setPrimeCommerciale(float primeCommerciale) {
		this.primeCommerciale = primeCommerciale;
	}

	public float getPrimePure() {
		return primePure;
	}

	public void setPrimePure(float primePure) {
		this.primePure = primePure;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAcceptation() {
		return acceptation;
	}

	public void setAcceptation(int acceptation) {
		this.acceptation = acceptation;
	}

	public int getDuration() {
		return Duration;
	}

	public void setDuration(int duration) {
		Duration = duration;
	}

	public Contrat() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
