package com.sir.wallet.controller;

import com.sir.wallet.model.Wallet;
import com.sir.wallet.services.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/wallets")
public class WalletController {


    private final WalletService walletService;

    @Autowired    
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet) {
        return ResponseEntity.status(HttpStatus.CREATED).body(walletService.saveWallet(wallet));
    }

    @GetMapping("/wallets")
    public ResponseEntity<Iterable<Wallet>> getAllWallets() {
        return ResponseEntity.ok(walletService.getAllWallets());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Wallet> getWallet(@RequestParam("id") long id) {
        return ResponseEntity.ok(this.walletService.getWalletById(id));
    }
    @PutMapping("/wallets")
    public ResponseEntity<Wallet> updateWallet(@RequestBody Wallet wallet) {
        return ResponseEntity.status(HttpStatus.CREATED).body((walletService.saveWallet(wallet)));
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteWallet(@RequestBody Wallet wallet){
         this.walletService.deleteWallet(wallet);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
