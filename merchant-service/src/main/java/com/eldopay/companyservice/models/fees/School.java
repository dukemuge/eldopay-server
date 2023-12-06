package com.eldopay.companyservice.models.fees;

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
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String schoolLogo;
    private String location;
    private String address;
    private String motto;
    private String vision;
    private String history;
    private String roles;
    @ManyToOne
    @JoinColumn(name = "school_category_id")
    private SchoolCategory schoolCategory;
    //Billing details
    private String bankAccount;
    private String payBill;
    private String tillNumber;


    private String userName;
    private String password;

}