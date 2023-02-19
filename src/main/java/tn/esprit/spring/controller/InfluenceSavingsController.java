package tn.esprit.spring.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import tn.esprit.spring.dto.PredictionDto;
import tn.esprit.spring.dto.PredictionEpargneResponse;
import tn.esprit.spring.dto.PredictionResponse;
import tn.esprit.spring.entity.InfluenceSavings;
import tn.esprit.spring.service.Implementation.InfluenceSavingsServiceImpl;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/influenceSavingss")
public class InfluenceSavingsController {

	@Autowired
	InfluenceSavingsServiceImpl influenceSavingsServiceImpl;
	
	@GetMapping("/")
	public List<InfluenceSavings> getInfluenceSavingss() {			
		return influenceSavingsServiceImpl.getInfluenceSavingss();
	}
	
	@PostMapping("/add")
	public InfluenceSavings addInfluenceSavings(@RequestBody InfluenceSavings influenceSavings){
		return influenceSavingsServiceImpl.addInfluenceSavings(influenceSavings);
	}
	
	
	@DeleteMapping("/{id}")  
	public void deleteInfluenceSavings(@PathVariable("id") Long id) {
		influenceSavingsServiceImpl.deleteInfluenceSavings(id);
	}
	
	@PutMapping("/{id}")
	public InfluenceSavings updateInfluenceSavings(@PathVariable("id") Long id, @RequestBody InfluenceSavings newInfluenceSavings) {
		System.out.println(newInfluenceSavings);
		return influenceSavingsServiceImpl.updateInfluenceSavings(id, newInfluenceSavings);
	}

	@GetMapping("/{id}")
	public InfluenceSavings getInfluenceSavings(@PathVariable("id") Long id) {
		return influenceSavingsServiceImpl.getInfluenceSavings(id);
	}
	
	
	@PostMapping(value = "/predictEpargne", consumes = MediaType.ALL_VALUE, produces = "application/json")

	public PredictionEpargneResponse PredictEpargne(@RequestBody PredictionDto predictionDto)
	{
		
		String uri = "http://127.0.0.1:5000/predictEpargne";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		HttpEntity<PredictionDto> getBody = new HttpEntity<>(predictionDto, headers);
		ResponseEntity<PredictionEpargneResponse> result = restTemplate.exchange(uri, HttpMethod.POST, getBody, PredictionEpargneResponse.class);
	 	PredictionEpargneResponse resultPrediction =result.getBody();
	     System.out.println(resultPrediction);
	     return resultPrediction;
		//System.out.println(result);
	     
	    
		
			
	}

}
