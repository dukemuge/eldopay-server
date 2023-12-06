package com.eldopay.companyservice.repository.wallet;


import com.eldopay.companyservice.models.wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository  extends JpaRepository<Wallet,Long> {
}
