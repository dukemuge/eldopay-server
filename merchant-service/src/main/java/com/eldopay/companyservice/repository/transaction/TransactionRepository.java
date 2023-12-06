package com.eldopay.companyservice.repository.transaction;

import com.eldopay.companyservice.models.wallet.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
