package com.sir.wallet.repository;

import com.sir.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
//
@Component
public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
