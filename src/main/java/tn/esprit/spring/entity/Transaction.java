package tn.esprit.spring.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.audit.Auditable;
import tn.esprit.spring.enums.TransactionType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "transaction")
public class Transaction extends Auditable<String> {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "iban_sender", nullable = false, length = 64)
  private String ibanSender;

  @Column(name = "iban_receiver", nullable = false, length = 64)
  private String ibanReceiver;

  @Column(name = "amount")
  private float amount;

  @Column(name = "description", nullable = false, length = 255)
  private String description;

  @Column(name = "execution_date", nullable = false)
  private LocalDateTime executionDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false, length = 16)
  private TransactionType type;

  @ManyToOne
  @JoinColumn(name = "transaction_label_id")
  private TransactionLabel transactionLabel;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "account_id")
  private Account account;
}
