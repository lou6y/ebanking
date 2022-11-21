package tn.esprit.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Credit;
import tn.esprit.spring.repository.CreditRepository;
import tn.esprit.spring.service.ICreditService;


@RestController

@RequestMapping("/credit")
public class CreditRestController {
	
	@Autowired
	CreditRepository creditRepository;
	
	@Autowired
	private ICreditService CreditService;
	

	// http://localhost:8084/credit/addCredit
		@PostMapping("/addCredit")	
		public Credit addCredit(@RequestBody Credit c) {
		return CreditService.addCredit(c);
		}
		
	
		// http://localhost:8084/credit/modifyCredit
				@PostMapping("/modifyCredit")
				@ResponseBody	
				public Credit ModifyCredit(@RequestBody Credit c) {
				return CreditService.modifyCredit(c);}
			
			
			//	http://localhost:8084/credit/retrieveAll
			@GetMapping("/retrieveAll")
			public List<Credit> getCredit() {
			 List<Credit> c = CreditService.retrieveAllCredits();

				return c;
			 }

			// http://localhost:8084/credit/remove-credit/{credit-id}
			@DeleteMapping("/remove-credit/{id}")
			@ResponseBody
			public void removeCredit(@PathVariable("offer-id") String creditId) {
				CreditService.deleteCredit(creditId);
			}
			
			
			
			// http://localhost:8084/credit/retrieve-credit/{credit-id}
			@GetMapping("/retrieve-credit/{id}")
			@ResponseBody
			public Credit retrieveCredit(@PathVariable("id") String creditId) {
			return CreditService.retrieveCredit(creditId);
			}
			
			
			
			
			
			
	

}
