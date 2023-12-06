package com.eldopay.companyservice.dtos.uber;


import com.eldopay.companyservice.models.uber.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverSignupRequest {
    private long id;
    private String fullName;
    private double rating;
    private double latitude;
    private  double longitude;
    private String password;
    private String email;
    private String mobile;
    private Vehicle vehicle;
}
