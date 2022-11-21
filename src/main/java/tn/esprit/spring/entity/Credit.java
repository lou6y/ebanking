package tn.esprit.spring.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "credit")
public class Credit implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="id")
	private long id;
	
	@Column(name="montant")
	private long montant;
	
	@Column(name="mensualité")
	private float mensualite;
	
	@Column(name="duree")
	private int duree;
	
	@Column(name="echeance")
	private float echeance;
	
	@Column(name="dateObtention")
	private Date dateObtention;
	
	
	@Column(name="description")
	private String description;
	
	@Enumerated(EnumType.STRING)
	private Status Status;
	
	@Enumerated(EnumType.STRING)
	private TypeCredit TypeCredit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMontant() {
		return montant;
	}

	public void setMontant(long montant) {
		this.montant = montant;
	}

	public float getMensualite() {
		return mensualite;
	}

	public void setMensualite(float mensualite) {
		this.mensualite = mensualite;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public float getEcheance() {
		return echeance;
	}

	public void setEcheance(float echeance) {
		this.echeance = echeance;
	}

	public Date getDateObtention() {
		return dateObtention;
	}

	public void setDateObtention(Date dateObtention) {
		this.dateObtention = dateObtention;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		Status = status;
	}

	public TypeCredit getTypeCredit() {
		return TypeCredit;
	}

	public void setTypeCredit(TypeCredit typeCredit) {
		TypeCredit = typeCredit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Credit [id=" + id + ", montant=" + montant + ", mensualite=" + mensualite + ", duree=" + duree
				+ ", echeance=" + echeance + ", dateObtention=" + dateObtention + ", description=" + description
				+ ", Status=" + Status + ", TypeCredit=" + TypeCredit + "]";
	}

	public Credit(long id, long montant, float mensualite, int duree, float echeance, Date dateObtention,
			String description, tn.esprit.spring.entity.Status status,
			tn.esprit.spring.entity.TypeCredit typeCredit) {
		super();
		this.id = id;
		this.montant = montant;
		this.mensualite = mensualite;
		this.duree = duree;
		this.echeance = echeance;
		this.dateObtention = dateObtention;
		this.description = description;
		Status = status;
		TypeCredit = typeCredit;
	}
	
	public Credit() {
		
	}
	
	
	
	
	
	
	
	
	
	
	}