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
        // Transaction transaction = new Transaction();
        // when(transactionRepository.save(any())).thenCallRealMethod();
        // Transaction savedTransaction = transactionServiceImpl.createTransaction(transaction);
        // transactionServiceImpl.deleteTransaction(savedTransaction);

        // //When
        // Transaction retrievedTransaction = this.transactionServiceImpl.getTransactionById(savedTransaction.getId());
        // List<Transaction> allTransactions = (List<Transaction>) this.transactionServiceImpl.getAllTransactions();
        // //Then
        // assertNull(retrievedTransaction);
        // assertTrue(allTransactions.stream().allMatch((tr) -> tr.getId() != savedTransaction.getId()));


    }

    @Test
    void testGetAllTransactions() {

    }

    @Test
    void testGetTransactionById() {

    }

    @Test
    void testUpdateTransaction() {

    }
}
