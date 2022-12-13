package tn.esprit.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "ProduitAssurance")
public class ProduitAssurance {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="IdProduitAssurance")
	private long IdProduitAssurance;
	
	@Column(name="titre")
	private String titre;
	
	@Column(name="nomCompagnie")
	private String nomCompagnie;
	
	@Column(name="intemediaire")
	private String intemediaire;
	
	@Column(name="inclusion")
	private String inclusion;
	
	@Column(name="exclusion")
	private String exclusion;
	
	@Column(name="prime")
	private float prime;
	
	@Column(name="fractionPayement")
	private float fractionPayement;
	@ManyToOne
    private Assureur assureur;

	public long getIdProduitAssurance() {
		return IdProduitAssurance;
	}

	public void setIdProduitAssurance(long idProduitAssurance) {
		IdProduitAssurance = idProduitAssurance;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getNomCompagnie() {
		return nomCompagnie;
	}

	public void setNomCompagnie(String nomCompagnie) {
		this.nomCompagnie = nomCompagnie;
	}

	public String getIntemediaire() {
		return intemediaire;
	}

	public void setIntemediaire(String intemediaire) {
		this.intemediaire = intemediaire;
	}

	public String getInclusion() {
		return inclusion;
	}

	public void setInclusion(String inclusion) {
		this.inclusion = inclusion;
	}

	public String getExclusion() {
		return exclusion;
	}

	public void setExclusion(String exclusion) {
		this.exclusion = exclusion;
	}

	public float getPrime() {
		return prime;
	}

	public void setPrime(float prime) {
		this.prime = prime;
	}

	public float getFractionPayement() {
		return fractionPayement;
	}

	public void setFractionPayement(float fractionPayement) {
		this.fractionPayement = fractionPayement;
	}

	public Assureur getAssureur() {
		return assureur;
	}

	public void setAssureur(Assureur assureur) {
		this.assureur = assureur;
	}

	public ProduitAssurance() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
