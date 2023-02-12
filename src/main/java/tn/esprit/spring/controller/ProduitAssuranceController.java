package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.ProduitAssurance;
import tn.esprit.spring.service.Implementation.ProduitAssuranceServiceImpli;

@RestController
@RequestMapping("/ProduitAssurances")
public class ProduitAssuranceController {
	@Autowired
	ProduitAssuranceServiceImpli produitAssuranceServiceImpli;
	
	@GetMapping("/")
	public List<ProduitAssurance> getProduitAssurances() {			
		return produitAssuranceServiceImpli.getProduitAssurances();
	}
	
	@PostMapping("/add")
	public ProduitAssurance addProduitAssurance(@RequestBody ProduitAssurance produitAssurance){
		return produitAssuranceServiceImpli.addProduitAssurance(produitAssurance);
	}
	
	
	@DeleteMapping("/{id}")  
	public void deleteProduitAssurance(@PathVariable("id") Long id) {
		produitAssuranceServiceImpli.deleteProduitAssurance(id);
	}
	
	@PutMapping("/{id}")
	public ProduitAssurance updateProduitAssurance(@PathVariable("id") Long id, @RequestBody ProduitAssurance produitAssurance) {
		System.out.println(produitAssurance);
		return produitAssuranceServiceImpli.updateProduitAssurance(id, produitAssurance);
	}

	@GetMapping("/{id}")
	public ProduitAssurance getProduitAssurance(@PathVariable("id") Long id) {
		return produitAssuranceServiceImpli.getProduitAssurance(id);
	}

}


