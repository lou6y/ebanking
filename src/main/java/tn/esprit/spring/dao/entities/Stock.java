package tn.esprit.spring.dao.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Stock {
	@Id
    @JsonAlias("01. symbol")
    private String symbol;

    private String name;

    @JsonAlias("05. price")
    private Double LTP;

    @JsonAlias("02. open")
    private Double previousOpen;

    @JsonAlias("08. previous close")
    private Double previousClose;

    @JsonAlias("03. high")
    private Double high;

    @JsonAlias("04. low")
    private Double low;

    public Stock(String symbol) {
        this(symbol, symbol.split("\\.")[0]);
    }

    public Stock(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

   

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", name=" + name + ", LTP=" + LTP + ", previousOpen=" + previousOpen
				+ ", previousClose=" + previousClose + ", high=" + high + ", low=" + low + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return Objects.equals(symbol, other.symbol);
	}

	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stock(String symbol, String name, Double lTP, Double previousOpen, Double previousClose, Double high,
			Double low) {
		super();
		this.symbol = symbol;
		this.name = name;
		LTP = lTP;
		this.previousOpen = previousOpen;
		this.previousClose = previousClose;
		this.high = high;
		this.low = low;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLTP() {
		return LTP;
	}

	public void setLTP(Double lTP) {
		LTP = lTP;
	}

	public Double getPreviousOpen() {
		return previousOpen;
	}

	public void setPreviousOpen(Double previousOpen) {
		this.previousOpen = previousOpen;
	}

	public Double getPreviousClose() {
		return previousClose;
	}

	public void setPreviousClose(Double previousClose) {
		this.previousClose = previousClose;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

}
