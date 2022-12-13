package tn.esprit.spring.entity;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class SecuritiesAccount {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long id;
	  private BigInteger rib;
	  @Temporal (TemporalType.DATE)
	  private Date creationDate;
	  private Double netInvested;
	  private Double netPortfolioValue;
	  private Double unrealizedGains;
	  private Double unrealizedGainsPercentage;
	  private Double realizedGains;
	  private Double realizedGainsPercentage;
	  
	  @OneToMany(mappedBy = "securitiesAccount", cascade = CascadeType.ALL)
	  @JsonManagedReference
	  private List<Investment> investments;
	  
	  
	  public SecuritiesAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SecuritiesAccount(Long id, BigInteger rib, Double netInvested, Double netPortfolioValue,
			Double unrealizedGains, Double unrealizedGainsPercentage, Double realizedGains,
			Double realizedGainsPercentage, List<Investment> investments) {
		super();
		this.id = id;
		this.rib = rib;
		this.creationDate = new java.util.Date();;
		this.netInvested = netInvested;
		this.netPortfolioValue = netPortfolioValue;
		this.unrealizedGains = unrealizedGains;
		this.unrealizedGainsPercentage = unrealizedGainsPercentage;
		this.realizedGains = realizedGains;
		this.realizedGainsPercentage = realizedGainsPercentage;
		this.investments = investments;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigInteger getRib() {
		return rib;
	}

	public void setRib(BigInteger rib) {
		this.rib = rib;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Double getNetInvested() {
		return netInvested;
	}

	public void setNetInvested(Double netInvested) {
		this.netInvested = netInvested;
	}

	public Double getNetPortfolioValue() {
		return netPortfolioValue;
	}

	public void setNetPortfolioValue(Double netPortfolioValue) {
		this.netPortfolioValue = netPortfolioValue;
	}

	public Double getUnrealizedGainsPercentage() {
		return unrealizedGainsPercentage;
	}

	public void setUnrealizedGainsPercentage(Double unrealizedGainsPercentage) {
		this.unrealizedGainsPercentage = unrealizedGainsPercentage;
	}

	public Double getRealizedGains() {
		return realizedGains;
	}

	public void setRealizedGains(Double realizedGains) {
		this.realizedGains = realizedGains;
	}

	public Double getRealizedGainsPercentage() {
		return realizedGainsPercentage;
	}

	public void setRealizedGainsPercentage(Double realizedGainsPercentage) {
		this.realizedGainsPercentage = realizedGainsPercentage;
	}

	public List<Investment> getInvestments() {
		return investments;
	}

	public void setInvestments(List<Investment> investments) {
		this.investments = investments;
	}

	public Double getUnrealizedGains() {
		return unrealizedGains;
	}	

	public void setUnrealizedGains(Double unrealizedGains) {
	    this.unrealizedGains = unrealizedGains;
	    this.setUnrealizedGainsPercentage(unrealizedGains * 100 / this.netInvested);
	}
	
}
