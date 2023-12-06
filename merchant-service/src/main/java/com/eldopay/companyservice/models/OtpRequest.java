package com.eldopay.companyservice.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class OtpRequest implements Serializable {
    private String otp;
    private String phoneNo;
}
