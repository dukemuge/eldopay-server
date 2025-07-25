package com.eldopay.companyservice.models.fees;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fee_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}
