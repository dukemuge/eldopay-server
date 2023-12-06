package com.eldopay.companyservice.dtos.shop;

import com.eldopay.companyservice.models.shop.MerchantType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantRequest {
    private long id;
    private String userName;
    private String password;
    private String firstName;
    private String secondName;
    private String nationalId;
    private String location;
    private String  address;
    private String  sourceLongitude;
    private String sourceLatitude;
    private String countyLicense;
    private String mobileNo;
    private String county;
    private String merchantCode;
    private String merchantNumber;
    private MerchantType merchantType;
    private String streetLocation;
    private String shopName;
    private String shopLogo;

}
