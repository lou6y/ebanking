package tn.esprit.spring.dao.entities;

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

	    // Supplying just the Stock, the Quantity and the buy Price
	    public Investment(Stock stock, Integer quantity, Double averageBuyPrice, SecuritiesAccount securitiesAccount) {

	        this.securitiesAccount = securitiesAccount;
	        this.stock = stock;
	        this.quantity = 0;
	        this.netInvested = 0.0;
	        this.currentValue = 0.0;
	        this.netProfit = 0.0;
	        this.netProfitPercentage = 0.0;
	        this.averageBuyPrice = 0.0;

	        buy(quantity, averageBuyPrice);

	        refresh();
	    }

	    // Updates stock data based on latest LTP.
	    void refresh() {

	        this.currentValue = this.stock.getLTP() * quantity;

	        this.netProfit = netInvested - currentValue;
	        this.netProfitPercentage = (netProfit / netInvested) * 100;

	    }

	    // Sells the stock and returns the income from the sale
	    public Double sell(int quantity, Double sellingPrice) {

	        this.quantity -= quantity;
	        this.netInvested -= this.quantity * averageBuyPrice;

	        refresh();

	        return (quantity * sellingPrice);
	    }

	    // Selling the stock at Market price and returning the income from the sale
	    public Double sell(int quantity) {
	        return sell(quantity, this.stock.getLTP());
	    }

	    // Selling all the stock at Market Price. and returning the income from the sale
	    public Double sell() {
	        return sell(this.quantity, this.stock.getLTP());
	    }

	    // Buy a certain quantity of the stock at the specified price.
	    public void buy(int quantity, Double buyPrice) {

	        this.quantity += quantity;
	        this.netInvested += quantity * buyPrice;
	        this.averageBuyPrice = this.netInvested / this.quantity;

	        refresh();

	    }

	    // Buy a certain quantity of the stock at the Market Price.
	    public void buy(int quantity) {
	        buy(quantity, this.stock.getLTP());
	    }

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
