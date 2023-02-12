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

	
	public Campaign addCompaign(Campaign compaign) {
		return compaignRep.save(compaign);
	}
	
	public void deleteCompaign(Long id) {
		
		compaignRep.deleteById(id);
	}
	public Campaign updateCompaign(Long id, Campaign newCompaign) {
		System.out.println(newCompaign);
		Campaign compaignModified = compaignRep.findById(id).get();
			compaignModified.setCampaignType(newCompaign.getCampaignType());
			compaignModified.setMainTarget(newCompaign.getMainTarget());
			compaignModified.setTargetHeart(newCompaign.getTargetHeart());
			compaignModified.setSecondaryTarget(newCompaign.getSecondaryTarget());
			compaignModified.setMsgCampaign(newCompaign.getMsgCampaign());
			compaignModified.setSupport(newCompaign.getSupport());
			compaignModified.setReportOperations(newCompaign.getReportOperations());
			
			compaignRep.save(compaignModified); 
			return compaignModified;
		}
	public Campaign getCompaign(Long id) {
		return compaignRep.findById(id).get();
	}
	
	
}
