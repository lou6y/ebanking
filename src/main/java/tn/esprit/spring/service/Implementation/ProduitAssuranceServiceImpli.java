package tn.esprit.spring.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.ProduitAssurance;
import tn.esprit.spring.repository.ProduitAssuranceRepo;
import tn.esprit.spring.service.Interface.ProduitAssuranceService;
@Service
public class ProduitAssuranceServiceImpli implements ProduitAssuranceService {
	@Autowired
	ProduitAssuranceRepo produitAssuranceRepo;
	@Override
	public List<ProduitAssurance> getProduitAssurances() {
		return (List<ProduitAssurance>) (produitAssuranceRepo.findAll());	
	}

	@Override
	public ProduitAssurance addProduitAssurance(ProduitAssurance produitAssurance) {
		return produitAssuranceRepo.save(produitAssurance);
	}

	@Override
	public void deleteProduitAssurance(long id) {
		produitAssuranceRepo.deleteById(id);		
		
	}

	@Override
	public ProduitAssurance updateProduitAssurance(long id, ProduitAssurance produitAssurance) {
		System.out.println(produitAssurance);
		ProduitAssurance ProduitAssuranceModified = produitAssuranceRepo.findById(id).get();
		ProduitAssuranceModified.setTitre(produitAssurance.getTitre());
		ProduitAssuranceModified.setNomCompagnie(produitAssurance.getNomCompagnie());
		ProduitAssuranceModified.setIntemediaire(produitAssurance.getIntemediaire());
		ProduitAssuranceModified.setExclusion(produitAssurance.getExclusion());
		ProduitAssuranceModified.setPrime(produitAssurance.getPrime());
		ProduitAssuranceModified.setFractionPayement(produitAssurance.getFractionPayement());

		produitAssuranceRepo.save(ProduitAssuranceModified); 
		return ProduitAssuranceModified;
	}

	@Override
	public ProduitAssurance getProduitAssurance(long id) {
		return produitAssuranceRepo.findById(id).get();

	}

}
