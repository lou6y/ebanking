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
@Table(name = "Offer")
public class Offer {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column( name = "idOffer")
	private int idOffre;
	private String OfferName; 
	private Long Description;
    private Long Picture;
    private Long Characteristic;
    private Long Advantage; 
    
	
	@Temporal(TemporalType.DATE)
	private Date CreationDate ;
	@Temporal(TemporalType.DATE)
	private Date EndDate ;
	public int getIdOffre() {
		return idOffre;
	}
	public void setIdOffre(int idOffre) {
		this.idOffre = idOffre;
	}
	public String getOfferName() {
		return OfferName;
	}
	public void setOfferName(String offerName) {
		OfferName = offerName;
	}
	public Long getDescription() {
		return Description;
	}
	public void setDescription(Long description) {
		Description = description;
	}
	public Long getPicture() {
		return Picture;
	}
	public void setPicture(Long picture) {
		Picture = picture;
	}
	public Long getCharacteristic() {
		return Characteristic;
	}
	public void setCharacteristic(Long characteristic) {
		Characteristic = characteristic;
	}
	public Long getAdvantage() {
		return Advantage;
	}
	public void setAdvantage(Long advantage) {
		Advantage = advantage;
	}
	public Date getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(Date creationDate) {
		CreationDate = creationDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	public Offer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
