package com.eldopay.companyservice.repository.merchant;

import com.eldopay.companyservice.models.shop.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository  extends JpaRepository<Review,Long > {

}
