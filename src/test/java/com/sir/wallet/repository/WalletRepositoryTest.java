package com.sir.wallet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sir.wallet.model.Wallet;

@SpringBootTest
@ActiveProfiles("test")
public class WalletRepositoryTest {

    @Autowired
    WalletRepository walletRepository;

    @Test
    void save() {
        // Given
        Wallet wallet = new Wallet();

        // When
        Wallet walletResponse = walletRepository.save(wallet);

        // Then
        assertNotNull(walletResponse);
        assertTrue(walletResponse.getId() > 0);
        assertEquals(walletResponse.getName(), wallet.getName());
    }

    @Test
    void update() {
        // Given
        Wallet wallet = walletRepository.save(new Wallet());
        wallet.setName("Ba");

        // When
        Wallet walletUpdate = walletRepository.save(wallet);

        // Then
        assertNotNull(walletUpdate);
        assertEquals(walletUpdate.getName(), wallet.getName());
    }

    // TODO: add test delete
    // @Test
    void delete() {
        // Given
        final Long testId = 50L;
        Wallet wallet = new Wallet();
        walletRepository.save(wallet);

        // When
        walletRepository.delete(wallet);

        // Then
        java.util.List<Wallet> wallets = walletRepository.findAll();
        assertTrue(wallets.stream().anyMatch(pers -> pers.getId() == testId));
    }

    // TODO: add test findById
    // @Test
    void findById() {
        // Given
        final Long testId = 50L;
        Wallet wallet = new Wallet(testId, "Ousmane", 100);
        walletRepository.save(wallet);

        // When
        walletRepository.findById(testId);

        // Then
        Wallet walletFound = walletRepository.findById(testId).orElse(null);
        assertTrue(walletFound != null);
        assertTrue(walletFound.getId() == testId);

    }

    // TODO: add test findAll
    // @Test
    void findByAll() {
        // Given
        walletRepository.save(new Wallet("Diagn", 100));
        walletRepository.save(new Wallet("Mor", 200));
        walletRepository.save(new Wallet("Lo", 200));

        // When
        List<Wallet> wallets = walletRepository.findAll();

        // Then
        assertNotNull(wallets);
        assertTrue(wallets.size() >= 3);
        assertTrue(wallets.stream().anyMatch(wallet -> wallet.getName().equals("Diagn")));
        assertTrue(wallets.stream().anyMatch(wallet -> wallet.getName().equals("Mor")));
        assertTrue(wallets.stream().anyMatch(wallet -> wallet.getName().equals("Lo")));

    }
}
