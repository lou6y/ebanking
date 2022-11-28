package tn.esprit.spring.service.Interface;

import java.util.List;

import tn.esprit.spring.entity.Assureur;


public interface AssureurService {
	List<Assureur> getAssureurs();
	
	Assureur addAssureur(Assureur assureur);
		
		void deleteAssureur(long id);
		
		Assureur updateAssureur(long id, Assureur assureur);
		
		Assureur getAssureur(long id);
}
