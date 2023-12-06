package com.eldopay.companyservice.models.shop;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "merchants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "roles")
    private String roles;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Column(name = "location")
    private String location;
    @Column(name = "address")
    private String  address;
    @Column(name = "source_longitude")
    private String  sourceLongitude;
    @Column(name = "source_latitude")
    private String sourceLatitude;
    @Column(name = "county_license")
    private String countyLicense;
    @Column(name = "mobile_no")
    private String mobileNo;
    @Column(name = "county")
    private String county;
    @Column(name = "merchant_code")
    private String merchantCode;
    @Column(name = "merchant_number")
    private String merchantNumber;
    @Enumerated
    private MerchantType merchantType;
    private boolean isApproved;
    @Column(name = "password")
    private String password;

    @Column(name = "discounted_price")
    private String streetLocation;

    //shop setting
    private LocalDateTime openingTime;
    private LocalDateTime closingTime;

    @Column(name = "store_number")
    private String storeNumber;
    @Column(name = "shop_name")
    private String shopName;
    @Column(name = "shop_logo")
    private String shopLogo;

    @Column(name = "till_number")
    private String tillNumber;

    @Column(name = "short_code")
    private String shortCode;

}
