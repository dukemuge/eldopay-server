package com.eldopay.companyservice.dtos.shop;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequest {

    private  long id;
    private long customerId;
    private List<OrderItem> orderItems =new ArrayList<>();
    private LocalDateTime deliveryDate;
    private Address address;
    private CreditInformation paymentDetails =new CreditInformation();
    private BigDecimal totalPrice;
    private BigDecimal totalDiscountedPrice;
    private  BigDecimal discount;
    private int totalItems;
    private long merchantId;
}
