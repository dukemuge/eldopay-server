package com.eldopay.companyservice.repository.fees;

import com.eldopay.companyservice.models.fees.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<Student,Long> {
}
