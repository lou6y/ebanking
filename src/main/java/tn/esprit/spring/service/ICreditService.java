package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Credit;

public interface ICreditService {

	 List<Credit> retrieveAllCredits(); 
	 Credit addCredit(Credit C);
	 void deleteCredit(String creditId);
	 Credit updateCredit(Credit C);
	 Credit retrieveCredit(String creditId);
	 Credit modifyCredit(Credit C);
	
	
	
}
