package com.sir.wallet.controller;

import com.sir.wallet.model.Transaction;
import com.sir.wallet.services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/transactions")
public class TransactionController {


    private final TransactionService transactionService;

    @Autowired    
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(transaction));
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.transactionService.getTransactionById(id));
    }
    @PutMapping("")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.status(HttpStatus.CREATED).body((transactionService.updateTransaction(transaction)));
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteTransaction(@RequestBody Transaction transaction){
         this.transactionService.deleteTransaction(transaction);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
