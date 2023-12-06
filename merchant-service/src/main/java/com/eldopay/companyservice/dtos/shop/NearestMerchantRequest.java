package com.eldopay.companyservice.dtos.shop;

import com.eldopay.companyservice.models.shop.MerchantType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class NearestMerchantRequest {
    private String shopName;
    private double latitude;
    private double longitude;
    private MerchantType merchantType;
}
