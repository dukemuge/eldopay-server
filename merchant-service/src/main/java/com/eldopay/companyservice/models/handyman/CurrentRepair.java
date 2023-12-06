package com.eldopay.companyservice.models.handyman;


import com.eldopay.companyservice.models.Address;
import com.eldopay.companyservice.models.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "current_repairs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrentRepair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    private LocalDateTime dateOfRequest;
    private LocalDateTime timeOfStart;
    private LocalDateTime dateOfExecution;
    private double destinationLatitude;
    private double destinationLongitude;
    private BigDecimal costOfRepair;
    private double amountTaken;
    private String description;
    private boolean isCompleted;
    private boolean isApproved;
    private boolean isPaid;
    private BigDecimal  commission;
    private String mobileNumber;
    @ManyToOne
    @JoinColumn(name = "repair_service_id")
    private RepairService repairService;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    //mpesa
    private String tillNumber;
    private String shortCode;

    //credit_section
    @Column(name = "cardholder_name")
    private String cardholderName;
    @Column(name="card_number")
    private  String cardNumber;
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    @Column(name = "cvv")
    private String cvv;

}
