package tn.esprit.spring.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user", 
uniqueConstraints = { 
  @UniqueConstraint(columnNames = "username"),
  @UniqueConstraint(columnNames = "email") 
})
public class User {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long id;
	@NotBlank
	  @Size(max = 20)
	  private String username;

	  @NotBlank
	  @Size(max = 50)
	  @Email
	  private String email;

	  @NotBlank
	  @Size(max = 120)
	  private String password;

	  private String name;
	  
	  private String lastname;
	  
	  @Temporal (TemporalType.DATE)
	  private Date birthday;
	  
	  private String gender;
	  
	  private int annualIncome;
	  
	  private int spendingScore;
	  
	  private String Cluster;
	  
	  @Temporal (TemporalType.DATE)
	  private Date creationDate;
	  
	  private Boolean verified;
	  
	  private String token;
	  
	  @Column(columnDefinition = "TIMESTAMP")
	  private LocalDateTime tokenCreationDate;
	  
	  private String verificationCode;
	  
	  @ManyToMany(fetch = FetchType.LAZY)
	  @JoinTable(  name = "user_roles", 
	        joinColumns = @JoinColumn(name = "user_id"), 
	        inverseJoinColumns = @JoinColumn(name = "role_id"))
	  private Set<Role> roles = new HashSet<>();
	  
	  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	  private Set<SecuritiesAccount> Saccounts;
	  
	public User() {
		super();
	}

	public User(String username, String email, String password) {
	    this.username = username;
	    this.email = email;
	    this.password = password;
	  }
	
	public User(Long id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password, String name, String lastname, Date birthday, String gender,
			int annualIncome,int spendingScore,String Cluster, Date creationDate, Boolean verified, String token, LocalDateTime tokenCreationDate, String verificationCode,
			Set<Role> roles, Set<SecuritiesAccount> Saccounts) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.birthday = birthday;
		this.gender = gender;
		this.annualIncome = annualIncome;
		this.spendingScore = spendingScore;
		this.Cluster = Cluster;
		this.creationDate = creationDate;
		this.verified = verified;
		this.token = token;
		this.tokenCreationDate = tokenCreationDate;
		this.verificationCode = verificationCode;
		this.roles = roles;
		this.Saccounts = Saccounts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public Date getBirthday() {
		return birthday;
	}



	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}


	public int getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(int annualIncome) {
		this.annualIncome = annualIncome;
	}

	public Date getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}



	public Boolean getVerified() {
		return verified;
	}



	public void setVerified(Boolean verified) {
		this.verified = verified;
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	public LocalDateTime getTokenCreationDate() {
		return tokenCreationDate;
	}



	public void setTokenCreationDate(LocalDateTime tokenCreationDate) {
		this.tokenCreationDate = tokenCreationDate;
	}



	public String getVerificationCode() {
		return verificationCode;
	}



	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public int getSpendingScore() {
		return spendingScore;
	}

	public void setSpendingScore(int spendingScore) {
		this.spendingScore = spendingScore;
	}

	public String getCluster() {
		return Cluster;
	}

	public void setCluster(String cluster) {
		Cluster = cluster;
	}

	public Set<SecuritiesAccount> getSaccounts() {
		return Saccounts;
	}

	public void setSaccounts(Set<SecuritiesAccount> saccounts) {
		Saccounts = saccounts;
	}
	  
	
}
