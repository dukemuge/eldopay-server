package com.eldopay.companyservice.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    private String roles;
    @Column(unique = true)
    private String mobile;
    private String profilePicture;

//    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
//    private List<Address> address = new ArrayList<>();

//    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
//    private List<Transaction>  transactions = new ArrayList<>();
//
//    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
//    private List<Order>  orders = new ArrayList<>();

//    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
//    private List<CreditInformation> paymentInformation = new ArrayList<>();

//    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Rating> ratings = new ArrayList<>();
//
//    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
//    private List<Review> reviews = new ArrayList<>();
    private LocalDateTime  createdAt;

}
