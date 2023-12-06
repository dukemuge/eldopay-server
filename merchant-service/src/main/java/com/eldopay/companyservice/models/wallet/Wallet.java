package com.eldopay.companyservice.models.wallet;

import com.eldopay.companyservice.models.Customer;
import com.eldopay.companyservice.models.handyman.Worker;
import com.eldopay.companyservice.models.shop.Merchant;
import com.eldopay.companyservice.models.uber.Driver;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String walletNumber;
//
//    @Enumerated(EnumType.STRING)
//    private AccountStatus status;
    private BigDecimal availableBalance;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

//    private List<Transaction> transactions = new ArrayList<>();
}
