package com.eldopay.companyservice.dtos.county;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateCountyRequest {
    private String name;
    private String countyLogo;
    private String countyCode;
    private String bankAccount;
    private String bankName;
    private String payBill;
}
