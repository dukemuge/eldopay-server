package com.eldopay.companyservice.repository.merchant;

import com.eldopay.companyservice.models.shop.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
