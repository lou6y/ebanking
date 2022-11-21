package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import tn.esprit.spring.entity.Simulateur;
import tn.esprit.spring.service.ISimulateurService;

@RestController
@RequestMapping("/simulateur")
public class SimulateurRestController {

	@Autowired
	ISimulateurService simulateurservice;
	
	
	// http://localhost:8084/simulateur/ajouter/{MontantEmprunte}/{duree}
	@PostMapping("/ajouter/{MontantEmprunte}/{duree}")
	
	
	@ResponseBody
	
	public Float ajouter(@PathVariable("MontantEmprunte")float MontantEmprunte,@PathVariable("duree")int Duree)
	
	{

		
		
		return simulateurservice.Simulater1(MontantEmprunte, Duree); 
	}
	
	
	
	
	
}
