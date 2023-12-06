package com.eldopay.companyservice.repository.fees;

import com.eldopay.companyservice.models.fees.FeeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeTransactionRepository extends JpaRepository<FeeTransaction,Long> {
}
