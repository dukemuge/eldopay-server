package com.eldopay.companyservice.dtos.shop;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class AddItemRequest {
    private long productId;
    private String size;
    private int quantity;
    private BigDecimal price;

}
