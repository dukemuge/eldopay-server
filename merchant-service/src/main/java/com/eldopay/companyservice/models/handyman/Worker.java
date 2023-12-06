package com.eldopay.companyservice.models.handyman;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "workers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;
    private String userName;
    private String nextOfKin;
    private String referee;
    private String refereeContact;
    private double rating;
    private String roles;
    private String password;
    private String email;
    private String mobile;
    private String practicingLicense;
    private String practisingLicenseNumber;
    private String licenseImage;
    private String certificateImage;
    private LocalDate licenseExpirationDate;

    private boolean isApproved;
    //education
    private String secondaryEducation;
    private String tertiaryEducation;
    private String nationalId;
    private String address;
    private String county;
    private String licenseId;
    private BigDecimal totalRevenue = BigDecimal.valueOf(0);
    private WorkerType workerType;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private CurrentRepair currentRepair;

    //added later
    private String tillNumber;
    private String businessCode;
//    private List<CurrentRepair> currentRepairs = new ArrayList<>();
//    private List<RepairService> repairs = new ArrayList<>();

}
