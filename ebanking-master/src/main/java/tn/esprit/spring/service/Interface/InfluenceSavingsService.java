package tn.esprit.spring.service.Interface;

import java.util.List;

import tn.esprit.spring.entity.InfluenceSavings;

public interface InfluenceSavingsService {

	public List<InfluenceSavings> getInfluenceSavingss();
	public InfluenceSavings addInfluenceSavings(InfluenceSavings influenceSavings);
	public void deleteInfluenceSavings(Long id);
	public InfluenceSavings updateInfluenceSavings(Long id, InfluenceSavings newInfluenceSavings);
	public InfluenceSavings getInfluenceSavings(Long id);
}
