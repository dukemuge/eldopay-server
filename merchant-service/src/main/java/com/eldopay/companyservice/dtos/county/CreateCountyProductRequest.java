package com.eldopay.companyservice.dtos.county;

import com.eldopay.companyservice.models.county.County;
import com.eldopay.companyservice.models.county.CountyProductCategory;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CreateCountyProductRequest {
    private String topLevelCategory;
    private String secondLevelCategory;
    private String thirdLevelCategory;
    private String title;
    private String description;
    private BigDecimal price;
    private BigDecimal  waiverPrice;
    private LocalDateTime createdAt;
    private County county;
    private CountyProductCategory countyProductCategory;
}
