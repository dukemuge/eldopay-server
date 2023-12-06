package com.eldopay.companyservice.dtos.shop;

import com.eldopay.companyservice.models.shop.Size;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CreateProductRequest {
    private String title;
    private String description;
    private String brand;
    private int quantity;
    private String color;
    private String imageUrl;
    private String topLevelCategory;
    private String secondLevelCategory;
    private String thirdLevelCategory;
    private BigDecimal price;
    private BigDecimal  discountedPrice;
    private BigDecimal discountPercent;
    private Set<Size> size = new HashSet<>();
    private MerchantRequest merchantRequest;


}
