package tn.esprit.spring.service.Interface;

import java.util.List;

import tn.esprit.spring.entity.Contrat;


public interface ContratService {
	
List<Contrat> getContrats();
	
List<Contrat> addContract(Contrat car);
	
List<Contrat> deleteContract(Long id);
	
List<Contrat> updateContract(Long id, Contrat car);
	
	Contrat getContract(Long id);
}
