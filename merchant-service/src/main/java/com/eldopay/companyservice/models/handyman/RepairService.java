package com.eldopay.companyservice.models.handyman;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Entity
@Table(name = "repair_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepairService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;
     private String name;
     private String description;
     private BigDecimal costPerService;
     private BigDecimal commissionOfRepair;
     @ManyToOne
     @JoinColumn(name = "worker_id")
     private Worker worker;
}
