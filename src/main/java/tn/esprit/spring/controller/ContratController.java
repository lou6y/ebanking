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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import tn.esprit.spring.dto.PredictionDto;
import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.service.Implementation.ContratServiceImpl;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/contrats")
public class ContratController {
	
	@Autowired
	ContratServiceImpl contractServiceImpl;
	
	@GetMapping("/")
	public List<Contrat> getContracts() {			
		return contractServiceImpl.getContrats();
	}
	
	@PostMapping("/add")
	public List<Contrat> addContract(@RequestBody Contrat contract){
		return contractServiceImpl.addContract(contract);
	}
	
	
	@DeleteMapping("/delete/{id}")  
	public List<Contrat> deleteContract(@PathVariable("id") Long id) {
		return contractServiceImpl.deleteContract(id);
	}
	
	@PostMapping("/update/{id}")
	public List<Contrat> updateContract(@PathVariable("id") Long id, @RequestBody Contrat newContract) {
		System.out.println(newContract);
		return contractServiceImpl.updateContract(id, newContract);
	}

	@GetMapping("/get/{id}")
	public Contrat getContract(@PathVariable("id") Long id) {
		return contractServiceImpl.getContract(id);
	}
	


}
