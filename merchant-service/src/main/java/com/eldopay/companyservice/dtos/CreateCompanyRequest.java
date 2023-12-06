package com.eldopay.companyservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class CreateCompanyRequest {
    private String logo;
    private  String location;
    private String streetName;
    private LocalDate dateOfIncorporation;
    private String tillNumber;
    private BigDecimal earnings;
    private String shortCode;
    private String email;
    private String bankAccount;

}
