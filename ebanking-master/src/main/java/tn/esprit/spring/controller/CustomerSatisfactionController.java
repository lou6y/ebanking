package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
