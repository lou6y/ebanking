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

import tn.esprit.spring.entity.Campaign;
import tn.esprit.spring.service.Implementation.CompaignServiceImpl;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/compaigns")
public class CompaignController {
	@Autowired
	CompaignServiceImpl compaignServiceImpl;
	
	@GetMapping("/")
	public List<Campaign> getCompaigns() {			
		return compaignServiceImpl.getCompaigns();
	}
	
	@PostMapping("/add")
	public List<Campaign> addCompaign(@RequestBody Campaign compaign){
		return compaignServiceImpl.addCompaign(compaign);
	}
	
	
	@DeleteMapping("/delete/{id}")  
	public List<Campaign> deleteCompaign(@PathVariable("id") Long id) {
		return compaignServiceImpl.deleteCompaign(id);
	}
	
	@PostMapping("/update/{id}")
	public List<Campaign> updateCompaign(@PathVariable("id") Long id, @RequestBody Campaign newCompaign) {
		System.out.println(newCompaign);
		return compaignServiceImpl.updateCompaign(id, newCompaign);
	}

	@GetMapping("/get/{id}")
	public Campaign getCompaign(@PathVariable("id") Long id) {
		return compaignServiceImpl.getCompaign(id);
	}
}