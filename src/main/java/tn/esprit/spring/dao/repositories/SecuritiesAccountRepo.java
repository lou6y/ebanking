package tn.esprit.spring.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.SecuritiesAccount;

@Repository
public interface SecuritiesAccountRepo extends JpaRepository<SecuritiesAccount, Long>{

}
