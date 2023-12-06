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
public class WithdrawFromWalletRequest {
    private Long userId;
    private BigDecimal amount;
    private String mobileNumber;
    private String companyAccount;
}
