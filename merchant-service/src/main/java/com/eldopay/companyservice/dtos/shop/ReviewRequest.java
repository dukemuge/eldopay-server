package com.eldopay.companyservice.dtos.shop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequest {
    private long productId;
    private String review;

}
