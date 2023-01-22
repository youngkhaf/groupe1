package com.sir.wallet.services;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sir.wallet.model.Wallet;
import com.sir.wallet.repository.WalletRepository;

@SpringBootTest
@ActiveProfiles("test")
public class WalletServiceImplTest {

    @InjectMocks
    WalletServiceImpl walletServiceImpl;


    @Mock
    WalletRepository walletRepository;

    @Test
    void testAssertWalletExist() {
        //Given 
        when(walletRepository.existsById(10L)).thenReturn(true);
        when(walletRepository.existsById(9L)).thenReturn(false);


        //When
        walletServiceImpl.assertWalletExist(10L);

        //Then
        assertDoesNotThrow( () -> walletServiceImpl.assertWalletExist(10L));
        assertThrows(IllegalArgumentException.class, () ->walletServiceImpl.assertWalletExist(9L));
        

    }

    @Test
    void testSaveWallet() {
                //Given
                Wallet wallet = new Wallet();
                wallet.setId(1L);
                when(walletRepository.save(any())).thenReturn(wallet);
        
                // doThrow(new RuntimeException("Error")).when(walletRepository).save(any());
        
                //When
                Wallet walletResponse = walletServiceImpl.saveWallet(wallet);
        
                //Then
                assertEquals(1L, walletResponse.getId());
                verify(walletRepository, times(1)).save(wallet);

    }

    @Test
    void testDeleteWallet() {
        Wallet wallet = new Wallet();
        when(walletRepository.existsById(any())).thenReturn(true);
        

         //Then
        walletServiceImpl.deleteWallet(wallet);
       
        


    }

    @Test
    void testGetAllWallets() {

        List<Wallet> wallets = new ArrayList<>();
       

        for(int i =0;i < 4;i++){
            wallets.add(new Wallet(i*30+2,"test"));
        }
       
        when(walletRepository.findAll()).thenReturn(wallets);
        List<Wallet> retrievedWallets = (List<Wallet>) this.walletServiceImpl.getAllWallets();
        assertTrue(retrievedWallets.stream()
                        .allMatch((rT) -> wallets
                        .stream()
                        .anyMatch((tr)-> tr.getId() == rT.getId())));

    }

    @Test
    void testGetWalletById() {
        long id = 10l;
        when(walletRepository.existsById(id)).thenReturn(true);

        Wallet savedWallet = new Wallet(id,"test",100);
        when(this.walletRepository.findById(id)).thenReturn(Optional.of(savedWallet));


        Wallet retrievedWallet = this.walletServiceImpl.getWalletById(id);
        assertTrue(savedWallet.getId() == retrievedWallet.getId());
        assertEquals(savedWallet, retrievedWallet);

    }

    @Test
    void testUpdateWallet() {
        long id = 10l;
        when(walletRepository.existsById(id)).thenReturn(true);
        Wallet updatedWallet = new Wallet(id,"test",1000);
        when(walletRepository.save(any())).thenReturn(updatedWallet);
        
        Wallet wallet = walletServiceImpl.updateWallet(id,updatedWallet);
        assertEquals(wallet,updatedWallet);

    }
}
