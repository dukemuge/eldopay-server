package com.eldopay.companyservice.dtos.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MerchantRatingRequest {
    private Long merchantId;
    private double rating;
}
