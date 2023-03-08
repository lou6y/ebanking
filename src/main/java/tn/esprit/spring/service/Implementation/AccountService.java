package tn.esprit.spring.service.Implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Account;
import tn.esprit.spring.exceptions.NotAllowedOperationException;
import tn.esprit.spring.exceptions.ResourceNotFoundException;
import tn.esprit.spring.helpers.IbanGenerator;
import tn.esprit.spring.repository.AccountRepository;

import java.util.List;

@Service
@Slf4j
public class AccountService {
  private final AccountRepository accountRepository;

  public AccountService(final AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account saveAccount(final Account account) {
    account.setIban(IbanGenerator.calculateIBAN());
    return accountRepository.save(account);
  }

  public Account getAccount(final Long id) {
    return accountRepository
        .findById(id)
        .orElseThrow(
            () -> {
              log.error("cannot found account with id '{}'", id);
              return new ResourceNotFoundException("cannot found account");
            });
  }

  public List<Account> getAllAccountsByUser(final Long idUser) {
    return accountRepository.findByUser_Id(idUser);
  }

  public void deleteAccountById(final Long id) {
    final Account account = getAccount(id);
    if (account.getBalance() == 0) {
      accountRepository.deleteById(id);
    } else {
      log.error(
          "Account balance must be equal to zero please fix your balance to zero by doing transactions");
      throw new NotAllowedOperationException(
          "Account balance must be equal to zero please fix your balance to zero by doing transactions");
    }
  }

  public Account updateAccount(final Account account) {
    return accountRepository.save(account);
  }

  public Account getAccountByIban(final String iban) {
    return accountRepository.findByIban(iban);
  }
}
