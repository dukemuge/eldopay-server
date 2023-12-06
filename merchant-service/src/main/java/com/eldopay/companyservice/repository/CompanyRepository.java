package com.eldopay.companyservice.repository;

import com.eldopay.companyservice.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
