package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Assureur;
@Repository

public interface AssureurRepo extends JpaRepository <Assureur,Long>{

}
