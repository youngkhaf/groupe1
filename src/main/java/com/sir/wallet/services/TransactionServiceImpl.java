package com.sir.wallet.services;

import com.sir.wallet.model.Transaction;
import com.sir.wallet.model.Wallet;
import com.sir.wallet.repository.TransactionRepository;
import com.sir.wallet.repository.WalletRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    TransactionRepository transactionRepository;
    WalletRepository walletServiceImpl;

    public TransactionServiceImpl(TransactionRepository transactionRepository,WalletRepository walletServiceImpl){
        this.transactionRepository = transactionRepository;
        this.walletServiceImpl = walletServiceImpl;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        Wallet wallet = Optional.of(transaction.getWallet()).orElse(new Wallet());
        // if(wallet != null){
        //     int multiplicator = transaction.getType() == "deposit" ? 1 : -1;
        //     wallet.setBalance(wallet.getBalance() - (transaction.getAmount() * multiplicator));
        //     this.walletServiceImpl.save(wallet);
        // }



       

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
