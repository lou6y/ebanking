package tn.esprit.spring.service.Implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.config.TransactionsParams;
import tn.esprit.spring.entity.Account;
import tn.esprit.spring.entity.Transaction;
import tn.esprit.spring.enums.TransactionType;
import tn.esprit.spring.exceptions.NotAllowedOperationException;
import tn.esprit.spring.exceptions.ResourceNotFoundException;
import tn.esprit.spring.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class TransactionService {
  private final TransactionRepository transactionRepository;
  private final AccountService accountService;
  private final TransactionsParams transactionsParams;

  public TransactionService(
      final TransactionRepository transactionRepository,
      final AccountService accountService,
      final TransactionsParams transactionsParams) {
    this.transactionRepository = transactionRepository;
    this.accountService = accountService;
    this.transactionsParams = transactionsParams;
  }

  public Transaction saveTransaction(final Transaction transaction) {

    if (TransactionType.TRANSFER.equals(transaction.getType())) {
      processTransactionTransfer(transaction);
    }
    return transactionRepository.save(transaction);
  }

  public void deleteTransaction(final Long id) {
    final Transaction transaction = getTransaction(id);
    if (!TransactionType.STANDING_ORDER.equals(transaction.getType())) {
      throw new NotAllowedOperationException("cannot delete transaction with type Transfer");
    }
    transactionRepository.delete(transaction);
  }

  public Transaction getTransaction(final Long id) {
    return transactionRepository
        .findById(id)
        .orElseThrow(
            () -> {
              log.error("cannot found transaction with id '{}'", id);
              return new ResourceNotFoundException("cannot found transaction");
            });
  }

  public List<Transaction> getAllTransactionByAccountId(final Long id) {
    return transactionRepository.findByAccount_Id(id);
  }

  public void processTransactionTransfer(final Transaction transaction) {

    final Account accountSender = accountService.getAccountByIban(transaction.getIbanSender());
    // check if the sender have the required balance to do the transaction (with the bank
    // commission)
    final Set<String> topFive = getTopFiveTransactionIban();
    final float fees;
    if (topFive.contains(transaction.getIbanSender())) {
      fees = transactionsParams.getVipRate();
    } else {
      fees = transactionsParams.getRate();
    }

    if (accountSender.getBalance() < (transaction.getAmount() + fees)) {
      throw new NotAllowedOperationException("You don't have the required amount");
    }
    // subtract amount from sender account
    accountSender.setBalance(accountSender.getBalance() - (transaction.getAmount() + fees));
    accountService.updateAccount(accountSender);

    // add amount to receiver account
    final Account accountReceiver = accountService.getAccountByIban(transaction.getIbanReceiver());
    accountReceiver.setBalance(accountReceiver.getBalance() + transaction.getAmount());
    accountService.updateAccount(accountReceiver);
  }

  private Set<String> getTopFiveTransactionIban() {
    return transactionRepository.getTopFiveTransactionIban().keySet();
  }

  @Scheduled(cron = "0 * * * * *") //       0 0 4 ? * *
  public void processStandingOrders() {

    log.info("Executing SO...");
    final LocalDateTime now = LocalDateTime.now();
    final int lastDayOfMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
    List<Transaction> additionalStandingOrders = new ArrayList<>();
    if ((Month.FEBRUARY.equals(now.getMonth())
            || Month.APRIL.equals(now.getMonth())
            || Month.JUNE.equals(now.getMonth())
            || Month.SEPTEMBER.equals(now.getMonth())
            || Month.NOVEMBER.equals(now.getMonth()))
        && now.getDayOfMonth() == lastDayOfMonth) {
      additionalStandingOrders =
          transactionRepository.findByDayOfMonth(TransactionType.STANDING_ORDER, lastDayOfMonth);
    }

    // get all standing order by the day of the month of the execution date
    final List<Transaction> standingOrders =
        transactionRepository.findByTypeAndCurrentDay(TransactionType.STANDING_ORDER);
    standingOrders.addAll(additionalStandingOrders);

    // loop all standing order and create transaction of type transfer for each one
    standingOrders.forEach(
        transaction -> {
          transaction.setId(null);
          transaction.setType(TransactionType.TRANSFER);
          transaction.setExecutionDate(LocalDateTime.now());
          saveTransaction(transaction);
        });
  }
}
