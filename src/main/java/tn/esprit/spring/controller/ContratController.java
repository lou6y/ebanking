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

import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.service.Implementation.ContratServiceImpli;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/contrats")
public class ContratController {
	
	@Autowired
	ContratServiceImpli contractServiceImpl;
	
	@GetMapping("/")
	public List<Contrat> getContracts() {			
		return contractServiceImpl.getContracts();
	}
	
	@PostMapping("/add")
	public Contrat addContract(@RequestBody Contrat contract){
		return contractServiceImpl.addContract(contract);
	}
	
	
	@DeleteMapping("/{id}")  
	public void deleteContract(@PathVariable("id") Long id) {
		contractServiceImpl.deleteContract(id);
	}
	
	@PutMapping("/{id}")
	public Contrat updateContract(@PathVariable("id") Long id, @RequestBody Contrat newContract) {
		System.out.println(newContract);
		return contractServiceImpl.updateContract(id, newContract);
	}

	@GetMapping("/{id}")
	public Contrat getContract(@PathVariable("id") Long id) {
		return contractServiceImpl.getContract(id);
	}

}
