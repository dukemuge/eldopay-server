package com.eldopay.companyservice.repository.county;

import com.eldopay.companyservice.models.county.CountyProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountyProductRepository extends JpaRepository<CountyProduct,Long> {
}
