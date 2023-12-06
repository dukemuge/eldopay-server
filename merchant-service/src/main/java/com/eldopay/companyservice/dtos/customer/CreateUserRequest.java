package com.eldopay.companyservice.dtos.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private long id;
    private String fullName;
    private String password;
    private String email;
    private String mobile;
}
