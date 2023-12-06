package com.eldopay.companyservice.repository.merchant;

import com.eldopay.companyservice.models.shop.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o WHERE o.user.id=:userId")
    public List<Order>  getUsersOrder(@Param("userId") Long userId);
}
