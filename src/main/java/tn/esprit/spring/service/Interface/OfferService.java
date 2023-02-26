package tn.esprit.spring.service.Interface;

import java.util.List;

import tn.esprit.spring.entity.Offer;

public interface OfferService {

		List<Offer> getOffers();
	
		List<Offer> addOffer(Offer car);
		
		List<Offer> deleteOffer(Long id);
		
		List<Offer> updateOffer(Long id, Offer car);
		
		Offer getOffer(Long id);
}
