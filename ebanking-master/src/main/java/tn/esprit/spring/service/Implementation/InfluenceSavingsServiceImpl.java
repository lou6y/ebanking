package tn.esprit.spring.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.InfluenceSavings;
import tn.esprit.spring.repository.InfluenceSavingsRepo;
import tn.esprit.spring.service.Interface.InfluenceSavingsService;

@Service
public class InfluenceSavingsServiceImpl implements InfluenceSavingsService {

	@Autowired
	InfluenceSavingsRepo influenceSavingsRep;
	
	public List<InfluenceSavings> getInfluenceSavingss() {
		return (List<InfluenceSavings>) (influenceSavingsRep.findAll());
	}

	
	public InfluenceSavings addInfluenceSavings(InfluenceSavings influenceSavings) {
		return influenceSavingsRep.save(influenceSavings);
	}
	
	public void deleteInfluenceSavings(Long id) {
		
		influenceSavingsRep.deleteById(id);
	}
	public InfluenceSavings updateInfluenceSavings(Long id, InfluenceSavings newInfluenceSavings) {
		System.out.println(newInfluenceSavings);
		InfluenceSavings influenceSavingsModified = influenceSavingsRep.findById(id).get();
		influenceSavingsModified.setAge(newInfluenceSavings.getAge());
		influenceSavingsModified.setJob(newInfluenceSavings.getJob());
		influenceSavingsModified.setMarital(newInfluenceSavings.getMarital());
		influenceSavingsModified.setEcucation(newInfluenceSavings.getEcucation());
		influenceSavingsModified.setDefault_(newInfluenceSavings.isDefault_());
		influenceSavingsModified.setBalance(newInfluenceSavings.getBalance());
		influenceSavingsModified.setHousing(newInfluenceSavings.isHousing());
		influenceSavingsModified.setLoan(newInfluenceSavings.isLoan());
		influenceSavingsModified.setContact(newInfluenceSavings.getContact());
		influenceSavingsModified.setDay(newInfluenceSavings.getDay());
		influenceSavingsModified.setMonth(newInfluenceSavings.getMonth());
		influenceSavingsModified.setDuration(newInfluenceSavings.getDuration());
		influenceSavingsModified.setCompaign(newInfluenceSavings.getCompaign());
		influenceSavingsModified.setPdays(newInfluenceSavings.getPdays());
		influenceSavingsModified.setPrevious(newInfluenceSavings.getPrevious());
		influenceSavingsModified.setPoutcome(newInfluenceSavings.getPoutcome());
		influenceSavingsModified.setY(newInfluenceSavings.isY());
				
		
		influenceSavingsRep.save(influenceSavingsModified); 
			return influenceSavingsModified;
		}
	public InfluenceSavings getInfluenceSavings(Long id) {
		return influenceSavingsRep.findById(id).get();
	}
	
	
	
}
