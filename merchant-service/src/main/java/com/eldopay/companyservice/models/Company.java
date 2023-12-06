package com.eldopay.companyservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String logo;
    private  String location;
    private String streetName;
    private LocalDate dateOfIncorporation;
    private String tillNumber;
    private BigDecimal earnings;
    private String shortCode;
    private String email;
    private String bankAccount;
}
