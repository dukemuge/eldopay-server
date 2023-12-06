package com.eldopay.companyservice.dtos.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepositToWalletRequest {
    private Long userId;
    private BigDecimal  amount;
    private String companyAccount;
    private String  userPhoneNumber;
}
