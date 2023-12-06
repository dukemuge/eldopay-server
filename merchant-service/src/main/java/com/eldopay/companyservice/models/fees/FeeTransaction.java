package com.eldopay.companyservice.models.fees;

import com.eldopay.companyservice.models.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "fee_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeeTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private LocalDate dateOfCreation;
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

}
