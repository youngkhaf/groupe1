package com.sir.wallet.services;

import com.sir.wallet.model.Wallet;
import com.sir.wallet.repository.WalletRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;

    

    @Override
    public Wallet saveWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet getWalletById(Long id) {
        this.assertWalletExist(id);
        return this.walletRepository.findById(id).orElse(null);
    }

    @Override
    public Wallet updateWallet(Long walletId,Wallet wallet) {
        this.assertWalletExist(walletId);
        return walletRepository.save(wallet);
    }

    @Override
    public void deleteWallet(Wallet wallet) {
        this.assertWalletExist(wallet.getId());
        walletRepository.deleteById(wallet.getId());

    }

    @Override
    public Iterable<Wallet> getAllWallets() {
        return this.walletRepository.findAll();
    }

    public void assertWalletExist(long id){
        if(!this.walletRepository.existsById(id)){
                throw new IllegalArgumentException("La wallet avec l'id " + id + " n'existe pas");
        }
    }
}
