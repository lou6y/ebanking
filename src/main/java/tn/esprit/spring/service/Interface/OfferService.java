package tn.esprit.spring.service.Interface;

import java.util.List;

import tn.esprit.spring.entity.Offer;

public interface OfferService {

		List<Offer> getOffers();
	
		Offer addOffer(Offer car);
		
		void deleteOffer(Long id);
		
		Offer updateOffer(Long id, Offer car);
		
		Offer getOffer(Long id);
}