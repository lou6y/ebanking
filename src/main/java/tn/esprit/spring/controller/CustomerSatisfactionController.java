package tn.esprit.spring.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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

import tn.esprit.spring.dto.PredictionCreditDto;
import tn.esprit.spring.dto.PredictionDto;
import tn.esprit.spring.dto.PredictionResponse;
import tn.esprit.spring.entity.CustomerSatisfaction;
import tn.esprit.spring.service.Implementation.CustomerSatisfactionServiceImpl;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customersSatisfactions")
public class CustomerSatisfactionController {
	
	@Autowired
	CustomerSatisfactionServiceImpl customerSatisfactionServiceImpl;
	
	@GetMapping("/")
	public List<CustomerSatisfaction> getCustomerSatisfactions() {			
		return customerSatisfactionServiceImpl.getCustomerSatisfactions();
	}
	
	@PostMapping("/add")
	public CustomerSatisfaction addCustomerSatisfaction(@RequestBody CustomerSatisfaction customerSatisfaction){
		return customerSatisfactionServiceImpl.addCustomerSatisfaction(customerSatisfaction);
	}
	
	
	@DeleteMapping("/{id}")  
	public void deleteCustomerSatisfaction(@PathVariable("id") Long id) {
		customerSatisfactionServiceImpl.deleteCustomerSatisfaction(id);
	}
	
	@PutMapping("/{id}")
	public CustomerSatisfaction updateCustomerSatisfaction(@PathVariable("id") Long id, @RequestBody CustomerSatisfaction newCustomerSatisfaction) {
		System.out.println(newCustomerSatisfaction);
		return customerSatisfactionServiceImpl.updateCustomerSatisfaction(id, newCustomerSatisfaction);
	}

	@GetMapping("/{id}")
	public CustomerSatisfaction getCustomerSatisfaction(@PathVariable("id") Long id) {
		return customerSatisfactionServiceImpl.getCustomerSatisfaction(id);
	}
	
	@PostMapping(value = "/predictCredit", consumes = MediaType.ALL_VALUE, produces = "application/json")

	public PredictionResponse PredictCredit(@RequestBody PredictionCreditDto predictionCreditDto)
	{
		String uri = "http://127.0.0.1:5000/predictCredit";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		HttpEntity<PredictionCreditDto> getBody = new HttpEntity<>(predictionCreditDto, headers);
		ResponseEntity<PredictionResponse> result = restTemplate.exchange(uri, HttpMethod.POST, getBody, PredictionResponse.class);
		PredictionResponse resultPrediction =result.getBody();
	     System.out.println(resultPrediction);
	     return resultPrediction;
		
	}


}
