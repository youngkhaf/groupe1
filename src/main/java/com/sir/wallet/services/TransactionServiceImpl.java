package com.sir.wallet.services;

import com.sir.wallet.model.Transaction;
import com.sir.wallet.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        this.assertTransactionExist(id);
        return this.transactionRepository.findById(id).orElse(null);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        this.assertTransactionExist(transaction.getId());
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        this.assertTransactionExist(transaction.getId());
        transactionRepository.deleteById(transaction.getId());

    }

    @Override
    public Iterable<Transaction> getAllTransactions() {
        return this.transactionRepository.findAll();
    }

    public void assertTransactionExist(long id){
        if(!this.transactionRepository.existsById(id)){
                throw new IllegalArgumentException("La transaction avec l'id " + id + " n'existe pas");
        }
    }
}
