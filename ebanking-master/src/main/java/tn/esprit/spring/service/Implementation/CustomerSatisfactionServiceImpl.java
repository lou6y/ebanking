package tn.esprit.spring.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.CustomerSatisfaction;
import tn.esprit.spring.repository.CustomerSatisfactionRepo;
import tn.esprit.spring.service.Interface.CustomerSatisfactionService;

@Service
public class CustomerSatisfactionServiceImpl implements CustomerSatisfactionService{
	
	@Autowired
	CustomerSatisfactionRepo customerSatisfactionRep;
	public List<CustomerSatisfaction> getCustomerSatisfactions() {
		return (List<CustomerSatisfaction>) (customerSatisfactionRep.findAll());
	}

	
	public CustomerSatisfaction addCustomerSatisfaction(CustomerSatisfaction customerSatisfaction) {
		return customerSatisfactionRep.save(customerSatisfaction);
	}
	
	public void deleteCustomerSatisfaction(Long id) {
		
		customerSatisfactionRep.deleteById(id);
	}
	public CustomerSatisfaction updateCustomerSatisfaction(Long id, CustomerSatisfaction newCustomerSatisfaction) {
		System.out.println(newCustomerSatisfaction);
		CustomerSatisfaction customerSatisfactionModified = customerSatisfactionRep.findById(id).get();
		customerSatisfactionModified.setDate_received(newCustomerSatisfaction.getDate_received());
		customerSatisfactionModified.setProduct(newCustomerSatisfaction.getProduct());
		customerSatisfactionModified.setIssue(newCustomerSatisfaction.getIssue());
		customerSatisfactionModified.setConsumer_complaint_narrative(newCustomerSatisfaction.getConsumer_complaint_narrative());
		customerSatisfactionModified.setCompany_public_response(newCustomerSatisfaction.getCompany_public_response());
		customerSatisfactionModified.setState(newCustomerSatisfaction.getState());
		customerSatisfactionModified.setSubmitted_via(newCustomerSatisfaction.getSubmitted_via());
		customerSatisfactionModified.setConsumer_disputed(newCustomerSatisfaction.isConsumer_disputed());
		
			customerSatisfactionRep.save(customerSatisfactionModified); 
			return customerSatisfactionModified;
		}
	public CustomerSatisfaction getCustomerSatisfaction(Long id) {
		return customerSatisfactionRep.findById(id).get();
	}
}
