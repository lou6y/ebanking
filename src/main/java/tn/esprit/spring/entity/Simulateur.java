package tn.esprit.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Simulateur {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="id")
	
	private long id;
	private Float taux;
	private Float montant;
	private int durée;
	
	public Simulateur() {
		super();
		this.id = id;
		this.taux = taux;
		this.montant = montant;
		this.durée = durée;
	}
	
	
	public Simulateur(long id, Float taux, Float montant, int durée) {
		super();
		this.id = id;
		this.taux = taux;
		this.montant = montant;
		this.durée = durée;
	}
	public Float getTaux() {
		return taux;
	}
	public void setTaux(Float taux) {
		this.taux = taux;
	}
	public Float getMontant() {
		return montant;
	}
	public void setMontant(Float montant) {
		this.montant = montant;
	}
	public int getDurée() {
		return durée;
	}
	public void setDurée(int durée) {
		this.durée = durée;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
}
