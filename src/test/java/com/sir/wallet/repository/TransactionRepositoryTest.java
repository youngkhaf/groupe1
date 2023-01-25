package com.sir.wallet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sir.wallet.model.Transaction;
import com.sir.wallet.model.Wallet;

@SpringBootTest
@ActiveProfiles("test")
public class TransactionRepositoryTest {

    @Autowired
    TransactionRepository tansactionRepository;

    @Autowired
    WalletRepository walletRepository;

    Wallet testWallet;
   

    @BeforeEach
    void saveWallet(){
        Wallet toSave = new Wallet();
        toSave.setId(10l);
        testWallet = this.walletRepository.save(toSave);
    }


    @Test
    void save() {
        //Given
        
        Transaction tansaction = new Transaction();
        tansaction.setWallet(testWallet);

        //When
        Transaction tansactionResponse = tansactionRepository.save(tansaction);

        //Then
        assertNotNull(tansactionResponse);
        assertTrue(tansactionResponse.getId() > 0);
        assertEquals(tansactionResponse.getType(), tansaction.getType());
    }


    @Test
    void update() {
        
        Transaction tansaction = new Transaction();
        tansaction.setWallet(testWallet);
        tansaction.setType("Ba");

        //When
        Transaction tansactionUpdate = tansactionRepository.save(tansaction);

        //Then
        assertNotNull(tansactionUpdate);
        assertEquals(tansactionUpdate.getType(), tansaction.getType());
    }





    // TODO: add test delete
    // @Test
    void delete(){
        //Given
        final Long testId = 50L;
        Transaction tansaction = new Transaction();
        tansactionRepository.save(tansaction);

        //When
        tansactionRepository.delete(tansaction);

        //Then
        java.util.List<Transaction> tansactions = tansactionRepository.findAll();
        assertTrue(tansactions.stream().anyMatch(pers -> pers.getId() == testId));
    }

    // TODO: add test findById
   //  @Test
    void findById(){
        //Given
        final Long testId = 50L;
        Transaction tansaction = new Transaction();
        
        tansaction.setWallet(testWallet);
        tansaction.setType("Ba");

        //When
        tansactionRepository.findById(testId);

        //Then
        Transaction tansactionFound = tansactionRepository.findById(testId).orElse(null);
        assertTrue(tansactionFound != null);
        assertTrue(tansactionFound.getId() == testId);

    }

    // TODO: add test findAll
     //   @Test
    void findByAll(){
        

        //Given
        tansactionRepository.save(new Transaction( testWallet, 100,"Diagn"));
        tansactionRepository.save(new Transaction(testWallet,200,"Mor"));
        tansactionRepository.save(new Transaction(testWallet,200,"Lo"));

        //When
        List<Transaction> tansactions = tansactionRepository.findAll();

        //Then
        assertNotNull(tansactions);
        assertTrue(tansactions.size() >= 3);
        assertTrue(tansactions.stream().anyMatch(tansaction -> tansaction.getType().equals("Diagn")));
        assertTrue(tansactions.stream().anyMatch(tansaction -> tansaction.getType().equals("Mor")));
        assertTrue(tansactions.stream().anyMatch(tansaction -> tansaction.getType().equals("Lo")));

    }
}
