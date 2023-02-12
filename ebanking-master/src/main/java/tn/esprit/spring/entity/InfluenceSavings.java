package tn.esprit.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Table( name = "influence")
public class InfluenceSavings {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column( name = "idInfluenceSavings")
	private Long idInfluenceSavings;
	private int age;
	private String job;
	private String marital;
	private String ecucation;
	private boolean default_;
	private int balance;
	private boolean housing;
	private boolean loan;
	private String contact;
	private int day;
	private String month; 
	private int duration;
	private int compaign;
	private int pdays;
	private int previous;
	private String poutcome;
	private boolean y;
	
	


	
}
