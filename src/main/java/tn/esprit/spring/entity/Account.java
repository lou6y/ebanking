package tn.esprit.spring.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.audit.Auditable;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "account")
public class Account extends Auditable<String> {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "iban", nullable = false, length = 64)
  private String iban;

  @Column(name = "balance")
  private float balance;

  @ManyToOne
  @JoinColumn(name = "type_id")
  private TransactionLabel type;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;
}
