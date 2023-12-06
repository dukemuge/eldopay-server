package com.eldopay.companyservice.repository.fees;

import com.eldopay.companyservice.models.fees.SchoolCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolCategoryRepository extends JpaRepository<SchoolCategory,Long> {
}
