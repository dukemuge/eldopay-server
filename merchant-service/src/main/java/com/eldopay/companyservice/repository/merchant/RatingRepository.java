package com.eldopay.companyservice.repository.merchant;

import com.eldopay.companyservice.models.shop.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository  extends JpaRepository<Rating,Long > {

    @Query("SELECT r From Rating r where r.product.id=:productId")
    public List<Rating> getAllProductRating(@Param("productId") Long productId);

}
