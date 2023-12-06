package com.eldopay.companyservice.models.county;

import com.eldopay.companyservice.models.Customer;
import com.eldopay.companyservice.models.shop.Merchant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountyPayment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private  long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "county_id",nullable = false)
  private County county;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id",nullable = false)
  private Customer customer;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "merchant_id",nullable = false)
  private Merchant merchant;

  private BigDecimal amount;
  private LocalDateTime dateOfPayment;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id",nullable = false)
  private CountyProduct product;



}
