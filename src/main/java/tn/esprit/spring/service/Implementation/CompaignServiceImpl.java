package tn.esprit.spring.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Campaign;
import tn.esprit.spring.repository.CompaignRepo;
import tn.esprit.spring.service.Interface.CompaignService;


@Service
public class CompaignServiceImpl implements CompaignService {

	@Autowired
	CompaignRepo compaignRep;
	
	public List<Campaign> getCompaigns() {
		return (List<Campaign>) (compaignRep.findAll());
	}

	
	public List<Campaign> addCompaign(Campaign compaign) {
	 compaignRep.save(compaign);
		return compaignRep.findAll();
	}
	
	public List<Campaign> deleteCompaign(Long id) {
		
		compaignRep.deleteById(id);
		return compaignRep.findAll();
	}
	public List<Campaign> updateCompaign(Long id, Campaign newCompaign) {
		System.out.println(newCompaign);
		/*Campaign compaignModified = compaignRep.findById(id).get();
			compaignModified.setCampaignType(newCompaign.getCampaignType());
			compaignModified.setMainTarget(newCompaign.getMainTarget());
			compaignModified.setTargetHeart(newCompaign.getTargetHeart());
			compaignModified.setSecondaryTarget(newCompaign.getSecondaryTarget());
			compaignModified.setMsgCampaign(newCompaign.getMsgCampaign());
			compaignModified.setSupport(newCompaign.getSupport());
			compaignModified.setReportOperations(newCompaign.getReportOperations());*/
			
			compaignRep.save(newCompaign); 
			return compaignRep.findAll();
		}
	public Campaign getCompaign(Long id) {
		return compaignRep.findById(id).get();
	}
	
	
}
