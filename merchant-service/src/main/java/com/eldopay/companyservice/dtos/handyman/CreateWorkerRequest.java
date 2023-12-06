package com.eldopay.companyservice.dtos.handyman;

import com.supeapp.delivery.models.WorkerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkerRequest {
    private long id;
    private String fullName;
    private String userName;
    private String nextOfKin;
    private String referee;
    private String refereeContact;
    private String password;
    private String email;
    private String mobile;

    //accreditation
    private String practicingLicense;
    private String practisingLicenseNumber;
    private String licenseImage;
    private String certificateImage;
    private LocalDate licenseExpirationDate;

    //education
    private String secondaryEducation;
    private String tertiaryEducation;
    private String nationalId;


    private String address;
    private String county;
    private String licenseId;
    private WorkerType workerType;
}
