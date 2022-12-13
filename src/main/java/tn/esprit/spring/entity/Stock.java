package tn.esprit.spring.entity;

import java.math.BigDecimal;
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
    private Double price;

    @JsonAlias("02. open")
    private Double previousOpen;

    @JsonAlias("08. previous close")
    private Double previousClose;

    @JsonAlias("03. high")
    private Double high;

    @JsonAlias("04. low")
    private Double low;
    
    @JsonAlias("06. volume")
    private BigDecimal volume;

    public Stock(String symbol) {
        this(symbol, symbol.split("\\.")[0]);
    }

    public Stock(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }


	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", name=" + name + ", price=" + price + ", previousOpen=" + previousOpen
				+ ", previousClose=" + previousClose + ", high=" + high + ", low=" + low + ", volume=" + volume + "]";
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

	public Stock(String symbol, String name, Double price, Double previousOpen, Double previousClose, Double high,
			Double low, BigDecimal volume) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.price = price;
		this.previousOpen = previousOpen;
		this.previousClose = previousClose;
		this.high = high;
		this.low = low;
		this.volume = volume;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

}
