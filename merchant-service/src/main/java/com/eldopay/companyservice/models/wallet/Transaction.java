package com.eldopay.companyservice.models.wallet;

import com.eldopay.companyservice.models.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue
    private long id;
    private BigDecimal amount;
    private LocalDateTime dateOfTransaction;
    private String description;
    private String transactionCode;
    private TransactionType transactionType;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

}
