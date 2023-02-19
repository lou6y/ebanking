package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Offer;
import tn.esprit.spring.service.Implementation.OfferServiceImpl;



	
	@CrossOrigin(origins = "*")
	@RestController
	@RequestMapping("/offers")
	public class OfferController {
		
		@Autowired
		OfferServiceImpl offerServiceImpl;
		
		@GetMapping("/")
		public List<Offer> getOffers() {			
			return offerServiceImpl.getOffers();
		}
		
		@PostMapping("/add")
		public Offer addOffer(@RequestBody Offer offer){
			return offerServiceImpl.addOffer(offer);
		}
		
		
		@DeleteMapping("/{id}")  
		public void deleteOffer(@PathVariable("id") Long id) {
			offerServiceImpl.deleteOffer(id);
		}
		
		@PutMapping("/{id}")
		public Offer updateOffer(@PathVariable("id") Long id, @RequestBody Offer newOffer) {
			System.out.println(newOffer);
			return offerServiceImpl.updateOffer(id, newOffer);
		}

		@GetMapping("/{id}")
		public Offer getContract(@PathVariable("id") Long id) {
			return offerServiceImpl.getOffer(id);
		}

}
	
	
	
