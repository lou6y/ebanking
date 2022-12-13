package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.ProduitAssurance;

@Repository
public interface ProduitAssuranceRepo extends JpaRepository<ProduitAssurance, Long>{

}
