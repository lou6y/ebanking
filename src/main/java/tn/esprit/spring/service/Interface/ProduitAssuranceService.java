package tn.esprit.spring.service.Interface;

import java.util.List;

import tn.esprit.spring.entity.ProduitAssurance;



public interface ProduitAssuranceService {
List<ProduitAssurance> getProduitAssurances();
	
ProduitAssurance addProduitAssurance(ProduitAssurance produitAssurance);
		
		void deleteProduitAssurance(long id);
		
		ProduitAssurance updateProduitAssurance(long id, ProduitAssurance produitAssurance);
		
		ProduitAssurance getProduitAssurance(long id);

}
