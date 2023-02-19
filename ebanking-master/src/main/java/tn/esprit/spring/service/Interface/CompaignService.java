package tn.esprit.spring.service.Interface;

import java.util.List;

import tn.esprit.spring.entity.Campaign;

public interface CompaignService {

	public List<Campaign> getCompaigns();
	public Campaign addCompaign(Campaign compaign);
	public void deleteCompaign(Long id);
	public Campaign updateCompaign(Long id, Campaign newCompaign);
	public Campaign getCompaign(Long id);
}
