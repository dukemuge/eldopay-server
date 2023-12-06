package com.eldopay.companyservice.dtos.handyman;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerRatingRequest {
    private Long workerId;
    private double rating;

}
