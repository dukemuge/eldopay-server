package com.eldopay.companyservice.models.uber;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="make")
    private String make;
    @Column(name="model")
    private String model;
    @Column(name = "color")
    private String color;
    @Column
    private VehicleType carType;
    @Column(name = "license_plate")
    private String licensePlate;
    @Column(name = "capacity")
    private String capacity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    @JsonIgnore
    private Driver driver;

    private Double latitude;

    private Double  longitude;

}
