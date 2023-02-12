package tn.esprit.spring.service.Interface;

import java.util.List;

import tn.esprit.spring.entity.CustomerSatisfaction;

public interface CustomerSatisfactionService {

	public List<CustomerSatisfaction> getCustomerSatisfactions();
	public CustomerSatisfaction addCustomerSatisfaction(CustomerSatisfaction customerSatisfaction);
	public void deleteCustomerSatisfaction(Long id);
	public CustomerSatisfaction updateCustomerSatisfaction(Long id, CustomerSatisfaction newCustomerSatisfaction);
	public CustomerSatisfaction getCustomerSatisfaction(Long id);
}
