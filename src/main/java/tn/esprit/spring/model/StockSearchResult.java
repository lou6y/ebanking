package tn.esprit.spring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockSearchResult {

    @JsonAlias("2. name")
    private String name;

    @JsonAlias("4. region")
    private String region;

    @JsonAlias("1. symbol")
    private String symbol;

    @JsonAlias("9. matchScore")
    private Double matchScore;

	public StockSearchResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StockSearchResult(String name, String region, String symbol, Double matchScore) {
		super();
		this.name = name;
		this.region = region;
		this.symbol = symbol;
		this.matchScore = matchScore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getMatchScore() {
		return matchScore;
	}

	public void setMatchScore(Double matchScore) {
		this.matchScore = matchScore;
	}

    
}
