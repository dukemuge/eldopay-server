package com.eldopay.companyservice.repository.county;

import com.eldopay.companyservice.models.county.CountyPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<CountyPayment,Long> {
}
