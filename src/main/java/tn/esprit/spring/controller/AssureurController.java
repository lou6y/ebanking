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
import tn.esprit.spring.entity.Assureur;
import tn.esprit.spring.service.Implementation.AssureurServiceImpli;

@RestController
@RequestMapping("/Assureurs")

public class AssureurController {
	
		
		@Autowired
		AssureurServiceImpli assureurServiceImpl;
		
		@GetMapping("/")
		public List<Assureur> getAssureurs() {			
			return assureurServiceImpl.getAssureurs();
		}
		
		@PostMapping("/add")
		public Assureur addAssureur(@RequestBody Assureur assureur){
			return assureurServiceImpl.addAssureur(assureur);
		}
		
		
		@DeleteMapping("/{id}")  
		public void deleteAssureur(@PathVariable("id") long id) {
			assureurServiceImpl.deleteAssureur(id);
		}
		
		@PutMapping("/{id}")
		public Assureur updateContract(@PathVariable("id") long id, @RequestBody Assureur newAssureur) {
			System.out.println(newAssureur);
			return assureurServiceImpl.updateAssureur(id, newAssureur);
		}

		@GetMapping("/{id}")
		public Assureur getAssureur(@PathVariable("id") long id) {
			return assureurServiceImpl.getAssureur(id);
		}

	}


