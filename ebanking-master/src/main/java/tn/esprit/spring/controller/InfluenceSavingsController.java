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

}
