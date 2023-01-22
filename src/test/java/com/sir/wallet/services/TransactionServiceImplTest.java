package com.sir.wallet.services;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sir.wallet.model.Transaction;
import com.sir.wallet.repository.TransactionRepository;
@SpringBootTest
@ActiveProfiles("test")
public class TransactionServiceImplTest {

    @InjectMocks
    TransactionServiceImpl transactionServiceImpl;


    @Mock
    TransactionRepository transactionRepository;

    @Test
    void testAssertTransactionExist() {
        //Given 
        when(transactionRepository.existsById(10L)).thenReturn(true);
        when(transactionRepository.existsById(9L)).thenReturn(false);


        //When
        transactionServiceImpl.assertTransactionExist(10L);

        //Then
        assertDoesNotThrow( () -> transactionServiceImpl.assertTransactionExist(10L));
        assertThrows(IllegalArgumentException.class, () ->transactionServiceImpl.assertTransactionExist(9L));
        

    }

    @Test
    void testCreateTransaction() {
                //Given
                Transaction transaction = new Transaction();
                transaction.setId(1L);
                when(transactionRepository.save(any())).thenReturn(transaction);
        
                // doThrow(new RuntimeException("Error")).when(transactionRepository).save(any());
        
                //When
                Transaction transactionResponse = transactionServiceImpl.createTransaction(transaction);
        
                //Then
                assertEquals(1L, transactionResponse.getId());
                verify(transactionRepository, times(1)).save(transaction);

    }

    @Test
    void testDeleteTransaction() {
        Transaction transaction = new Transaction();
        when(transactionRepository.existsById(any())).thenReturn(true);
        

         //Then
        transactionServiceImpl.deleteTransaction(transaction);
       
        


    }

    @Test
    void testGetAllTransactions() {

        List<Transaction> transactions = new ArrayList<>();
       

        for(int i =0;i < 4;i++){
            transactions.add(new Transaction(i*30+2,"test"));
        }
       
        when(transactionRepository.findAll()).thenReturn(transactions);
        List<Transaction> retrievedTransactions = (List<Transaction>) this.transactionServiceImpl.getAllTransactions();
        assertTrue(retrievedTransactions.stream()
                        .allMatch((rT) -> transactions
                        .stream()
                        .anyMatch((tr)-> tr.getId() == rT.getId())));

    }

    @Test
    void testGetTransactionById() {
        long id = 10l;
        when(transactionRepository.existsById(id)).thenReturn(true);

        Transaction savedTransaction = new Transaction(id,null,100,"test");
        when(this.transactionRepository.findById(id)).thenReturn(Optional.of(savedTransaction));


        Transaction retrievedTransaction = this.transactionServiceImpl.getTransactionById(id);
        assertTrue(savedTransaction.getId() == retrievedTransaction.getId());
        assertEquals(savedTransaction, retrievedTransaction);

    }

    @Test
    void testUpdateTransaction() {
        long id = 10l;
        when(transactionRepository.existsById(id)).thenReturn(true);
        Transaction updatedTransaction = new Transaction(id,null,1000,"testUpdated");
        when(transactionRepository.save(any())).thenReturn(updatedTransaction);
        
        Transaction transaction = transactionServiceImpl.updateTransaction(updatedTransaction);
        assertEquals(transaction,updatedTransaction);

    }
}
