package tn.esprit.spring.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Table( name = "CustomerSatisfaction")
public class CustomerSatisfaction {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column( name = "idCustomerSatisfaction")
	
	
	private Long idCustomerSatisfaction; 
	@Temporal(TemporalType.DATE)
	private Date date_received;
	private String product;
	private String issue;
	private Long consumer_complaint_narrative;
	private Long company_public_response;
	private String state;
	private String submitted_via; 
	private boolean consumer_disputed;
	
	
	//@OneToMany(cascade = CascadeType.ALL, mappedBy="customerSatisfaction")
	//private Set<CustomerSatisfaction> customerSatisfactions;
}
