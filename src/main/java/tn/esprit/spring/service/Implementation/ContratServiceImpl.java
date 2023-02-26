package tn.esprit.spring.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Contrat;
import tn.esprit.spring.repository.ContratRepo;
import tn.esprit.spring.service.Interface.ContratService;

@Service
public class ContratServiceImpl implements ContratService {

	@Autowired
	ContratRepo contractRep;
	
	
	@Override
	public List<Contrat> getContrats() {
		return contractRep.findAll();
	}

	
	public List<Contrat> addContract(Contrat contract) {
		 contractRep.save(contract);
		 return contractRep.findAll();
	}
	
	public List<Contrat> deleteContract(Long id) {
		
		contractRep.deleteById(id);
		 return contractRep.findAll();
	}
	public List<Contrat> updateContract(Long id, Contrat newContract) {
		/*System.out.println(newContract);
			Contrat contractModified = contractRep.findById(id).get();
			contractModified.setDuration(newContract.getDuration());
			contractModified.setDateDebut(newContract.getDateDebut());
			contractModified.setDateFin(newContract.getDateFin());
			contractModified.setStatus(newContract.getStatus());
			contractModified.setPrimeCommerciale(newContract.getPrimeCommerciale());
			contractModified.setPrimePure(newContract.getPrimePure());
			contractModified.setAcceptation(newContract.getAcceptation());
*/
			contractRep.save(newContract); 
			return contractRep.findAll();
		}
	public Contrat getContract(Long id) {
		return contractRep.findById(id).get();
	}


	
	

}
