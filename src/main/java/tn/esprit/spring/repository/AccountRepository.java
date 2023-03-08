package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
  List<Account> findByUser_Id(Long id);

  Account findByIban(String iban);
}
