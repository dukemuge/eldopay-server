package com.eldopay.companyservice.models.county;

import com.eldopay.companyservice.models.shop.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "county_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountyProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "waiver_price")
    private BigDecimal  waiverPrice;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "county_id")
    private County county;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CountyProductCategory countyProductCategory;
}
