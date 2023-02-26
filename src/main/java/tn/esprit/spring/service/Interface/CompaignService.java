package tn.esprit.spring.service.Interface;

import java.util.List;

import tn.esprit.spring.entity.Campaign;

public interface CompaignService {

	public List<Campaign> getCompaigns();
	public List<Campaign> addCompaign(Campaign compaign);
	public List<Campaign> deleteCompaign(Long id);
	public List<Campaign> updateCompaign(Long id, Campaign newCompaign);
	public Campaign getCompaign(Long id);
}
