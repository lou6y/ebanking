package tn.esprit.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Transaction;
import tn.esprit.spring.service.Implementation.TransactionService;

import java.util.List;

@RestController
public class TransactionController {
  private final TransactionService transactionService;

  public TransactionController(final TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping("/transaction")
  public ResponseEntity<Transaction> saveTransaction(@RequestBody final Transaction transaction) {
    return new ResponseEntity<>(
        transactionService.saveTransaction(transaction), HttpStatus.CREATED);
  }

  @GetMapping("/transaction/{id}")
  public ResponseEntity<Transaction> getTransaction(@PathVariable final Long id) {
    return new ResponseEntity<>(transactionService.getTransaction(id), HttpStatus.OK);
  }

  @GetMapping("/transaction/all/{id}")
  public ResponseEntity<List<Transaction>> getAllTransactionByAccountId(
      @PathVariable final Long id) {
    return new ResponseEntity<>(transactionService.getAllTransactionByAccountId(id), HttpStatus.OK);
  }
}
