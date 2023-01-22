package com.sir.wallet.controller;

import com.sir.wallet.model.Transaction;
import com.sir.wallet.services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/transaction")
public class TransactionController {


    private final TransactionService transactionService;

    @Autowired    
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/transactions")
    public Iterable<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping(value="/{id}")
    public Transaction getTransaction(@RequestParam("id") long id) {
        return this.transactionService.getTransactionById(id);
    }
    @PutMapping("/transactions")
    public Transaction updateTransaction(@RequestBody Transaction transaction) {
        return transactionService.updateTransaction(transaction);
    }

    @DeleteMapping("")
    public void deleteTransaction(@RequestBody Transaction transaction){
        this.transactionService.deleteTransaction(transaction);
    }
    
}
