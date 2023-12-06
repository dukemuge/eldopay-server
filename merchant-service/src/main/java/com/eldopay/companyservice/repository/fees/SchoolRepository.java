package com.eldopay.companyservice.repository.fees;

import com.eldopay.companyservice.models.Customer;
import com.eldopay.companyservice.models.fees.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School,Long> {
    School findByUserName(String username);
}
