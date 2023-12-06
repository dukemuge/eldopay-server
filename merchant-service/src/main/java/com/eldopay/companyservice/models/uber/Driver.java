package com.eldopay.companyservice.models.uber;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;
    private String userName;
    private double rating;
    private double latitude;
    private  double longitude;
    private String roles;
    private String password;
    private String email;
    private String mobile;
    private String licenseNumber;
    private String licenseCounty;
    private LocalDate licenseExpirationDate;


//    @JsonIgnore
//    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<PassengerRide> rides = new ArrayList<>();
//    @JsonIgnore
//    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<CourierRide> courierRides = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private PassengerRide currentRide;

    private BigDecimal totalRevenue = BigDecimal.valueOf(0);

    @OneToOne(mappedBy = "driver",cascade = CascadeType.ALL)
    private Vehicle vehicle;

}
