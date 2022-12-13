package tn.esprit.spring.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Investment {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    Integer id;

	    @ManyToOne
	    @JoinColumn(name = "account_id", nullable = false)
	    @JsonBackReference
	    private SecuritiesAccount securitiesAccount;

	    @OneToOne(cascade = CascadeType.ALL)
	    private Stock stock;

	    private Integer quantity;

	    private Double netInvested;
	    private Double currentValue;

	    private Double netProfit;
	    private Double netProfitPercentage;

	    private Double averageBuyPrice;


		@Override
		public String toString() {
			return "Investment [id=" + id + ", securitiesAccount=" + securitiesAccount + ", stock=" + stock + ", quantity=" + quantity
					+ ", netInvested=" + netInvested + ", currentValue=" + currentValue + ", netProfit=" + netProfit
					+ ", netProfitPercentage=" + netProfitPercentage + ", averageBuyPrice=" + averageBuyPrice + "]";
		}

		public Investment() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Investment(Integer id, SecuritiesAccount securitiesAccount, Stock stock, Integer quantity, Double netInvested, Double currentValue,
				Double netProfit, Double netProfitPercentage, Double averageBuyPrice) {
			super();
			this.id = id;
			this.securitiesAccount = securitiesAccount;
			this.stock = stock;
			this.quantity = quantity;
			this.netInvested = netInvested;
			this.currentValue = currentValue;
			this.netProfit = netProfit;
			this.netProfitPercentage = netProfitPercentage;
			this.averageBuyPrice = averageBuyPrice;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}


		public SecuritiesAccount getSecuritiesAccount() {
			return securitiesAccount;
		}

		public void setSecuritiesAccount(SecuritiesAccount securitiesAccount) {
			this.securitiesAccount = securitiesAccount;
		}

		public Stock getStock() {
			return stock;
		}

		public void setStock(Stock stock) {
			this.stock = stock;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public Double getNetInvested() {
			return netInvested;
		}

		public void setNetInvested(Double netInvested) {
			this.netInvested = netInvested;
		}

		public Double getCurrentValue() {
			return currentValue;
		}

		public void setCurrentValue(Double currentValue) {
			this.currentValue = currentValue;
		}

		public Double getNetProfit() {
			return netProfit;
		}

		public void setNetProfit(Double netProfit) {
			this.netProfit = netProfit;
		}

		public Double getNetProfitPercentage() {
			return netProfitPercentage;
		}

		public void setNetProfitPercentage(Double netProfitPercentage) {
			this.netProfitPercentage = netProfitPercentage;
		}

		public Double getAverageBuyPrice() {
			return averageBuyPrice;
		}

		public void setAverageBuyPrice(Double averageBuyPrice) {
			this.averageBuyPrice = averageBuyPrice;
		}
	    
	    
}
