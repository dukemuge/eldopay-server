package com.eldopay.companyservice.models.shop;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "title")
    private String title;
    @Column
    private String color;
    private String brand;
    private int quantity;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "discounted_price")
    private BigDecimal  discountedPrice;
    @Column(name = "discount_percent")
    private BigDecimal discountPercent;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Size> sizes = new HashSet<>();
    @Column(name = "image_url")
    private String imageUrl;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
    @Column(name = "num_rating")
    private int numRating;

    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;
//    @ManyToOne
//    @JoinColumn(name = "merchant_id")
//    private Merchant merchant;
}
