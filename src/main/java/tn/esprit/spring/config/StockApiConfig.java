package tn.esprit.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api")
public class StockApiConfig {
	private String API_KEY;

	public String getAPI_KEY() {
		return API_KEY;
	}

	public void setAPI_KEY(String aPI_KEY) {
		API_KEY = aPI_KEY;
	}
	
}
