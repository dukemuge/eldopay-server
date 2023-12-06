package com.eldopay.companyservice.models.utility;


import com.eldopay.companyservice.models.Customer;
import com.eldopay.companyservice.models.enums.PaymentMode;
import com.eldopay.companyservice.models.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "utility_payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtilityPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigDecimal amount;
    private String transactionId;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

}
