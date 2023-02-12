package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Campaign;

@Repository
public interface CompaignRepo extends JpaRepository<Campaign, Long> {

}
