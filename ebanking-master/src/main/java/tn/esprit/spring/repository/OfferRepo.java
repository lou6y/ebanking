package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Offer;

@Repository
public interface OfferRepo extends JpaRepository<Offer, Long> {

}
