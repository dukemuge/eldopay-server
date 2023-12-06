package com.eldopay.companyservice.dtos.uber;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DriverRatingRequest {
    private long driverId;
    private double rating;
}
