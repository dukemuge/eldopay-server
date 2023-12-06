package com.eldopay.companyservice.repository.county;

import com.eldopay.companyservice.models.Customer;
import com.eldopay.companyservice.models.county.County;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountyRepository extends JpaRepository<County,Long> {
    County findByUserName(String username);
}
