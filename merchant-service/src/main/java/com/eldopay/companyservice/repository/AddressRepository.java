package com.eldopay.companyservice.repository;

import com.eldopay.companyservice.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
