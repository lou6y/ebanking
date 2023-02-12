package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.InfluenceSavings;

@Repository
public interface InfluenceSavingsRepo extends JpaRepository<InfluenceSavings, Long>{

}
