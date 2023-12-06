package com.eldopay.companyservice.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditInformation {
    @Id
    private long id;
    @Column(name = "cardholder_name")
    private String cardholderName;
    @Column(name="card_number")
    private  String cardNumber;
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    @Column(name = "cvv")
    private String cvv;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
//    @ManyToOne
//    @JoinColumn(name = "merchant_id")
//    private Merchant merchant;
//    @ManyToOne
//    @JoinColumn(name = "worker_id")
//    private Worker worker;
}
