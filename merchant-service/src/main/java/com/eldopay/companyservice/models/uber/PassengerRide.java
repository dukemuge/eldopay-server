package com.eldopay.companyservice.models.uber;

import com.eldopay.companyservice.models.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PassengerRide {
    @Id
    @GeneratedValue()
    private long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private Driver driver;
    @JsonIgnore
    private List<Integer> declinedDrivers = new ArrayList<>();
    private double pickUpLatitude;
    private double PickUpLongitude;
    private double destinationLatitude;
    private double  destinationLongitude;
    private  long duration;
    private double distance;
    private String pickupArea;
    private String destinationArea;
    private RideStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal fare;
    private int otp;
}
