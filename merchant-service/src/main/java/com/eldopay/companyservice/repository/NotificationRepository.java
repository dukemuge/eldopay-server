package com.eldopay.companyservice.repository;

import com.eldopay.companyservice.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
