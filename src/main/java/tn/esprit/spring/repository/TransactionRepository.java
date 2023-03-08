package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.entity.Transaction;
import tn.esprit.spring.enums.TransactionType;

import java.util.List;
import java.util.Map;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findByAccount_Id(Long id);

  @Query(
      "select t from Transaction t where t.type = :type and EXTRACT(DAY FROM t.executionDate)=EXTRACT(DAY FROM CURRENT_TIMESTAMP)")
  List<Transaction> findByTypeAndCurrentDay(@Param("type") TransactionType type);

  @Query(
      "select t from Transaction t where t.type = :type and  EXTRACT(DAY FROM t.executionDate) > :dayOfMonth")
  List<Transaction> findByDayOfMonth(
      @Param("type") TransactionType type, @Param("dayOfMonth") int dayOfMonth);

  @Query(
      value =
          "SELECT t.iban_sender, COUNT(*) AS trNumber  FROM transaction t WHERE MONTH(t.execution_date)=MONTH(now()) AND YEAR(t.execution_date)=YEAR(now()) GROUP BY t.iban_sender ORDER BY trNumber DESC LIMIT 5",
      nativeQuery = true)
  Map<String, Integer> getTopFiveTransactionIban();
}
