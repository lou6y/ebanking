package tn.esprit.spring.service.Interface;

import java.util.List;

import tn.esprit.spring.entity.Contrat;


public interface ContratService {
	
List<Contrat> getContracts();
	
Contrat addContract(Contrat car);
	
	void deleteContract(Long id);
	
	Contrat updateContract(Long id, Contrat car);
	
	Contrat getContract(Long id);
}
