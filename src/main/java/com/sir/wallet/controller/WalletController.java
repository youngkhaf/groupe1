package com.sir.wallet.controller;

import com.sir.wallet.model.Wallet;
import com.sir.wallet.services.WalletService;

import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {


    private final WalletService walletService;


    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/wallets/{id}")
    public Wallet getWallet(@PathVariable long id) {
        return walletService.getWalletById(id);
    }

    @PutMapping("/wallets/{id}")
    public Wallet updateWallet(@PathVariable long id, @RequestBody Wallet wallet) {
        return walletService.updateWallet(id, wallet);
    }


    @PostMapping("/wallets")
    public Wallet createWallet(@RequestBody Wallet wallet) {
        return walletService.saveWallet(wallet);
    }

    @GetMapping("/wallets")
    public Iterable<Wallet> getAllWallets() {
        return walletService.getAllWallets();
    }


    @DeleteMapping("")
    public void deleteWallet(@RequestBody Wallet wallet){
        this.walletService.deleteWallet(wallet);
    }
}
