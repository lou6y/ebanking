package tn.esprit.spring.service.Implementation;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.repository.ContratRepo;

@Service
public class ContratServiceImpli implements Serializable {

	@Autowired
	ContratRepo contractRep;
	public List<Contrat> getContracts() {
		return (List<Contrat>) (contractRep.findAll());
	}

	
	public Contrat addContract(Contrat contract) {
		return contractRep.save(contract);
	}
	
	public void deleteContract(Long id) {
		
		contractRep.deleteById(id);
	}
	public Contrat updateContract(Long id, Contrat newContract) {
		System.out.println(newContract);
			Contrat contractModified = contractRep.findById(id).get();
			contractModified.setDuration(newContract.getDuration());
			contractModified.setDate_debut(newContract.getDate_debut());
			contractModified.setDate_fin(newContract.getDate_fin());
			contractModified.setStatus(newContract.getStatus());
			contractModified.setPrimeCommerciale(newContract.getPrimeCommerciale());
			contractModified.setPrimePure(newContract.getPrimePure());
			contractModified.setAcceptation(newContract.getAcceptation());

			contractRep.save(contractModified); 
			return contractModified;
		}
	public Contrat getContract(Long id) {
		return contractRep.findById(id).get();
	}
	

}
