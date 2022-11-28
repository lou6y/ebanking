package tn.esprit.spring.entity;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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

	public SecuritiesAccount(Long id, BigInteger rib, Date creationDate, Double netInvested, Double netPortfolioValue,
			Double unrealizedGains, Double unrealizedGainsPercentage, Double realizedGains,
			Double realizedGainsPercentage, List<Investment> investments) {
		super();
		this.id = id;
		this.rib = rib;
		this.creationDate = creationDate;
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
	public void invest(Investment investment) {
	    this.investments.add(investment);
	    this.netInvested += investment.getNetInvested();
	    this.netPortfolioValue += investment.getNetInvested();
	    this.investments.add(investment);
	    refresh();
	}
	public void refresh() {

	    AtomicReference<Double> tempUnrealizedGains = new AtomicReference<>(0.0);
	    AtomicReference<Double> tempPortfolioValue = new AtomicReference<>(0.0);

	    investments.forEach(i ->
	    {
	        tempUnrealizedGains.updateAndGet(v -> v + i.getNetProfit());
	        tempPortfolioValue.updateAndGet(v -> v + i.getCurrentValue());
	    });

	    this.setUnrealizedGains(tempUnrealizedGains.get());
	    this.setNetPortfolioValue(tempPortfolioValue.get());

	}

	public void setUnrealizedGains(Double unrealizedGains) {
	    this.unrealizedGains = unrealizedGains;
	    this.setUnrealizedGainsPercentage(unrealizedGains * 100 / this.netInvested);
	}
	public void buy(Stock stock, Integer quantity, Double buyPrice) {

	    System.out.println(this + " buying " + quantity + " of " + stock + " at " + buyPrice);

	    Investment previousInvestment = null;

	    for (Investment investment : investments)
	        if (investment.getStock().equals(stock)) {
	            previousInvestment = investment;
	            break;
	        }

	    if (previousInvestment != null)
	        previousInvestment.buy(quantity, buyPrice);
	    else
	        this.invest(new Investment(stock, quantity, buyPrice, this));

	    refresh();

	    System.out.println("After buying: investments: " + this.investments);

	}

	public void sell(Stock stock, Integer quantity, Double sellingPrice) throws IllegalArgumentException {

	    Investment ownedInvestment = null;

	    for (Investment investment : investments)
	        if (investment.getStock().equals(stock)) {
	            ownedInvestment = investment;
	            break;
	        }

	    if (ownedInvestment == null)
	        throw new IllegalArgumentException("You don't own this stock");

	    if (quantity > ownedInvestment.getQuantity())
	        throw new IllegalArgumentException("You don't own enough quantity of this stock");

	    sell(ownedInvestment, quantity, sellingPrice);

	}

	public void sell(int investmentId, Integer quantity, Double sellingPrice) throws IllegalArgumentException {

	    Investment ownedInvestment = null;

	    for (Investment investment : investments)
	        if (investment.getId().equals(investmentId)) {
	            ownedInvestment = investment;
	            break;
	        }

	    if (ownedInvestment == null)
	        throw new IllegalArgumentException("You don't own this stock");

	    if (quantity > ownedInvestment.getQuantity())
	        throw new IllegalArgumentException("You don't own enough quantity of this stock");

	    sell(ownedInvestment, quantity, sellingPrice);

	}

	public void sell(Investment investment, Integer quantity, Double sellingPrice) {

	    this.netInvested -= investment.getNetInvested();

	    this.realizedGains += investment.sell(quantity, sellingPrice) - investment.getNetInvested();

	    if (investment.getQuantity() == 0)
	        this.investments.remove(investment);

	    refresh();

	}

	public Optional<Investment> getInvestmentFromStock(String stockSymbol) {

	    for (Investment i : this.getInvestments()) {
	        if (i.getStock().getSymbol().equals(stockSymbol))
	            return Optional.of(i);
	    }

	    return Optional.empty();

	}

	public Optional<Investment> getInvestmentFromId(int investmentId) {

	    for (Investment i : this.getInvestments()) {
	        if (i.getId() == investmentId)
	            return Optional.of(i);
	    }

	    return Optional.empty();

	}
}
