package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, String>{

}
