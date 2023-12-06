package com.eldopay.companyservice.repository;

import com.eldopay.companyservice.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByUserName(String username);
}
