package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.Credit;
import tn.esprit.spring.repository.CreditRepository;


@Service
@Slf4j
public class CreditServiceImpl implements ICreditService{
	
	
	@Autowired
	private CreditRepository creditRepository;

	@Override
	public List<Credit> retrieveAllCredits() {
		List<Credit> listCredit= creditRepository.findAll();
		for(Credit c:listCredit)
		{
			log.info("Credit:" + c.getId()+ c.getMontant());
		}
		return listCredit;
	}

	@Override
	public Credit addCredit(Credit c) {
		creditRepository.save(c);
		return c;
	}

	@Override
	public void deleteCredit(String id) {
		creditRepository.deleteById(Long.parseLong(id));

	}
	
	
	

	@Override
	public Credit updateCredit(Credit c) {
		creditRepository.save(c);
		return c;
	}

	@Override
	public Credit retrieveCredit(String id) {
		Credit c= creditRepository.findById(Long.parseLong(id)).get();
		return c;
	}
	
	@Override
	public Credit modifyCredit(Credit C) {
		
		Credit CreditSaved = null;
		CreditSaved = creditRepository.save(C);
		
		return CreditSaved;
	}
	
	
	
	

}
