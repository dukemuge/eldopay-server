package com.eldopay.companyservice.models.county;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "counties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "county_logo")
    private String countyLogo;
    @Column(name = "county_code")
    private String countyCode;
    //payment details
    @Column(name = "bank_account")
    private String bankAccount;
    @Column(name = "bank_name")
    private String bankName;
    //mpesa payment details
    @Column(name = "pay_bill")
    private String payBill;

    private String userName;
    private String password;
    private String roles;

}
