package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.CustomerSatisfaction;


@Repository
public interface CustomerSatisfactionRepo extends JpaRepository<CustomerSatisfaction, Long>{

}
