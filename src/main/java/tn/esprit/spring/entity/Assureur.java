package tn.esprit.spring.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table( name = "Assureur")
public class Assureur {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column( name = "idAssurance")
		private long idAssurance; 
	
	@Column( name="nomAssurance")
	private String nomAssurance;
	
	@Column(name="rue")
	private String rue;
	
	@Column(name="ville")
	private String ville;
	
	@Column(name="codePostal")
	private String codePostal;
	
	@OneToMany(mappedBy="assureur")
    private List<ProduitAssurance> produits_assurances;
	
	
	public long getIdAssurance() {
		return idAssurance;
	}

	public void setIdAssurance(long idAssurance) {
		this.idAssurance = idAssurance;
	}

	public String getNomAssurance() {
		return nomAssurance;
	}

	public void setNomAssurance(String nomAssurance) {
		this.nomAssurance = nomAssurance;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public Assureur() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
