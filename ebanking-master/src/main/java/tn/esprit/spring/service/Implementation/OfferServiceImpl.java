
package tn.esprit.spring.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Offer;
import tn.esprit.spring.repository.OfferRepo;
import tn.esprit.spring.service.Interface.OfferService;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	OfferRepo offerRep;
	public List<Offer> getOffers() {
		return (List<Offer>) (offerRep.findAll());
	}

	
	public Offer addOffer(Offer offer) {
		return offerRep.save(offer);
	}
	
	public void deleteOffer(Long id) {
		
		offerRep.deleteById(id);
	}
	public Offer updateOffer(Long id, Offer newOffer) {
		System.out.println(newOffer);
			Offer offerModified = offerRep.findById(id).get();
			offerModified.setOfferName(newOffer.getOfferName());
			offerModified.setDescription(newOffer.getDescription());
			offerModified.setPicture(newOffer.getPicture());
			offerModified.setCharacteristic(newOffer.getCharacteristic());
			offerModified.setAdvantage(newOffer.getAdvantage());
			offerModified.setCreationDate(newOffer.getCreationDate());
			offerModified.setEndDate(newOffer.getEndDate());

			offerRep.save(offerModified); 
			return offerModified;
		}
	public Offer getOffer(Long id) {
		return offerRep.findById(id).get();
	}
	

}
