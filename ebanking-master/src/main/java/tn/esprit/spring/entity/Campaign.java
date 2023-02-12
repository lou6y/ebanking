package tn.esprit.spring.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Table( name = "Campaign")
public class Campaign {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column( name = "idCampaign")
	private Long idCampaign; // Clee primaire
	private CampaignType campaignType;
	private String mainTarget;
	private String targetHeart;
	private String secondaryTarget;
	private Long msgCampaign;
	private String support;
	private Long reportOperations;
	
	
	@ManyToOne
	CustomerSatisfaction customerSatisfaction;
	
	@ManyToOne
	InfluenceSavings infuenceSavings;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Offer> offers;
	
	
	
	

}
